package se_project.TestServices;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.entity.Course;
import se_project.service.CourseService;
import se_project.service.InstructorService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestCourseService {
	@Autowired 
	CourseService courseService;
	
	@Autowired 
	InstructorService instructorService;
		
	@Test
	void testCourseDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(courseService);
	}
	
	@Test
	void testFindAllReturnsCourses() {
		List<Course> storedCourses = courseService.findAll();
		Assertions.assertNotNull(storedCourses);
		Assertions.assertEquals("SE", storedCourses.get(0).getName());
	}
	
	@Test
	void testFindByIdReturnsCourse() {
		Course storedCourse = courseService.findById(1);
		Assertions.assertNotNull(storedCourse);
		Assertions.assertEquals("SE", storedCourse.getName());
	}
	
	@Test
	void testSaveCourse() {
		Course newCourse = new Course("TestCourse", instructorService.findByUsername("zarras"), "TestSyllabus", 2018, 7, 0.0, 0.0);
		courseService.save(newCourse);
		Course storedCourse = courseService.findById(newCourse.getId());
		Assertions.assertEquals("TestCourse", storedCourse.getName());
		courseService.deleteById(storedCourse.getId());
	}
	
	@Test
	void testDeleteCourse() {
		Course newCourse = new Course("TestCourse", instructorService.findByUsername("zarras"), "TestSyllabus", 2018, 7, 0.0, 0.0);
		courseService.save(newCourse);
		Course storedCourse = courseService.findById(newCourse.getId());
		Assertions.assertEquals("TestCourse", storedCourse.getName());
		courseService.deleteById(storedCourse.getId());
		try {
			storedCourse = courseService.findById(newCourse.getId());
		}catch (Exception e) {
			Assertions.assertEquals("Did not find course id", e.getMessage());
		}
	}
	
	@Test
	void testFindByInstructorReturnsCourse() {
		List<Course> storedCourses = courseService.findByInstructorUsername("tsiatouxas");
		Assertions.assertNotNull(storedCourses);
		Assertions.assertEquals("VHDL", storedCourses.get(0).getName());
	}
}
