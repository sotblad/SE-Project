package se_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se_project.entity.Instructor;

@Repository
public interface InstructorDAO extends JpaRepository<Instructor, Integer> {
	
	public Instructor findById(int theId);
	
	public Instructor findByUsername(String theUsername);
		
}
