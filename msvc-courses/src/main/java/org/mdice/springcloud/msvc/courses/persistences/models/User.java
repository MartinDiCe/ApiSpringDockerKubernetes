package org.mdice.springcloud.msvc.courses.persistences.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createDate;

    private Status status;

    private LocalDateTime updateDate;

}
