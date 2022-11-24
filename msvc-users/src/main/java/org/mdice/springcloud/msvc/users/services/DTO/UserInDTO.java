package org.mdice.springcloud.msvc.users.services.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserInDTO {

    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

}
