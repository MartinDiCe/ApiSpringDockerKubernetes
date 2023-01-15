package org.mdice.springcloud.msvc.courses.persistences.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "courses")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course Name cannot be empty")
    @Column(nullable = true)
    private String name;

    private CourseStatus courseStatus;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
