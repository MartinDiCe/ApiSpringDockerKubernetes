package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;

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

    Optional<Course> findByName(String name);

    Optional<User> addUser(User user, Long idCourse);

    Optional <User> createUserCourse(User user, Long idCourse);

    Optional<User> deleteUserCourse(User user, Long idCourse);

}
