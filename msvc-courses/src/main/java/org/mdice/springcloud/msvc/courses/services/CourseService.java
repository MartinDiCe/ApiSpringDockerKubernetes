package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.persistences.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CourseService {


    List<Course> listCourses();

    Optional<Course> findCourseById(Long id);

    Course saveCourse(CourseInDTO CourseInDTO);

    void deleteCourse(Long id);

    Course updateCourse(Long id, CourseInDTO courseInDTO);

    List<Course> finAllByStatus(CourseStatus status);

    Course newStatus(Long id, CourseStatus status);

    Optional<Course> findByName (String name);

}
