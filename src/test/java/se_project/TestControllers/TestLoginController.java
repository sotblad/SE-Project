package se_project.TestControllers;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import se_project.controller.LoginController;
import se_project.entity.Instructor;
import se_project.service.InstructorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class TestLoginController {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	LoginController loginController;
	
	@Autowired 
	InstructorService instructorService;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testCourseControllerIsNotNull() {
		Assertions.assertNotNull(loginController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}	
	
	@WithMockUser(username = "zarras")
	@Test 
	void testDashboardReturnsPage() throws Exception {
		mockMvc.perform(
			get("/")).
			andExpect(status().isOk()).
			andExpect(model().attributeExists("instructor")).
			andExpect(view().name("dashboard/dashboard")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testLoginReturnsPage() throws Exception {
		mockMvc.perform(
			get("/login")).
			andExpect(status().isOk()).
			andExpect(view().name("login")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testRegisterReturnsPage() throws Exception {
		mockMvc.perform(
			get("/register")).
			andExpect(status().isOk()).
			andExpect(view().name("register")
		);		
	}
	
	@WithMockUser(value = "zarras")
	@Test 
	void testPostRegisterReturnsPage() throws Exception {
		Instructor testInstructor = new Instructor("testFullname", "testUsername", "$2a$12$RoKPilIq4OiHxq.iiude7e7x4mMmPGdK0rnymjJKB9YqWhv1hLgsq", true);
	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(testInstructor.getId()));
	    multiValueMap.add("fullname", testInstructor.getFullname());
	    multiValueMap.add("username", testInstructor.getUsername());
	    multiValueMap.add("password", testInstructor.getPassword());
	    multiValueMap.add("enabled", Boolean.toString(testInstructor.isEnabled()));
	    instructorService.deleteByUsername("testUsername");
	    
		mockMvc.perform(
			post("/postRegister").
			params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/login")
		);
	}
}