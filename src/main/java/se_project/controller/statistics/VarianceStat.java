package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class VarianceStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public VarianceStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		double sum = 0;
		double mean = 0;
		
		for(StudentRegistration student : list) {
			sum += student.getGrade();
		}

		mean = sum/list.size();
		sum = 0;
		
		for(int i = 0;i<list.size();i++) {
			sum += Math.pow(list.get(i).getGrade()-mean, 2);
		}
		
		statistics.setVariance((float)sum/(list.size()-1));
	}
}
