package se_project.service;

import java.util.List;

import se_project.entity.Course;
import se_project.entity.Instructor;

public interface CourseService {

	public List<Course> findAll();
	
	public Course findById(int theId);
	
	public void save(Course theCourse);
	
	public void deleteById(int theId);
	
	public List<Course> findByInstructor(String name);
	
}
