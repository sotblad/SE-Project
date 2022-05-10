package se_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se_project.dao.AuthoritiesDAO;
import se_project.entity.Authorities;

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
			throw new RuntimeException("Did not find username");
		}
	}
	
	@Override
	@Transactional
	public void save(Authorities theAuthorities) {
		authoritiesRepository.save(theAuthorities);
	}
	
	@Override
	@Transactional
	public void deleteByUsername(String theUsername) {
		authoritiesRepository.deleteByUsername(theUsername);
	}
}






