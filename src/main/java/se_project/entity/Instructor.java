package se_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instructors")
public class Instructor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;

	public Instructor() {
		
	}
	
	public Instructor(String fullname, String username, String password, boolean enabled) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", fullname=" + fullname + ", username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}
}











