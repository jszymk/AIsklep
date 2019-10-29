package app.auth;

import app.dto.UserDTO;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

@Component
@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UserSessionInfo {

    @Autowired
    private UserService userService;

    private UserDTO userDTO;

    public UserDTO getCurrentUser() {
        if (userDTO == null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl) {
            userDTO = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserDTO();
        }
        return userDTO;
    }

    public void invalidate() {
        SecurityContextHolder.clearContext();
        userDTO = null;
    }
}
