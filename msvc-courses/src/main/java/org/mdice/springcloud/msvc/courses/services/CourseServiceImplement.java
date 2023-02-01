package org.mdice.springcloud.msvc.courses.services;

import org.mdice.springcloud.msvc.courses.Clients.UserClientRest;
import org.mdice.springcloud.msvc.courses.mapper.CourseInDTOToCourse;
import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.UserCourse;
import org.mdice.springcloud.msvc.courses.persistences.repositories.CourseRepository;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

            this.repository.findById(id);

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

            this.repository.findById(id);

        }

        this.repository.deleteById(id);

    }

    @Override
    @Transactional
    public Course updateCourse(Long id, CourseInDTO courseInDTO) {

        Optional<Course> optional = this.repository.findById(id);

        if (optional.isEmpty()){

            this.repository.findById(id);

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

            this.repository.findById(id);

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
    public Optional<UserCourse> addUser(UserInDTO user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        Optional<User> ou = client.getByUsername(user.getUsername());

        if (ou.isEmpty()) {

            return Optional.empty();

        }

        User msvcUser = ou.get();
        Course course = o.get();


        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(msvcUser.getId());

        course.addUserCourse(userCourse);
        repository.save(course);

        return Optional.of(userCourse);

    }

    @Transactional
    @Override
    public Optional<UserCourse> createUserCourse(UserInDTO user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        Optional<User> ou = client.getByUsername(user.getUsername());

        if(ou.isPresent()) {

            return Optional.empty();

        }

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

        return Optional.of(userCourse);

    }

    @Transactional
    @Override
    public Optional<Course> deleteUserCourse(UserInDTO user, Long idCourse) {

        Optional<Course> o = repository.findById(idCourse);

        Optional<User> ou = client.getByUsername(user.getUsername());

        if(ou.isEmpty()) {

            return Optional.empty();

        }

        User msvcUser = ou.get();
        Course course = o.get();
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(msvcUser.getId());

        List<UserCourse> uc = course.getUsersCourse();

        for (UserCourse u : uc){

            if (!(u.getUserId().equals(msvcUser.getId()))) {

                return Optional.empty();

            }
        }

        course.removeUserCourse(userCourse);
        repository.save(course);

        return Optional.of(course);

    }

    @Override
    public Optional<UserCourse> findUserId(Course course, UserInDTO user) {

        Optional<Course> o = repository.findById(course.getId());

        if (o.isPresent()) {

            List<UserCourse> userCourse = o.get().getUsersCourse();

            Optional <User> ou = client.getByUsername(user.getUsername());

            if (ou.isEmpty()){

                return Optional.empty();

            }

            for (UserCourse uc: userCourse){

                if (uc.getUserId().equals(ou.get().getId())) {

                    return Optional.of(uc);

                }

            }

            return Optional.empty();

        }

        return Optional.empty();

    }

    @Override
    public Optional<Course> usersById(Long courseId) {

        Optional<Course> o = repository.findById(courseId);

        if (o.isPresent()) {

            Course course = o.get();

            if (!course.getUsersCourse().isEmpty()) {

                List<Long> ids = course
                        .getUsersCourse().stream().map(cu -> cu.getUserId())
                        .collect(Collectors.toList());

                List<User> users = client.getUsersByCourse(ids);

                course.setUsers(users);

            }

            return Optional.of(course);

        }

        return Optional.empty();

    }
}
