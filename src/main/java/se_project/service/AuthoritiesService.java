package se_project.service;

import java.util.List;

import se_project.entity.Authorities;

public interface AuthoritiesService {
	
	public List<Authorities> findByUsername(String theUsername);
	
	public void save(Authorities theAuthorities);
	
}
