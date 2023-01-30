package org.mdice.springcloud.msvc.courses.persistences.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.mdice.springcloud.msvc.courses.persistences.models.User;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name="Users_courses")
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (!(obj instanceof UserCourse)){

            return false;
        }

        UserCourse o = (UserCourse) obj;

        return  this.userId != null && this.userId.equals(o.userId);

    }
}
