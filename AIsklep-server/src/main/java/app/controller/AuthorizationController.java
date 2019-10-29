package app.controller;

import app.auth.UserSessionInfo;
import app.auth.jwt.JwtTokenProvider;
import app.dto.FrontUserDTO;
import app.dto.UserDTO;
import app.dto.auth.ChangePasswordDTO;
import app.dto.auth.LoginDTO;
import app.dto.auth.RegisterDTO;
import app.dto.auth.ResetPasswordDTO;
import app.exception.BadRequestException;
import app.model.User;
import app.service.UserService;
import app.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController extends Controller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionInfo userSessionInfo;

    @Secured("ROLE_USER")
    @GetMapping("/user")
    public ResponseEntity user() throws IOException {
        return respond(DtoUtils.convert(userSessionInfo.getCurrentUser(), FrontUserDTO.class), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO data) throws IOException {
        try {
            String username = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username);
            Map<Object, Object> model = new HashMap<>();
            model.put("token", token);
            return respond(model, HttpStatus.OK);
        }
        catch(AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) throws Exception {
        User userExists = userService.findUserByEmail(data.getEmail());
        if (userExists != null) {
            throw new BadRequestException("User with email: " + data.getEmail() + " already exists");
        }
        userService.createUser(data);
        return respond(HttpStatus.OK);
    }

    @GetMapping("/activate")
    public ResponseEntity activate(@RequestParam("token") String token) throws Exception {
        User user = userService.findByRegisterToken(token);
        if(user == null || user.getIsActive()) {
            throw new BadRequestException("User not found or already active");
        }
        user.setIsActive(true);
        userService.save(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("email", user.getEmail());
        return respond(model, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDTO data) throws Exception {
        User user = userService.findUserByEmail(data.getEmail());
        if (user == null) {
            throw new BadRequestException("User does not exist");
        }
        userService.resetPassword(user);
        return respond(HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDTO data) throws Exception {
        UserDTO user = userSessionInfo.getCurrentUser();
        if (user == null) {
            throw new BadRequestException("Not logged in");
        }
        userService.changePassword(user, data.getOldPassword(), data.getPassword());
        return respond(HttpStatus.OK);
    }
}
