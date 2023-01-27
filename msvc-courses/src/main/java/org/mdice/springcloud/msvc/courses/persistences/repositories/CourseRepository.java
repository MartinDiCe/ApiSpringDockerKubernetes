package org.mdice.springcloud.msvc.courses.persistences.repositories;

import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {

    public List<Course> findAllByCourseStatus(CourseStatus status);

    Optional<Course> findByName(String name);

}
