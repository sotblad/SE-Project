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
	public Instructor findByUsername(String theUsername) {
		Instructor result = instructorRepository.findByUsername(theUsername);
				
		return result;
	}
	
	@Override
	@Transactional
	public void deleteByUsername(String theUsername) {
		instructorRepository.deleteByUsername(theUsername);
	}
	
	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		instructorRepository.save(theInstructor);
	}
}






