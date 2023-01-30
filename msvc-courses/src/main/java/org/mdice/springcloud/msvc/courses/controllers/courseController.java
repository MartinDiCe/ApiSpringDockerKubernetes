package org.mdice.springcloud.msvc.courses.controllers;

import feign.FeignException;
import org.apache.catalina.startup.UserConfig;
import org.mdice.springcloud.msvc.courses.persistences.models.Status;
import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.UserCourse;
import org.mdice.springcloud.msvc.courses.services.CourseService;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.io.IOException;
import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/courseApi")
public class courseController {

    private final CourseService service;

    public courseController(CourseService service) {

        this.service = service;

    }

    @GetMapping
    public List<Course> listAll() {

        return service.listCourses();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {

        Optional<Course> userOptional = service.findCourseById(id);

        if (userOptional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error: ", "Course not exists"));

        }

        return ResponseEntity.ok().body(userOptional.get());

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CourseInDTO courseInDTO, BindingResult result){


        ResponseEntity<Map<String, String>> errors = validate(result);
        if (errors != null) return errors;

        Optional<Course> oc = service.findByName(courseInDTO.getName());

        if (oc.isPresent()) {

            if (courseInDTO.getName().equalsIgnoreCase(oc.get().getName())) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("Error: ", "Course already exists"));

            }
        }

        Course course = this.service.saveCourse(courseInDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(course);

    }

    @PutMapping ( "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseInDTO courseInDTO, BindingResult result, @PathVariable("id") Long id){

        ResponseEntity<Map<String, String>> errors = validate(result);
        if (errors != null) return errors;

        Optional<Course> oc = service.findCourseById(id);

        if (oc.isPresent()) {

            if (courseInDTO.getName().equalsIgnoreCase(oc.get().getName())) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("Error: ", "Course already exists"));

            }
        }

            Course course = this.service.updateCourse(id, courseInDTO);

            return ResponseEntity.status(HttpStatus.OK).body(course);

    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id, @RequestBody CourseStatus status){

        Optional<Course> oc = service.findCourseById(id);

        if (oc.isEmpty()) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("Error: ", "Course not exists"));

        }

        this.service.newStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK)
                        .body(Collections.singletonMap("Success: ", "Course with id: "
                                +id +" was updated to new status: " +status+ " successfully"));

    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findAllByStatus(@PathVariable("status") CourseStatus status){

        List<Course> courses = this.service.finAllByStatus(status);

        if (courses.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Status not exists"));

        }

        return ResponseEntity.status(HttpStatus.OK).body(courses);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){

        this.service.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Course with id: " +id+" was deleted successfully");

    }

    @PutMapping("/add-user/{idCourse}")
    public ResponseEntity<?> addUserToCourse(@RequestBody UserInDTO user, @PathVariable Long idCourse){

        Optional<Course> oc = service.findCourseById(idCourse);

        if (oc.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Course not exists"));

        }

        try {

            Optional<UserCourse> ou = service.findUserId(oc.get(), user);

            if (ou.isPresent()) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "User already exists in the course"));

            }
        }

        catch (FeignException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Api not response " + e.getMessage()));

        }

        Optional<UserCourse> o;

        try {

            o = service.addUser(user, idCourse);

        }

        catch (FeignException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Api not response" + e.getMessage()));

        }

        if (o.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Username not exists"));

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(o.get());

    }

    @PostMapping("/create-user/{idCourse}")
    public ResponseEntity<?> createUserToCourse(@RequestBody UserInDTO user, @PathVariable Long idCourse){

        Optional<Course> oc = service.findCourseById(idCourse);

        if (oc.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Course not exists"));

        }

        Optional<UserCourse> o;

        try {

            o = service.createUserCourse(user, idCourse);

        }

        catch (FeignException e ) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Api not response " + e.getMessage()));

        }

        if (o.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Username already exists for this course"));

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(o.get());

    }

    @DeleteMapping ("/delete-user/{idCourse}")
    public ResponseEntity<?> deleteUserToCourse(@RequestBody UserInDTO user, @PathVariable Long idCourse){

        Optional<Course> oc = service.findCourseById(idCourse);

        if (oc.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Course not exists"));

        }

        Optional<Course> o;

        try {

            o = service.deleteUserCourse(user, idCourse);

        }

        catch (FeignException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Api not response " + e.getMessage()));

        }

        if (o.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error","Username not exists"));

        }

        return ResponseEntity.status(HttpStatus.OK).body(o.get());

    }

    private ResponseEntity<Map<String,String>> validate(BindingResult result) {

        if (result.hasErrors()){

            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors()
                    .forEach(err -> errors.put(err.getField(), "Field "
                            + err.getField() + " " + err.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);

        }

        return null;
    }
}
