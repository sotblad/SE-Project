package se_project.service;

import java.util.List;

import se_project.entity.Course;
import se_project.entity.StudentRegistration;

public interface StudentRegistrationService {

	public List<StudentRegistration> findAll();
	
	public StudentRegistration findById(int theId);
	
	public void save(StudentRegistration theStudentRegistration);
	
	public void deleteById(int theId);
	
	public List<StudentRegistration> findByCourseId(int theId);

	public StudentRegistration findByStudentIdAndCourseId(int studentId, int courseId);

	public List<StudentRegistration> findByStudentId(int studentId);
	
	public void calculateGrades(Course course, List<StudentRegistration> studentsList);
	
	public void calculateStats(int courseId);
	
}
