package org.mdice.springcloud.msvc.courses.services.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class CourseInDTO {

    @NotBlank(message = "Course Name cannot be empty")
    @Column(nullable = true)
    private String name;

}
