package se_project.TestDAOs;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.InstructorDAO;
import se_project.entity.Instructor;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestInstructorDAO {
	@Autowired 
	InstructorDAO instructorDAO;
		
	@Test
	void testInstructorDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(instructorDAO);
	}
	
	@Test
	void testFindByIdReturnsInstructor() {
		Instructor storedInstructor = instructorDAO.findById(1);
		Assertions.assertNotNull(storedInstructor);
		Assertions.assertEquals("zarras", storedInstructor.getUsername());
	}
	
	@Test
	void testFindByUsernameReturnsInstructor() {
		Instructor storedInstructor = instructorDAO.findByUsername("tsiatouxas");
		Assertions.assertNotNull(storedInstructor);
		Assertions.assertEquals(2, storedInstructor.getId());
	}
}
