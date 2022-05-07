package se_project.TestServices;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.AuthoritiesDAO;
import se_project.entity.Authorities;
import se_project.entity.Course;
import se_project.entity.StudentRegistration;
import se_project.service.AuthoritiesService;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestAuthoritiesService {
	@Autowired 
	AuthoritiesService authoritiesService;
		
	@Test
	void testAuthoritiesDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(authoritiesService);
	}
	
	@Test
	void testFindByUsernameReturnsAuthorities() {
		List<Authorities> storedAuthorities = authoritiesService.findByUsername("zarras");
		Assertions.assertNotNull(storedAuthorities);
		Assertions.assertEquals("INSTRUCTOR", storedAuthorities.get(0).getAuthority());
	}
	
	@Test
	void testSaveAuthority() {
		Authorities newAuthority = new Authorities("testUser", "testRole");
		authoritiesService.save(newAuthority);
		List<Authorities> storedAuthority = authoritiesService.findByUsername(newAuthority.getUsername());
		Assertions.assertEquals("testUser", storedAuthority.get(0).getUsername());
		authoritiesService.deleteByUsername(storedAuthority.get(0).getUsername());
	}
	
	@Test
	void testDeleteAuthority() {
		Authorities newAuthority = new Authorities("testUser", "testRole");
		authoritiesService.save(newAuthority);
		List<Authorities> storedAuthority = authoritiesService.findByUsername(newAuthority.getUsername());
		Assertions.assertEquals("testUser", storedAuthority.get(0).getUsername());
		authoritiesService.deleteByUsername(storedAuthority.get(0).getUsername());
		try {
			storedAuthority = authoritiesService.findByUsername(storedAuthority.get(0).getUsername());
		}catch (Exception e) {
			Assertions.assertEquals("Did not find username", e.getMessage());
		}
	}
}
