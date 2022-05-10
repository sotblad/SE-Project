package se_project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se_project.controller.Singleton;
import se_project.controller.statistics.Statistic;
import se_project.dao.StudentRegistrationDAO;
import se_project.entity.Course;
import se_project.entity.StudentRegistration;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegistrationDAO studentRegistrationRepository;
	
	public StudentRegistrationServiceImpl() {
		super();
	}

	@Autowired
	public StudentRegistrationServiceImpl(StudentRegistrationDAO theStudentRegistrationRepository) {
		studentRegistrationRepository = theStudentRegistrationRepository;
	}
	
	@Override
	@Transactional
	public List<StudentRegistration> findAll() {
		return studentRegistrationRepository.findAll();
	}

	@Override
	@Transactional
	public StudentRegistration findById(int theId) {
		StudentRegistration result = studentRegistrationRepository.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find StudentRegistration");
		}
	}
	
	@Transactional
	public List<StudentRegistration> findByCourseId(int theId) {
		List<StudentRegistration> result = studentRegistrationRepository.findByCourseId(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find course");
		}
	}

	@Override
	@Transactional
	public void save(StudentRegistration theStudentRegistration) {
		studentRegistrationRepository.save(theStudentRegistration);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		studentRegistrationRepository.deleteById(theId);
	}

	@Override
	public StudentRegistration findByStudentIdAndCourseId(int studentId, int courseId) {
		return studentRegistrationRepository.findByStudentIdAndCourseId(studentId, courseId);
	}

	@Override
	public List<StudentRegistration> findByStudentId(int studentId) {
		return studentRegistrationRepository.findByStudentId(studentId);
	}

	@Override
	public void calculateGrades(Course course, List<StudentRegistration> studentsList) {
		double examWeight = course.getExamWeight()/100;
		double projectWeight = course.getProjectWeight()/100;
		
		for(int i = 0; i < studentsList.size(); i++) {
			double examGrade = studentsList.get(i).getExamGrade();
			double projectGrade = studentsList.get(i).getProjectGrade();
			double grade = examGrade*examWeight + projectGrade*projectWeight;

			studentsList.get(i).setGrade(grade);
			this.save(studentsList.get(i));
		}
	}

	@Override
	public void calculateStats(int courseId) {
		HashMap<String, Statistic> statistics = Singleton.supportedStatistics;
		
		if(this.findByCourseId(courseId).size() == 0)
			return;
		
		for(String stat : statistics.keySet()) {
			statistics.get(stat).execute(this.findByCourseId(courseId));
		}
	}
}






