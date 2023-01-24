package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.Clients.UserClientRest;
import org.mdice.springcloud.msvc.courses.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.courses.mapper.CourseInDTOToCourse;
import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.UserCourse;
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

    private final UserClientRest client;

    public CourseServiceImplement(CourseRepository repository, CourseInDTOToCourse mapperCourse, UserClientRest client) {

        this.repository = repository;
        this.mapper = mapperCourse;
        this.client = client;

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

    @Override
    public Optional<Course> findByName(String name) {

        return repository.findByName(name);

    }

    @Transactional
    @Override
    public Optional<User> addUser(User user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        if (o.isPresent()){
            Optional<User> ou = client.getByUsername(user.getUsername());

            if (ou.isPresent()) {
                User msvcUser = ou.get();
                Course course = o.get();
                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(msvcUser.getId());

                course.addUserCourse(userCourse);
                repository.save(course);

                return Optional.of(msvcUser);
            }
            return Optional.empty();
        }

        return Optional.empty();

    }

    @Transactional
    @Override
    public Optional<User> createUserCourse(User user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        if (o.isPresent()){

            Optional<User> ou = client.getByUsername(user.getUsername());

            if(!ou.isPresent()) {

                UserInDTO msvcUserInDTO = new UserInDTO();

                msvcUserInDTO.setUsername(user.getUsername());
                msvcUserInDTO.setEmail(user.getEmail());
                msvcUserInDTO.setPassword(user.getPassword());

                User msvcNewUser = client.create(msvcUserInDTO);

                Course course = o.get();
                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(msvcNewUser.getId());

                course.addUserCourse(userCourse);
                repository.save(course);

                return Optional.of(msvcNewUser);
            }
            return Optional.empty();
            }
            return Optional.empty();

    }

    @Transactional
    @Override
    public Optional<User> deleteUserCourse(User user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        if (o.isPresent()){
            Optional<User> ou = client.getById(user.getId());

            if(ou.isPresent()) {
                User msvcUser = ou.get();
                Course course = o.get();
                UserCourse userCourse = new UserCourse();
                userCourse.setUserId(msvcUser.getId());

                course.removeUserCourse(userCourse);
                repository.save(course);

                return Optional.of(msvcUser);
            }
            return Optional.empty();
        }

        return Optional.empty();

    }

}
