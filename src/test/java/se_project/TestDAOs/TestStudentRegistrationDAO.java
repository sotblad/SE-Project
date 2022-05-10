package se_project.TestDAOs;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.StudentRegistrationDAO;
import se_project.entity.StudentRegistration;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestStudentRegistrationDAO {
	@Autowired 
	StudentRegistrationDAO studentRegistrationDAO;
		
	@Test
	void testStudentRegistrationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(studentRegistrationDAO);
	}
	
	@Test
	void testFindByIdReturnsStudentRegistration() {
		StudentRegistration storedStudentRegistration = studentRegistrationDAO.findById(1);
		Assertions.assertNotNull(storedStudentRegistration);
		Assertions.assertEquals("sotblad", storedStudentRegistration.getName());
	}
	
	@Test
	void testFindByInstructorReturnsStudentRegistration() {
		List<StudentRegistration> storedStudentRegistrations = studentRegistrationDAO.findByCourseId(1);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("stratis", storedStudentRegistrations.get(1).getName());
	}
	
	@Test
	void testFindByStudentIdAndCourseIdReturnsStudentRegistration() {
		StudentRegistration storedStudentRegistrations = studentRegistrationDAO.findByStudentIdAndCourseId(3,1);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("jim", storedStudentRegistrations.getName());
	}
	
	@Test
	void testFindByStudentIdReturnsStudentRegistration() {
		List<StudentRegistration> storedStudentRegistrations = studentRegistrationDAO.findByStudentId(2);
		Assertions.assertNotNull(storedStudentRegistrations);
		Assertions.assertEquals("stratis", storedStudentRegistrations.get(1).getName());
		Assertions.assertEquals(2, storedStudentRegistrations.get(1).getCourseId());
	}
}
