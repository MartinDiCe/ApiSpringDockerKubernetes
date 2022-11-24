package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.courses.mapper.CourseInDTOToCourse;
import org.mdice.springcloud.msvc.courses.persistences.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.persistences.repositories.CourseRepository;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplement implements CourseService{

    private final CourseRepository repository;
    private final CourseInDTOToCourse mapper;

    public CourseServiceImplement(CourseRepository repository, CourseInDTOToCourse mapperCourse) {

        this.repository = repository;
        this.mapper = mapperCourse;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> listCourses() {

        return repository.findAll();

    }


    @Override
    public Optional<Course> findCourseById(Long id) {

        Optional<Course> optional = this.repository.findById(id);

        if (optional.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        return repository.findById(id);

    }


    @Override
    @Transactional
    public Course saveCourse(CourseInDTO courseInDTO) {

        Course course = mapper.map(courseInDTO);

        return repository.save(course);

    }


    @Override
    @Transactional
    public void deleteCourse(Long id) {

        Optional<Course> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);

    }


    @Override
    @Transactional
    public Course updateCourse(Long id, CourseInDTO courseInDTO) {

        Optional<Course> optional = this.repository.findById(id);

        if (optional.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        Course course = optional.get();
        course.setName(courseInDTO.getName());
        course.setUpdateDate(LocalDateTime.now());
        repository.save(course);

        return course;

    }


    @Override
    @Transactional(readOnly = true)
    public List<Course> finAllByStatus(CourseStatus status) {

        return repository.findAllByCourseStatus(status);

    }

    @Override
    @Transactional
    public Course newStatus(Long id, CourseStatus status) {
        Optional<Course> optional = this.repository.findById(id);

        if (optional.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        Course course = optional.get();

        course.setCourseStatus(status);
        repository.save(course);

        return course;

    }
}
