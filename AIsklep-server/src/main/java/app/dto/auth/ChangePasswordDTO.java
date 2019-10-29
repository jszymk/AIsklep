package app.dto.auth;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String password;
    private String oldPassword;
}
