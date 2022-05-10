package se_project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se_project.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
	
	public Course findById(int theId);
	
	public List<Course> findByInstructorUsername(String name);
		
}
