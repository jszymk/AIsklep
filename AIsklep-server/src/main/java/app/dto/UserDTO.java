package app.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String password;
    private String email;
    private String address;
    private String phone;
    private Boolean isActive;
}
