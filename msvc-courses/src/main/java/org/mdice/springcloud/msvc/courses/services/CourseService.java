package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.persistences.models.Status;
import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.UserCourse;
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

    Optional<UserCourse> addUser(UserInDTO user, Long idCourse);

    Optional <UserCourse> createUserCourse(UserInDTO user, Long idCourse);

    Optional<Course> deleteUserCourse(UserInDTO user, Long idCourse);

    Optional<UserCourse> findUserId(Course course, UserInDTO user);

}
