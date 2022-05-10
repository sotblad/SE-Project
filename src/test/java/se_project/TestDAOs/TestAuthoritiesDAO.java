package se_project.TestDAOs;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import se_project.dao.AuthoritiesDAO;
import se_project.entity.Authorities;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class TestAuthoritiesDAO {
	@Autowired 
	AuthoritiesDAO authoritiesDAO;
		
	@Test
	void testAuthoritiesDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(authoritiesDAO);
	}
	
	@Test
	void testFindByUsernameReturnsAuthorities() {
		List<Authorities> storedAuthorities = authoritiesDAO.findByUsername("zarras");
		Assertions.assertNotNull(storedAuthorities);
		Assertions.assertEquals("INSTRUCTOR", storedAuthorities.get(0).getAuthority());
	}
}
