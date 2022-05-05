package se_project.service;

import java.util.List;

import se_project.entity.StudentRegistration;

public interface StudentRegistrationService {

	public List<StudentRegistration> findAll();
	
	public StudentRegistration findById(int theId);
	
	public void save(StudentRegistration theStudentRegistration);
	
	public void deleteById(int theId);
	
	public List<StudentRegistration> findByCourseId(int theId);
	
}
