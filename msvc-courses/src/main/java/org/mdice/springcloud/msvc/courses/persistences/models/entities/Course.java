package org.mdice.springcloud.msvc.courses.persistences.models.entities;


import lombok.Data;
import org.mdice.springcloud.msvc.courses.persistences.models.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<UserCourse> usersCourse;

    @Transient
    private List<User> users;

    private CourseStatus courseStatus;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public Course() {

        usersCourse=new ArrayList<>();

    }

    public void addUserCourse(UserCourse userCourse){

        usersCourse.add(userCourse);

    }

    public void removeUserCourse(UserCourse userCourse){

        usersCourse.remove(userCourse);

    }


}
