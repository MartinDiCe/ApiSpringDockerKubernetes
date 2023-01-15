package org.mdice.springcloud.msvc.courses.controllers;

import org.mdice.springcloud.msvc.courses.persistences.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.services.CourseService;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
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
        if (userOptional.isPresent()){
            return ResponseEntity.ok().body(userOptional.get());
        }

        return ResponseEntity.notFound().build();

    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CourseInDTO courseInDTO, BindingResult result){


        ResponseEntity<Map<String, String>> errors = validate(result);
        if (errors != null) return errors;

        Optional<Course> optionalCourse = service.findByName(courseInDTO.getName());

        if (optionalCourse.isPresent()) {

            Course courseDB = optionalCourse.get();

            if (courseInDTO.getName().equalsIgnoreCase(courseDB.getName())) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Course already exists"));
            }

            if (service.findByName(courseInDTO.getName()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Course already exists"));
            }
        }

        Course course = this.service.saveCourse(courseInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }


    @PutMapping ( "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseInDTO courseInDTO, BindingResult result, @PathVariable("id") Long id){

        ResponseEntity<Map<String, String>> errors = validate(result);
        if (errors != null) return errors;

        Optional<Course> optionalCourse = service.findCourseById(id);

        if (optionalCourse.isPresent()) {

            Course courseDB = optionalCourse.get();

            if (courseInDTO.getName().equalsIgnoreCase(courseDB.getName())) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Course already exists"));
            }

            if (service.findByName(courseInDTO.getName()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Course already exists"));
            }
        }
            Course course = this.service.updateCourse(id, courseInDTO);
            return ResponseEntity.status(HttpStatus.OK).body(course);

    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<?> activate(@PathVariable("id") Long id, @Valid @RequestBody CourseStatus status){
        this.service.newStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body("Course with id: " +id +" was updated to new status: " +status+ " successfully");
    }


    @GetMapping("/status/{status}")
    public List<Course> findAllByStatus(@PathVariable("status") CourseStatus status){
        return this.service.finAllByStatus(status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        this.service.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body("Course with id: " +id+" was deleted successfully");
    }


    private ResponseEntity<Map<String,String>> validate(BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        return null;
    }


}
