package org.mdice.springcloud.msvc.users.services.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class UserInDTO {


    private String username;

    @Email

    private String email;


    private String password;

}
