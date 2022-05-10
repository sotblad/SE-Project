package se_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se_project.dao.InstructorDAO;
import se_project.entity.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorDAO instructorRepository;
	
	public InstructorServiceImpl() {
		super();
	}

	@Autowired
	public InstructorServiceImpl(InstructorDAO theInstructorRepository) {
		instructorRepository = theInstructorRepository;
	}

	@Override
	@Transactional
	public Instructor findById(int theId) {
		Instructor result = instructorRepository.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find instructor");
		}
	}
	
	@Override
	@Transactional
	public Instructor findByUsername(String theUsername) {
		Instructor result = instructorRepository.findByUsername(theUsername);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find instructor");
		}
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		instructorRepository.deleteById(theId);
	}
	
	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		instructorRepository.save(theInstructor);
	}
}






