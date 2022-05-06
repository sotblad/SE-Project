package se_project.controller.statistics;

import java.util.List;

import se_project.entity.StudentRegistration;

public interface Statistic {
	public void execute(List<StudentRegistration> list);
}
