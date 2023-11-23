package com.elkastali.courseservice;

import com.elkastali.courseservice.entity.Course;
import com.elkastali.courseservice.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }


/*    @Bean
    CommandLineRunner commandLineRunner(CourseService courseService){
        return args -> {
            List.of(Course.builder()
                    .name("Java")
                    .price(1000)
                    .duration("3 months")
                    .build(),

                    Course.builder()
                            .name("C#")
                            .price(100)
                            .duration("1 month")
                            .build(),
                    Course.builder()
                            .name("ML&DL")
                            .price(100)
                            .duration("7 month")
                            .build()).forEach(c -> courseService.addNewCourse(c));
        };
    }*/
}
