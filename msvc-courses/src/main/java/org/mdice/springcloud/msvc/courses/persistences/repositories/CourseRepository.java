package org.mdice.springcloud.msvc.courses.persistences.repositories;

import org.mdice.springcloud.msvc.courses.persistences.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.entities.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {

    public List<Course> findAllByCourseStatus(CourseStatus status);

}
