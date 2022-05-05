package se_project.service;

import se_project.entity.Instructor;

public interface InstructorService {
	
	public Instructor findById(int theId);
	
	public void save(Instructor theCourse);
	
}
