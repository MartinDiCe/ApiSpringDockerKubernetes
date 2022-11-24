package org.mdice.springcloud.msvc.courses.controllers;

import org.mdice.springcloud.msvc.courses.persistences.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.services.CourseService;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody CourseInDTO courseInDTO){
        Course course = this.service.saveCourse(courseInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }


    @PutMapping("change-status/{id}")
    public ResponseEntity<?> activate(@PathVariable("id") Long id, @RequestBody CourseStatus status){
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


    @PutMapping ( "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,@RequestBody CourseInDTO courseInDTO){
        this.service.updateCourse(id, courseInDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Course with id: " +id+" was updated successfully");

    }


}
