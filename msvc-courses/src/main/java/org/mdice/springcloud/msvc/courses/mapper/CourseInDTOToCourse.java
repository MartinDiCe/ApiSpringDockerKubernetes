package org.mdice.springcloud.msvc.courses.mapper;


import org.mdice.springcloud.msvc.courses.persistences.models.entities.Course;
import org.mdice.springcloud.msvc.courses.persistences.models.entities.CourseStatus;
import org.mdice.springcloud.msvc.courses.services.DTO.CourseInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class CourseInDTOToCourse implements IMapper<CourseInDTO, Course>{
    @Override
    public Course map(CourseInDTO in) {

        Course course = new Course();

        course.setName(in.getName());
        course.setCourseStatus(CourseStatus.OPENED);
        course.setCreateDate(LocalDateTime.now());
        course.setUpdateDate(null);

        return course;

    }
}
