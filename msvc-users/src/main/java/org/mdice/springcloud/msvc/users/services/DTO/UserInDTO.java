package org.mdice.springcloud.msvc.users.services.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class UserInDTO {

    @NotBlank(message = "Username cannot be empty")
    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Email
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable = true,length = 50,unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Column(nullable = true)
    private String password;

}
