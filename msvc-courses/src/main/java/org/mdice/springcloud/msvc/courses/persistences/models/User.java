package org.mdice.springcloud.msvc.courses.persistences.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createDate;

    private Status status;

    private LocalDateTime updateDate;

}
