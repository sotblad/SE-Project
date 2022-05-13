package se_project.TestControllers;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import se_project.controller.CourseController;
import se_project.entity.Course;
import se_project.entity.Instructor;
import se_project.service.CourseService;
import se_project.service.InstructorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestCourseController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CourseController courseController;
	
	@Autowired
	InstructorService instructorService;
	
	@Autowired
	private CourseService courseService;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
		if(instructorService.findByUsername("testUsername") == null) {
			Instructor instructor = new Instructor("Test Instructor", "testInstructor", "$2a$12$.PQuhJBs4B4amRdEc9w8gu83H0mNmkJ4Io3cVYlklMH3jwZJvWpLG", true);
			instructorService.save(instructor);
		}
    }
	
	@Test
	void testCourseControllerIsNotNull() {
		Assertions.assertNotNull(courseController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}	
	
	@WithMockUser(username = "zarras")
	@Test 
	void testMyCoursesReturnsPage() throws Exception {
		mockMvc.perform(
			get("/myCourses")).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("listCourses")).
			andExpect(view().name("dashboard/coursesList")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testAddCourseReturnsPage() throws Exception {
		mockMvc.perform(
			get("/addCourse")).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("course")).
			andExpect(view().name("dashboard/addCourse")
		);		
	}

	@WithMockUser(value = "zarras")
	@Test 
	void testPostCourseReturnsPage() throws Exception {
		
	    Course course = new Course("testName", instructorService.findByUsername("testUsername"), "testSyllabus", 2018, 1, 10, 10);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("instructor.id", Integer.toString(course.getInstructor().getId()));
	    multiValueMap.add("instructor.fullname", course.getInstructor().getFullname());
	    multiValueMap.add("instructor.username", course.getInstructor().getUsername());
	    multiValueMap.add("instructor.password", course.getInstructor().getPassword());
	    multiValueMap.add("instructor.enabled", Boolean.toString(course.getInstructor().isEnabled()));
	    multiValueMap.add("syllabus", course.getSyllabus());
	    multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester", Integer.toString(course.getSemester()));
	    multiValueMap.add("examWeight", Double.toString(course.getExamWeight()));
	    multiValueMap.add("projectWeight", Double.toString(course.getProjectWeight()));
	    
		mockMvc.perform(
			post("/postCourse").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:myCourses")
		);
	}

	@WithMockUser(value = "zarras")
	@Test 
	void testDeleteCourseReturnsPage() throws Exception {
		Course course = new Course("testName", instructorService.findByUsername("testUsername"), "testSyllabus", 2018, 1, 10, 10);
		courseService.save(course);
		List<Course> courses = courseService.findAll();
	    int courseId = courses.get(courses.size()-1).getId();
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("course", Integer.toString(courseId));
	    
		mockMvc.perform(
			post("/deleteCourse").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:myCourses")
		);	
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testEditCourseReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("course", Integer.toString(1));
		
		mockMvc.perform(
			get("/editCourse").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("course")).
			andExpect(view().name("dashboard/editCourse")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateCourseReturnsPage() throws Exception {   	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("course", Integer.toString(1));
	    
		mockMvc.perform(
			post("/updateCourse").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/myCourses")
		);	
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testViewCourseReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("course", Integer.toString(1));
		
		mockMvc.perform(
			get("/viewCourse").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("course")).
			andExpect(model().attributeExists("students")).
			andExpect(view().name("dashboard/viewCourse")
		);		
	}
}