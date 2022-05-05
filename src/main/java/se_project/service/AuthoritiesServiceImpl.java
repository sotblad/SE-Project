package se_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se_project.dao.AuthoritiesDAO;
import se_project.dao.InstructorDAO;
import se_project.entity.Authorities;
import se_project.entity.Instructor;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

	@Autowired
	private AuthoritiesDAO authoritiesRepository;
	
	public AuthoritiesServiceImpl() {
		super();
	}

	@Autowired
	public AuthoritiesServiceImpl(AuthoritiesDAO theAuthoritiesRepository) {
		authoritiesRepository = theAuthoritiesRepository;
	}

	@Override
	@Transactional
	public List<Authorities> findByUsername(String theUsername) {
		List<Authorities> result = authoritiesRepository.findByUsername(theUsername);
				
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find username - " + theUsername);
		}
	}
	
	@Override
	@Transactional
	public void save(Authorities theAuthorities) {
		authoritiesRepository.save(theAuthorities);
	}
}





