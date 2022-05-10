package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class MinStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public MinStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		double min = 10;
		for(StudentRegistration student : list) {
			double grade = student.getGrade();
			if(grade < min)
				min = grade;
		}
		statistics.setMin((float)min);
	}
}
