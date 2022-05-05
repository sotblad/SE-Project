package se_project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.entity.Authorities;
import se_project.entity.Course;
import se_project.entity.Instructor;
import se_project.entity.StudentRegistration;
import se_project.service.AuthoritiesService;
import se_project.service.CourseService;
import se_project.service.InstructorService;
import se_project.service.StudentRegistrationService;

@Controller
public class LoginController {
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	public LoginController(InstructorService theInstructorService, AuthoritiesService theAuthoritiesService) {
		instructorService = theInstructorService;
		authoritiesService = theAuthoritiesService;
	}
	
	@GetMapping("/login")    
	public String login(Model model) {
	    return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {	
		model.addAttribute("instructor", new Instructor());
	    return "register";
	}
	
	@PostMapping("/postLogin")
	public String postLogin(Model model) {
	    return "redirect:/login";
	}
	
	@PostMapping("/postRegister")
	public String postRegister(@ModelAttribute("instructor")Instructor instructor, Model model) {
		Authorities authority = new Authorities(instructor.getUsername(), "INSTRUCTOR");
	    String encodedPassword  = passwordEncoder.encode(instructor.getPassword());
	    instructor.setPassword(encodedPassword);
	    instructor.setEnabled(true);
	    instructorService.save(instructor);
	    authoritiesService.save(authority);
	    
	    return "redirect:/login";
	}

}
