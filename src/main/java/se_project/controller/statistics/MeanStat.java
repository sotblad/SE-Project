package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class MeanStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public MeanStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		double sum = 0;
		for(StudentRegistration student : list) {
			sum += student.getGrade();
		}

		statistics.setMean((float)sum/list.size());
	}
}
