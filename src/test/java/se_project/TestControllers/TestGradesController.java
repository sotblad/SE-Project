package se_project.TestControllers;
import org.junit.jupiter.api.Test;
import org.junit.Before;
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
import se_project.controller.GradesController;
import se_project.controller.Singleton;
import se_project.entity.Course;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.StudentRegistrationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestGradesController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	GradesController gradesController;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	@Autowired
	private CourseService courseService;

	@BeforeEach
    public void setup() {
		Singleton.getInstance();
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCourseControllerIsNotNull() {
		Assertions.assertNotNull(gradesController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}	
	
	@WithMockUser(username = "zarras")
	@Test 
	void testEditGradesReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("student", Integer.toString(1));
	    multiValueMap.add("course", Integer.toString(1));
	    
		mockMvc.perform(
			get("/editGrades").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("student")).
			andExpect(view().name("dashboard/editGrades")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateGradesReturnsPage() throws Exception {
		List<StudentRegistration> studentRegistrations = studentRegistrationService.findAll();
	    StudentRegistration studentRegistration = studentRegistrations.get(studentRegistrations.size()-1);
	    
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", Integer.toString(studentRegistration.getId()));
	    multiValueMap.add("studentId", Integer.toString(studentRegistration.getStudentId()));
	    multiValueMap.add("name", studentRegistration.getName());
	    multiValueMap.add("yearOfRegistration", Integer.toString(studentRegistration.getYearOfRegistration()));
	    multiValueMap.add("semester", Integer.toString(studentRegistration.getSemester()));
	    multiValueMap.add("courseId", Integer.toString(studentRegistration.getCourseId()));
	    multiValueMap.add("grade", Double.toString(studentRegistration.getGrade()));
	    multiValueMap.add("projectGrade", Double.toString(studentRegistration.getProjectGrade()));
	    multiValueMap.add("examGrade", Double.toString(studentRegistration.getExamGrade()));
	    
		mockMvc.perform(
			post("/updateGrades").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + studentRegistration.getCourseId())
		);	
	}
	
	@WithMockUser(username = "zarras")
	@Test 
	void testEditWeightsReturnsPage() throws Exception {
	    
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("course", Integer.toString(1));
	    
		mockMvc.perform(
			get("/editWeights").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("course")).
			andExpect(view().name("dashboard/editWeights")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateWeightsReturnsPage() throws Exception {
		List<Course> courses = courseService.findAll();
		Course course = courses.get(courses.size()-1);
	    
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", Integer.toString(course.getId()));
	    multiValueMap.add("name", course.getName());
	    multiValueMap.add("instructor", course.getInstructor());
	    multiValueMap.add("syllabus", course.getSyllabus());
	    multiValueMap.add("year", Integer.toString(course.getYear()));
	    multiValueMap.add("semester", Integer.toString(course.getSemester()));
	    multiValueMap.add("examWeight", Double.toString(course.getExamWeight()));
	    multiValueMap.add("projectGrade", Double.toString(course.getProjectWeight()));
	    
		mockMvc.perform(
			post("/updateWeights").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + course.getId())
		);	
	}
	
	@WithMockUser(username = "zarras")
	@Test 
	void testCalculateGradesReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("course", Integer.toString(1));
	    
		mockMvc.perform(
			get("/calculateGrades").
			params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=1")
		);
	}
	
	@WithMockUser(username = "zarras")
	@Test 
	void testViewStatsReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("course", Integer.toString(1));
	    
		mockMvc.perform(
			get("/viewStats").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("course")).
			andExpect(model().attributeExists("stats")).
			andExpect(view().name("dashboard/viewStats")
		);		
	}
}