package app.auth;

import app.dto.UserDTO;
import app.service.UserService;
import app.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO userDTO = DtoUtils.convert(userService.findUserByEmail(s), UserDTO.class);
        return new UserDetailsImpl(userDTO);
    }
}
