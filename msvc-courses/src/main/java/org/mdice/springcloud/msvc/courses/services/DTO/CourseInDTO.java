package org.mdice.springcloud.msvc.courses.services.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CourseInDTO {


    @Column(nullable = true)
    private String name;

}
