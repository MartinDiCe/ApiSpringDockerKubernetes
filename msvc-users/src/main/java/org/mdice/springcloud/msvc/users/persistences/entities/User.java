package org.mdice.springcloud.msvc.users.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la estrategia identity porque usamos mysql
    private Long id;

    @Column(nullable = true, length = 30, unique = true)
    private String name;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;


}
