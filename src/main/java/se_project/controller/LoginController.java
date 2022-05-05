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

import se_project.entity.Course;
import se_project.entity.Instructor;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.InstructorService;
import se_project.service.StudentRegistrationService;

@Controller
public class LoginController {
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private InstructorService instructorService;
	
	public LoginController(InstructorService theInstructorService) {
		instructorService = theInstructorService;
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
	    System.out.println("ZS");
	    return "redirect:/login";
	}
	
	@PostMapping("/postRegister")
	public String postRegister(@ModelAttribute("instructor")Instructor instructor, Model model) {
	    String encodedPassword  = passwordEncoder.encode(instructor.getPassword());
	    instructor.setPassword(encodedPassword);
	    instructorService.save(instructor);
	    return "redirect:/login";
	}

}
