package org.mdice.springcloud.msvc.courses.persistences.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Users_courses")
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", unique = true)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserCourse)){
            return false;
        }

        UserCourse obj = (UserCourse) o;
        return this.userId != null && this.userId.equals(obj.userId);
    }


}
