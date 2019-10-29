package app.service;

import app.dto.FrontUserDTO;
import app.dto.auth.RegisterDTO;
import app.dto.UserDTO;
import app.model.User;
import app.repository.UserRepository;
import app.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public FrontUserDTO findFrontUserByEmail(String email) {
        return DtoUtils.convert(userRepository.findByEmail(email), FrontUserDTO.class);
    }

    public void createUser(RegisterDTO data) {
        User newUser = DtoUtils.convert(data, User.class);
        newUser.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
        newUser.setIsActive(false);
        newUser.setRegisterToken(UUID.randomUUID().toString());
        emailService.sendRegisterEmail(newUser.getEmail(), newUser.getRegisterToken());
        userRepository.save(newUser);
    }

    public User findByRegisterToken(String token) {
        return userRepository.findByRegisterToken(token);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void resetPassword(User user) {
        String newPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        emailService.sendResetPasswordEmail(user.getEmail(), newPassword);
        userRepository.save(user);
    }

    public void changePassword(UserDTO userDTO, String oldPassword, String password) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }
}
