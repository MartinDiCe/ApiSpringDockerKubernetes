package org.mdice.springcloud.msvc.users.services.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
public class UserInDTO {

    @NotBlank(message = "cannot be empty")
    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Email(message = "is not a valid email address")
    @NotBlank(message = "cannot be empty")
    @Column(nullable = true,length = 50,unique = true)
    private String email;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = true)
    private String password;

}
