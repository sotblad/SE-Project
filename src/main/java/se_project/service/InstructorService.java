package se_project.service;

import se_project.entity.Instructor;

public interface InstructorService {
	
	public Instructor findByUsername(String theUsername);
	
	public void save(Instructor authority);
	
	public void deleteByUsername(String theUsername);
	
}
