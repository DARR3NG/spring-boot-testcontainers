package com.elkastali.courseservice.repository;

import com.elkastali.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Long> {
}
