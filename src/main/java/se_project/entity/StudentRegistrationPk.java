package se_project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentRegistrationPk implements Serializable{
	@Column(name="id")
	private int id;
	
	@Column(name="course_id")
	private int courseId;
	
	public StudentRegistrationPk() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
