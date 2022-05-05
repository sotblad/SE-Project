package se_project.entity;

import java.io.Serializable;

public class AccountId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 271767987177583490L;

	private int id;

    private int courseId;

	public AccountId() {
	}

	public AccountId(int id, int courseId) {
		super();
		this.id = id;
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountId other = (AccountId) obj;
		if (courseId != other.courseId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	
    // default constructor

}