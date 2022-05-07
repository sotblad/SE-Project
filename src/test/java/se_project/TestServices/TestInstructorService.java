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
import se_project.service.AuthoritiesService;
import se_project.service.InstructorService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestInstructorService {
	@Autowired 
	InstructorService instructorService;
		
	@Test
	void testInstructorDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(instructorService);
	}
	
	@Test
	void testFindByIdReturnsInstructor() {
		Instructor storedInstructor = instructorService.findById(1);
		Assertions.assertNotNull(storedInstructor);
		Assertions.assertEquals("zarras", storedInstructor.getUsername());
	}
	
	@Test
	void testSaveInstructor() {
		Instructor newInstructor = new Instructor("Test Instructor", "testInstructor", "$2a$12$.PQuhJBs4B4amRdEc9w8gu83H0mNmkJ4Io3cVYlklMH3jwZJvWpLG", true);
		instructorService.save(newInstructor);
		Instructor storedInstructor = instructorService.findById(newInstructor.getId());
		Assertions.assertEquals("testInstructor", storedInstructor.getUsername());
		instructorService.deleteById(storedInstructor.getId());
	}
	
	@Test
	void testDeleteInstructor() {
		Instructor newInstructor = new Instructor("Test Instructor", "testInstructor", "$2a$12$.PQuhJBs4B4amRdEc9w8gu83H0mNmkJ4Io3cVYlklMH3jwZJvWpLG", true);
		instructorService.save(newInstructor);
		Instructor storedInstructor = instructorService.findById(newInstructor.getId());
		Assertions.assertEquals("testInstructor", storedInstructor.getUsername());
		instructorService.deleteById(storedInstructor.getId());
		try {
			storedInstructor = instructorService.findById(newInstructor.getId());
		}catch (Exception e) {
			Assertions.assertEquals("Did not find instructor", e.getMessage());
		}
	}
	
	@Test
	void testFindByUsernameReturnsInstructor() {
		Instructor storedInstructor = instructorService.findByUsername("tsiatouxas");
		Assertions.assertNotNull(storedInstructor);
		Assertions.assertEquals(2, storedInstructor.getId());
	}
}
