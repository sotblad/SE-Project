package se_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.entity.Course;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.StudentRegistrationService;

@Controller
public class GradesController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	public GradesController(StudentRegistrationService theStudentRegistrationService, CourseService theCourseService) {
		courseService = theCourseService;
		studentRegistrationService = theStudentRegistrationService;
	}
	
	//US10
	@GetMapping("/editGrades")
	public String editGrades(@ModelAttribute("student") int studentId, @ModelAttribute("course") int courseId, Model model) {
		StudentRegistration student = studentRegistrationService.findByStudentIdAndCourseId(studentId, courseId);

		model.addAttribute("student", student);
		return "dashboard/editGrades";
	}
	
	@PostMapping("/updateGrades")
	public String updateGrades(@ModelAttribute("student")StudentRegistration student, Model model) {
		studentRegistrationService.save(student);
		
		return "redirect:/viewCourse?course=" + student.getCourseId(); 
	}
	
	@GetMapping("/editWeights")
	public String editWeights(@ModelAttribute("course") int courseId, Model model) {
		Course course = courseService.findById(courseId);

		model.addAttribute("course", course);
		return "dashboard/editWeights";
	}
	
	@PostMapping("/updateWeights")
	public String updateWeights(@ModelAttribute("course")Course course, Model model) {
		courseService.save(course);
		
		return "redirect:/viewCourse?course=" + course.getId(); 
	}
	
	//US11
	@GetMapping("/calculateGrades")
	public String calculateGrades(@ModelAttribute("course")int courseId, Model model) {
		List<StudentRegistration> studentsList = studentRegistrationService.findByCourseId(courseId);
		Course course = courseService.findById(courseId);
		
		studentRegistrationService.calculateGrades(course, studentsList);

		return "redirect:/viewCourse?course=" + course.getId(); 
	}
	
	//US12
	@GetMapping("/viewStats")
	public String viewStats(@ModelAttribute("course") int courseId, Model model) {
		Course course = courseService.findById(courseId);
		
		studentRegistrationService.calculateStats(courseId);

		model.addAttribute("course", course);
		model.addAttribute("stats", Singleton.statistics);

		return "dashboard/viewStats";
	}
}






