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
import se_project.controller.StudentController;
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
class TestStudentController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	StudentController studentController;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCourseControllerIsNotNull() {
		Assertions.assertNotNull(studentController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}	
	
	@WithMockUser(username = "zarras")
	@Test 
	void testAddStudentRegistrationReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("course", Integer.toString(1));
		
		mockMvc.perform(
			get("/addStudent").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("courseId")).
			andExpect(model().attributeExists("student")).
			andExpect(view().name("dashboard/addStudent")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testPostStudentRegistrationReturnsPage() throws Exception {   	  
		StudentRegistration newStudentRegistration = new StudentRegistration(100, "testStudent", 2018, 7, 1, 0.0, 0.0, 0.0);
		
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(newStudentRegistration.getId()));
	    multiValueMap.add("studentId", Integer.toString(newStudentRegistration.getStudentId()));
	    multiValueMap.add("name", newStudentRegistration.getName());
	    multiValueMap.add("yearOfRegistration", Integer.toString(newStudentRegistration.getYearOfRegistration()));
	    multiValueMap.add("semester", Integer.toString(newStudentRegistration.getSemester()));
	    multiValueMap.add("courseId", Integer.toString(newStudentRegistration.getCourseId()));
	    multiValueMap.add("grade", Double.toString(newStudentRegistration.getGrade()));
	    multiValueMap.add("projectGrade", Double.toString(newStudentRegistration.getProjectGrade()));
	    multiValueMap.add("examGrade", Double.toString(newStudentRegistration.getExamGrade()));
	    
		mockMvc.perform(
			post("/postStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + newStudentRegistration.getCourseId())
		);
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testDeleteStudentRegistrationReturnsPage() throws Exception {
		List<StudentRegistration> studentRegistrations = studentRegistrationService.findAll();
	    StudentRegistration studentRegistration = studentRegistrations.get(studentRegistrations.size()-1);
	    System.out.println(studentRegistration);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("student", Integer.toString(studentRegistration.getStudentId()));
	    multiValueMap.add("course", Integer.toString(studentRegistration.getCourseId()));
	    
		mockMvc.perform(
			post("/removeStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + studentRegistration.getCourseId())
		);	
	}
	
	@WithMockUser(username = "zarras")
	@Test 
	void testEditStudentReturnsPage() throws Exception {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("student", Integer.toString(1));
	    multiValueMap.add("course", Integer.toString(1));
		
		mockMvc.perform(
			get("/editStudent").
			params(multiValueMap)).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("student")).
			andExpect(view().name("dashboard/editStudent")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateStudentSoftReturnsPage() throws Exception {
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
		multiValueMap.add("soft", Integer.toString(1));
	    
		mockMvc.perform(
			post("/updateStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + studentRegistration.getCourseId())
		);	
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testUpdateStudentHardReturnsPage() throws Exception {
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
			post("/updateStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:myCourses")
		);	
	}
}