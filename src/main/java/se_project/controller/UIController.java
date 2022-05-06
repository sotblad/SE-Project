package se_project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.controller.statistics.Statistic;
import se_project.entity.Course;
import se_project.entity.Instructor;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;
import se_project.service.CourseService;
import se_project.service.InstructorService;
import se_project.service.StudentRegistrationService;

@Controller
public class UIController {
	
	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	public UIController(StudentRegistrationService theStudentRegistrationService, CourseService theCourseService, InstructorService theInstructorService) {
		courseService = theCourseService;
		instructorService = theInstructorService;
	}
	
	@GetMapping("")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Instructor instructor = instructorService.findByUsername(authentication.getName());
		
	    model.addAttribute("instructor", instructor.getFullname());

	    return "dashboard/dashboard";
	}

	// US2
	@GetMapping("/myCourses")
	public String myCourses(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		List<Course> listCourses = courseService.findByInstructor(authentication.getName());

	    model.addAttribute("listCourses", listCourses);

	    return "dashboard/coursesList";
	}
	
	//US3 -- TODO id?
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

		model.addAttribute("course",course);
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
		Course course = courseService.findById(courseId);
		List<StudentRegistration> students = studentRegistrationService.findByCourseId(courseId);
		model.addAttribute("course",course);
		model.addAttribute("students",students);
	    return "dashboard/viewCourse";
	}
	
	
	//US7
	@GetMapping("/addStudent")
	public String addStudent(@ModelAttribute("course")int courseId, Model model) {
		StudentRegistration student = new StudentRegistration();
		model.addAttribute("courseId", courseId);
		model.addAttribute("student", student);
		
	    return "dashboard/addStudent";
	}
	
	@PostMapping("/postStudent")
	public String postStudent(@ModelAttribute("student")StudentRegistration studentRegistration, Model model) {
		int registrationId = studentRegistration.getStudentId();
		
		List<StudentRegistration> studentsList = studentRegistrationService.findByCourseId(studentRegistration.getCourseId());
		for(StudentRegistration i : studentsList){
			if(i.getStudentId() == registrationId || registrationId < 0)
				return "error";
		}

		studentRegistrationService.save(studentRegistration);
		
	    return "redirect:/viewCourse?course="+studentRegistration.getCourseId();
	}
	
	//US8
	@PostMapping("/removeStudent")
	public String removeStudent(@ModelAttribute("student")int studentId, @ModelAttribute("course")int courseId, Model model) {
			
		StudentRegistration result = studentRegistrationService.findByStudentIdAndCourseId(studentId, courseId);
		studentRegistrationService.deleteById(result.getId());

		return "redirect:/viewCourse?course="+courseId;
	}
	
	//US9
	@GetMapping("/editStudent")
	public String editStudent(@ModelAttribute("student") int studentId, @ModelAttribute("course") int courseId, Model model) {
		StudentRegistration student = studentRegistrationService.findByStudentIdAndCourseId(studentId, courseId);

		model.addAttribute("student", student);
		return "dashboard/editStudent";
	}
	
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("student")StudentRegistration student, @RequestParam(required=false, value = "soft") String soft, Model model) {
		if(soft != null) {
			studentRegistrationService.save(student);
			return "redirect:/viewCourse?course="+student.getCourseId(); 
		}
		
		List<StudentRegistration> result = studentRegistrationService.findByStudentId(student.getStudentId());
		
		for(StudentRegistration i : result) {
			i.setStudentId(student.getStudentId());
			i.setName(student.getName());
			i.setYearOfRegistration(student.getYearOfRegistration());
			i.setSemester(student.getSemester());
			studentRegistrationService.save(i);
		}
		
	    return "redirect:myCourses";
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
		
		return "redirect:/viewCourse?course="+student.getCourseId(); 
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
		
		return "redirect:/viewCourse?course="+course.getId(); 
	}
	
	//US11
	@GetMapping("/calculateGrades")
	public String calculateGrades(@ModelAttribute("course")int courseId, Model model) {
		Course course = courseService.findById(courseId);
		double examWeight = course.getExamWeight()/100; // set to weight apo front end -> field sto course?
		double projectWeight = course.getProjectWeight()/100;
		List<StudentRegistration> studentsList = studentRegistrationService.findByCourseId(courseId);

		for(int i = 0; i < studentsList.size(); i++) {
			double examGrade = studentsList.get(i).getExamGrade();
			double projectGrade = studentsList.get(i).getProjectGrade();
			double grade = examGrade*examWeight + projectGrade*projectWeight;

			studentsList.get(i).setGrade(grade);
			studentRegistrationService.save(studentsList.get(i));
		}

		return "redirect:/viewCourse?course="+course.getId(); 
	}
	
	@GetMapping("/viewStats")
	public String viewStats(@ModelAttribute("course") int courseId, Model model) {
		Course course = courseService.findById(courseId);
		
		HashMap<String, Statistic> statistics = Singleton.supportedStatistics;
		
		for(String stat : statistics.keySet()) {
			statistics.get(stat).execute(studentRegistrationService.findByCourseId(courseId));
		}
		System.out.println(Singleton.statistics);
		model.addAttribute("course", course);
		model.addAttribute("stats", Singleton.statistics);

		
		
		
		return "dashboard/viewStats";
	}
	
	
	
}






