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
import se_project.entity.Instructor;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.InstructorService;
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
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private InstructorService instructorService;

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
		if(courseService.findByInstructorUsername("testUsername").size() == 0) {
			Course course = new Course("testName", instructorService.findByUsername("testUsername"), "testSyllabus", 2018, 1, 10, 10);
			courseService.save(course);
		}
		StudentRegistration newStudentRegistration = new StudentRegistration(100, "testStudent", 2018, 7, courseService.findByInstructorUsername("testUsername").get(0), 0.0, 0.0, 0.0);
		
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(newStudentRegistration.getId()));
	    multiValueMap.add("studentId", Integer.toString(newStudentRegistration.getStudentId()));
	    multiValueMap.add("name", newStudentRegistration.getName());
	    multiValueMap.add("yearOfRegistration", Integer.toString(newStudentRegistration.getYearOfRegistration()));
	    multiValueMap.add("semester", Integer.toString(newStudentRegistration.getSemester()));
	    multiValueMap.add("course.id", Integer.toString(newStudentRegistration.getCourse().getId()));
	    multiValueMap.add("course.name", newStudentRegistration.getCourse().getName());
	    multiValueMap.add("course.instructor", Integer.toString(newStudentRegistration.getCourse().getInstructor().getId()));
	    multiValueMap.add("course.syllabus", newStudentRegistration.getCourse().getSyllabus());
	    multiValueMap.add("course.year", Integer.toString(newStudentRegistration.getCourse().getYear()));
	    multiValueMap.add("course.semester", Integer.toString(newStudentRegistration.getCourse().getSemester()));
	    multiValueMap.add("course.examWeight", Double.toString(newStudentRegistration.getCourse().getExamWeight()));
	    multiValueMap.add("course.projectWeight", Double.toString(newStudentRegistration.getCourse().getProjectWeight()));
	    multiValueMap.add("grade", Double.toString(newStudentRegistration.getGrade()));
	    multiValueMap.add("projectGrade", Double.toString(newStudentRegistration.getProjectGrade()));
	    multiValueMap.add("examGrade", Double.toString(newStudentRegistration.getExamGrade()));
	    
		mockMvc.perform(
			post("/postStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + newStudentRegistration.getCourse().getId())
		);
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testDeleteStudentRegistrationReturnsPage() throws Exception {
		List<StudentRegistration> studentRegistrations = studentRegistrationService.findAll();
	    StudentRegistration studentRegistration = studentRegistrations.get(studentRegistrations.size()-1);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("student", Integer.toString(studentRegistration.getStudentId()));
	    multiValueMap.add("course", Integer.toString(studentRegistration.getCourse().getId()));
	    
		mockMvc.perform(
			post("/removeStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + studentRegistration.getCourse().getId())
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
	    multiValueMap.add("course.id", Integer.toString(studentRegistration.getCourse().getId()));
	    multiValueMap.add("course.name", studentRegistration.getCourse().getName());
	    multiValueMap.add("course.instructor", Integer.toString(studentRegistration.getCourse().getInstructor().getId()));
	    multiValueMap.add("course.syllabus", studentRegistration.getCourse().getSyllabus());
	    multiValueMap.add("course.year", Integer.toString(studentRegistration.getCourse().getYear()));
	    multiValueMap.add("course.semester", Integer.toString(studentRegistration.getCourse().getSemester()));
	    multiValueMap.add("course.examWeight", Double.toString(studentRegistration.getCourse().getExamWeight()));
	    multiValueMap.add("course.projectWeight", Double.toString(studentRegistration.getCourse().getProjectWeight()));
	    multiValueMap.add("grade", Double.toString(studentRegistration.getGrade()));
	    multiValueMap.add("projectGrade", Double.toString(studentRegistration.getProjectGrade()));
	    multiValueMap.add("examGrade", Double.toString(studentRegistration.getExamGrade()));
		multiValueMap.add("soft", Integer.toString(1));
	    
		mockMvc.perform(
			post("/updateStudent").
		    params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/viewCourse?course=" + studentRegistration.getCourse().getId())
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
	    multiValueMap.add("courseId", Integer.toString(studentRegistration.getCourse().getId()));
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