package se_project.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.entity.Course;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.InstructorService;
import se_project.service.StudentRegistrationService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	@Autowired
	private InstructorService instructorService;
	
	public CourseController(StudentRegistrationService theStudentRegistrationService, CourseService theCourseService) {
		courseService = theCourseService;
		studentRegistrationService = theStudentRegistrationService;
	}

	// US2
	@GetMapping("/myCourses")
	public String myCourses(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<Course> listCourses = courseService.findByInstructorUsername(authentication.getName());

	    model.addAttribute("listCourses", listCourses);

	    return "dashboard/coursesList";
	}
	
	//US3
	@GetMapping("/addCourse")
	public String addCourse(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Course course = new Course();
		course.setInstructor(instructorService.findByUsername(authentication.getName()));
		
		model.addAttribute("course", course);
		
	    return "dashboard/addCourse";
	}
	
	@PostMapping("/postCourse")
	public String postCourse(@ModelAttribute("course")Course course, Model model) {
		courseService.save(course);
		
	    return "redirect:myCourses";
	}
	
	//US4
	@PostMapping("/deleteCourse")
	public String deleteCourse(@ModelAttribute("course")int courseId, Model model) {
		courseService.deleteById(courseId);

	    return "redirect:myCourses";
	}
	
	//US5
	@GetMapping("/editCourse")
	public String editCourse(@ModelAttribute("course") int courseId, Model model) {
		Course course = courseService.findById(courseId);

		model.addAttribute("course", course);
		
		return "dashboard/editCourse";
	}
	
	@PostMapping("/updateCourse")
	public String updateCourse(@ModelAttribute("course")Course course, Model model) {
		courseService.save(course);
		
		return "redirect:/myCourses";
	}
	
	//US6
	@GetMapping("/viewCourse")
	public String viewCourse(@ModelAttribute("course")int courseId, Model model) {
		List<StudentRegistration> students = studentRegistrationService.findByCourseId(courseId);
		Course course = courseService.findById(courseId);
		
		model.addAttribute("course", course);
		model.addAttribute("students", students);
		
	    return "dashboard/viewCourse";
	}	
}






