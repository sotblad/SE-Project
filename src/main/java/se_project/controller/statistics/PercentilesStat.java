package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class PercentilesStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public PercentilesStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		statistics.setPercentiles(10);
	}
}
