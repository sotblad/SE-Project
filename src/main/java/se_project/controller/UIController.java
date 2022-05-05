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
import se_project.service.StudentRegistrationService;

@Controller
public class UIController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	public UIController(StudentRegistrationService theStudentRegistrationService, CourseService theCourseService) {
		courseService = theCourseService;
	}
	
	@GetMapping("")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
	    model.addAttribute("instructor", authentication.getName());

	    return "dashboard/dashboard";
	}

	@GetMapping("/myCourses")
	public String myCourses(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		List<Course> listCourses = courseService.findByInstructor(authentication.getName());

	    model.addAttribute("listCourses", listCourses);

	    return "dashboard/coursesList";
	}
	
	@GetMapping("/addCourse")
	public String addCourse(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		
	    return "dashboard/addCourse";
	}
	
	@PostMapping("/postCourse")
	public String postCourse(@ModelAttribute("course")Course course, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		course.setInstructor(authentication.getName());

		courseService.save(course);
		
	    return "redirect:myCourses";
	}
	
	@PostMapping("/deleteCourse")
	public String deleteCourse(@ModelAttribute("course")int courseId, Model model) {
		
		courseService.deleteById(courseId);

	    return "redirect:myCourses";
	}
	
	@GetMapping("/calculateGrades")
	public String calculateGrades(@ModelAttribute("course")int courseId, Model model) {
		double examWeight = 0.7; // set to weight apo front end -> field sto course?
		double projectWeight = 1-examWeight;
		List<StudentRegistration> studentsList = studentRegistrationService.findByCourseId(courseId);
		
		for(int i = 0; i < studentsList.size(); i++) {
			double examGrade = studentsList.get(i).getExamGrade();
			double projectGrade = studentsList.get(i).getProjectGrade();
			double grade = examGrade*examWeight + projectGrade*projectWeight;

			studentsList.get(i).setGrade(grade);
			studentRegistrationService.save(studentsList.get(i));
		}

	    return "redirect:myCourses";
	}
	
	@GetMapping("/viewCourse")
	public String viewCourse(@ModelAttribute("course")int courseId, Model model) {
		Course course = courseService.findById(courseId);
		List<StudentRegistration>  students = studentRegistrationService.findByCourseId(courseId);
		model.addAttribute("course",course);
		model.addAttribute("students",students);
	    return "dashboard/viewCourse";
	}
	
	@GetMapping("/addStudent")
	public String addStudent(@ModelAttribute("course")int courseId, Model model) {
		StudentRegistration student = new StudentRegistration();
		model.addAttribute("courseId", courseId);
		model.addAttribute("student", student);
		
	    return "dashboard/addStudent";
	}
	
	@PostMapping("/postStudent")
	public String postStudent(@ModelAttribute("student")StudentRegistration student, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(student.toString());
		studentRegistrationService.save(student);
		System.out.println(model);
		
		List<StudentRegistration> studentsList = studentRegistrationService.findByCourseId(student.getCourseId());
		
	    return "redirect:/dashboard/viewCourse?course="+student.getCourseId();
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("course")Course course, Model model) {
		System.out.println(course.toString());
		Course tempCourse = courseService.findById(course.getId());
		tempCourse.setName(course.getName());
		tempCourse.setSyllabus(course.getSyllabus());
		tempCourse.setYear(course.getYear());
		tempCourse.setSemester(course.getSemester());
		courseService.save(tempCourse);
		
		
	    return "redirect:myCourses";
	}
	
	
	
	
}






