package se_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="studentRegistrations")
public class StudentRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="year_of_registration")
	private int yearOfRegistration;
	
	@Column(name="semester")
	private int semester;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@Column(name="grade")
	private double grade;

	@Column(name="project_grade")
	private double projectGrade;
	
	@Column(name="exam_grade")
	private double examGrade;
	
	public StudentRegistration() {
	}

	public StudentRegistration(int studentId, String name, int yearOfRegistration, int semester, Course course, double grade,
			double projectGrade, double examGrade) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.yearOfRegistration = yearOfRegistration;
		this.semester = semester;
		this.course = course;
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
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
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
		return "StudentRegistration [id=" + id + ", studentId=" + studentId + ", name=" + name + ", yearOfRegistration=" + yearOfRegistration
				+ ", semester=" + semester + ", course=" + course + ", grade=" + grade + ", projectGrade="
				+ projectGrade + ", examGrade=" + examGrade + "]";
	}
		
}











