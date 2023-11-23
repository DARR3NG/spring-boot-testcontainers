package com.elkastali.courseservice.service;

import com.elkastali.courseservice.entity.Course;
import com.elkastali.courseservice.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository courseRepository;


    public List<Course>findAll(){
        log.info("CourseService::findAll method executed");
        return courseRepository.findAll();
    }

    public Course addNewCourse(Course course){
        log.info("CourseService::addNewCourse method executed");
        return courseRepository.save(course);
    }


    public Course findByid(Long id){
        log.info("CourseService::findByid method executed");
        return courseRepository.findById(id).orElse(null);
    }
}
