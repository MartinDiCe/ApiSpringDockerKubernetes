package org.mdice.springcloud.msvc.courses.persistences.models.entities;

import lombok.Getter;
import lombok.Setter;
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
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }

        if (!(o instanceof UserCourse obj)){

            return false;
        }

        return Objects.equals(this.userId, obj.userId);

    }
}
