package app.dto.auth;

import lombok.Data;

@Data
public class RegisterDTO {
    private String password;
    private String email;
    private String address;
    private String phone;
}
