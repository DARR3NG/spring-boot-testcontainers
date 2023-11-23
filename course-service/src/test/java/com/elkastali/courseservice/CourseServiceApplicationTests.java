package com.elkastali.courseservice;

import com.elkastali.courseservice.controller.CourseController;
import com.elkastali.courseservice.entity.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@AutoConfigureMockMvc
class CourseServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

static MySQLContainer mySQLContainer=new MySQLContainer("mysql:latest");



@DynamicPropertySource
static void configureProperties(DynamicPropertyRegistry registry){
    registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username",mySQLContainer::getUsername);
    registry.add("spring.datasource.password",mySQLContainer::getPassword);

}

@BeforeAll
static public void beforAll(){
    mySQLContainer.start();
}

    @AfterAll
    static public void afterAll(){
        mySQLContainer.stop();
    }

    public void setUp(){
        this.mockMvc= MockMvcBuilders
                .standaloneSetup(CourseController.class)
                .build();
    }

    @Test
    void contextLoads() {
    }



    @Test
    public void addNewCourseTest() throws Exception {

        Course course=Course.builder()
                .name("test-course")
                .price(100)
                .duration("0 month")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/courses")
                .contentType("application/json")
                .content(asJsonString(course))
                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());        
    }


    @Test
    public void getAllCoursesTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses")
                .accept("application/json")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    private String asJsonString(Object object) {

        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
