package org.mdice.springcloud.msvc.users.persistences.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la estrategia identity porque usamos mysql
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Email
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable = true,length = 50, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Column(nullable = true)
    private String password;

    private LocalDateTime createDate;

    private Status status;

    private LocalDateTime updateDate;


}
