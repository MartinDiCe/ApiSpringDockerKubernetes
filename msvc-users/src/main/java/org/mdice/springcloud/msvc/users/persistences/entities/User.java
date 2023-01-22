package org.mdice.springcloud.msvc.users.persistences.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la estrategia identity porque usamos mysql
    private Long id;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Email(message = "is not a valid email address")
    @NotBlank(message = "cannot be empty")
    @Column(nullable = true,length = 50, unique = true)
    private String email;

    @NotBlank(message = "cannot be empty")
    @Column(nullable = true)
    private String password;

    private LocalDateTime createDate;

    private Status status;

    private LocalDateTime updateDate;


}
