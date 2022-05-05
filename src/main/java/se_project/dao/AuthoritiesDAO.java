package se_project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se_project.entity.Authorities;

@Repository
public interface AuthoritiesDAO extends JpaRepository<Authorities, Integer> {
	
	public List<Authorities> findByUsername(String theUsername);
		
}
