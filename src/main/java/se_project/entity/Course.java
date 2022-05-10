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
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "instructor")
	private Instructor instructor;
	
	@Column(name="syllabus")
	private String syllabus;
	
	@Column(name="year")
	private int year;
	
	@Column(name="semester")
	private int semester;
	
	@Column(name="exam_weight")
	private double examWeight;
	
	@Column(name="project_weight")
	private double projectWeight;

	public Course() {
		
	}
	
	public Course(String name, Instructor instructor, String syllabus, int year, int semester, double examWeight,
			double projectWeight) {
		super();
		this.name = name;
		this.instructor = instructor;
		this.syllabus = syllabus;
		this.year = year;
		this.semester = semester;
		this.examWeight = examWeight;
		this.projectWeight = projectWeight;
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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public double getExamWeight() {
		return examWeight;
	}

	public void setExamWeight(double examWeight) {
		this.examWeight = examWeight;
	}

	public double getProjectWeight() {
		return projectWeight;
	}

	public void setProjectWeight(double projectWeight) {
		this.projectWeight = projectWeight;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", instructor=" + instructor + ", syllabus=" + syllabus
				+ ", year=" + year + ", semester=" + semester + ", examWeight=" + examWeight + ", projectWeight="
				+ projectWeight + "]";
	}	
		
}











