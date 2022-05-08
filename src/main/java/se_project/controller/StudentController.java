package se_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import se_project.entity.StudentRegistration;
import se_project.service.StudentRegistrationService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	public StudentController(StudentRegistrationService theStudentRegistrationService) {
		studentRegistrationService = theStudentRegistrationService;
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
		
	    return "redirect:/viewCourse?course=" + studentRegistration.getCourseId();
	}
	
	//US8
	@PostMapping("/removeStudent")
	public String removeStudent(@ModelAttribute("student")int studentId, @ModelAttribute("course")int courseId, Model model) {
			
		StudentRegistration result = studentRegistrationService.findByStudentIdAndCourseId(studentId, courseId);
		studentRegistrationService.deleteById(result.getId());

		return "redirect:/viewCourse?course=" + courseId;
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
			return "redirect:/viewCourse?course=" + student.getCourseId(); 
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
}






