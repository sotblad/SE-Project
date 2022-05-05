package se_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(AccountId.class)
@Table(name="studentRegistrations")
public class StudentRegistration {

	// define fields
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="year_of_registration")
	private int yearOfRegistration;
	
	@Column(name="semester")
	private int semester;
	
	@Id
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="grade")
	private double grade;

	@Column(name="project_grade")
	private double projectGrade;
	
	@Column(name="exam_grade")
	private double examGrade;
	
	public StudentRegistration() {
	}

	public StudentRegistration(String name, int yearOfRegistration, int semester, int courseId, double grade,
			double projectGrade, double examGrade) {
		super();
		this.name = name;
		this.yearOfRegistration = yearOfRegistration;
		this.semester = semester;
		this.courseId = courseId;
		this.grade = grade;
		this.projectGrade = projectGrade;
		this.examGrade = examGrade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getProjectGrade() {
		return projectGrade;
	}

	public void setProjectGrade(double projectGrade) {
		this.projectGrade = projectGrade;
	}

	public double getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(double examGrade) {
		this.examGrade = examGrade;
	}

	@Override
	public String toString() {
		return "StudentRegistration [id=" + id + ", name=" + name + ", yearOfRegistration=" + yearOfRegistration
				+ ", semester=" + semester + ", courseId=" + courseId + ", grade=" + grade + ", projectGrade="
				+ projectGrade + ", examGrade=" + examGrade + "]";
	}
		
}











