package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class MaxStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public MaxStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		double max = 0;
		for(StudentRegistration student : list) {
			double grade = student.getGrade();
			if(grade > max)
				max = grade;
		}
		statistics.setMax((float)max);
	}
}
