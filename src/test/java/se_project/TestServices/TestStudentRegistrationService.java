package se_project.TestServices;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.AuthoritiesDAO;
import se_project.entity.Authorities;
import se_project.entity.Course;
import se_project.entity.Instructor;
import se_project.entity.StudentRegistration;
import se_project.service.AuthoritiesService;
import se_project.service.CourseService;
import se_project.service.InstructorService;
import se_project.service.StudentRegistrationService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestStudentRegistrationService {
	@Autowired 
	StudentRegistrationService studentRegistrationService;
	
	@Autowired 
	CourseService courseService;
		
	@Test
	void testStudentRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(studentRegistrationService);
	}
	
	@Test
	void testFindAllReturnsCourses() {
		List<StudentRegistration> storedStudentRegistrations = studentRegistrationService.findAll();
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("sotblad", storedStudentRegistrations.get(0).getName());
	}
	
	@Test
	void testFindByIdReturnsStudentRegistration() {
		StudentRegistration storedStudentRegistration = studentRegistrationService.findById(1);
		Assertions.assertNotNull(storedStudentRegistration);
		Assertions.assertEquals("sotblad", storedStudentRegistration.getName());
	}
	
	@Test
	void testFindByInstructorReturnsStudentRegistration() {
		List<StudentRegistration> storedStudentRegistrations = studentRegistrationService.findByCourseId(1);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("stratis", storedStudentRegistrations.get(1).getName());
	}
	
	@Test
	void testFindByStudentIdAndCourseIdReturnsStudentRegistration() {
		StudentRegistration storedStudentRegistrations = studentRegistrationService.findByStudentIdAndCourseId(3,3);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("jim", storedStudentRegistrations.getName());
	}
	
	@Test
	void testFindByStudentIdReturnsStudentRegistration() {
		List<StudentRegistration> storedStudentRegistrations = studentRegistrationService.findByStudentId(2);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("stratis", storedStudentRegistrations.get(1).getName());
		Assertions.assertEquals(3, storedStudentRegistrations.get(1).getCourse().getId());
	}
	
	@Test
	void testSaveStudentRegistration() {
		StudentRegistration newStudentRegistration = new StudentRegistration(100, "testStudent", 2018, 7, courseService.findByInstructorUsername("testUsername").get(0), 0.0, 0.0, 0.0);
		studentRegistrationService.save(newStudentRegistration);
		StudentRegistration storedStudentRegistration = studentRegistrationService.findById(newStudentRegistration.getId());
		Assertions.assertEquals("testStudent", storedStudentRegistration.getName());
		studentRegistrationService.deleteById(storedStudentRegistration.getId());
	}
	
	@Test
	void testDeleteStudentRegistration() {
		StudentRegistration newStudentRegistration = new StudentRegistration(100, "testStudent", 2018, 7, courseService.findByInstructorUsername("testUsername").get(0), 0.0, 0.0, 0.0);
		studentRegistrationService.save(newStudentRegistration);
		StudentRegistration storedStudentRegistration = studentRegistrationService.findById(newStudentRegistration.getId());
		Assertions.assertEquals("testStudent", storedStudentRegistration.getName());
		try {
			storedStudentRegistration = studentRegistrationService.findById(newStudentRegistration.getId());
		}catch (Exception e) {
			Assertions.assertEquals("Did not find StudentRegistration", e.getMessage());
		}
	}
}
