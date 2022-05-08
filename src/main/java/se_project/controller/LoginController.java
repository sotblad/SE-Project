package se_project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.entity.Authorities;
import se_project.entity.Instructor;
import se_project.service.AuthoritiesService;
import se_project.service.InstructorService;

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
	
	@GetMapping("")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Instructor instructor = instructorService.findByUsername(authentication.getName());
		
	    model.addAttribute("instructor", instructor.getFullname());

	    return "dashboard/dashboard";
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
