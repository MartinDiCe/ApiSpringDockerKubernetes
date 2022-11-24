package org.mdice.springcloud.msvc.users.persistences.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la estrategia identity porque usamos mysql
    private Long id;

    @Column(nullable = true, length = 30, unique = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    private LocalDateTime createDate;

    private Status status;

    private LocalDateTime updateDate;


}
