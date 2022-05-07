package se_project.TestDAOs;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.CourseDAO;
import se_project.entity.Course;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseDAO {
	@Autowired 
	CourseDAO courseDAO;
		
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseDAO);
	}
	
	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseDAO.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("SE", storedCourse.getName());
	}
	
	@Test
	void testFindByInstructorReturnsCourse() {
		List<Course> storedCourses = courseDAO.findByInstructor("tsiatouxas");
		Assertions.assertNotNull(storedCourses);
		Assertions.assertEquals("VHDL", storedCourses.get(0).getName());
	}
}
