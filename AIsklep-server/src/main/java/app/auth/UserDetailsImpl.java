package app.auth;

import app.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private UserDTO userDTO;

    public UserDetailsImpl(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDTO != null ?
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) :
                Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDTO != null && userDTO.getIsActive() != null ? userDTO.getIsActive() : false;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
