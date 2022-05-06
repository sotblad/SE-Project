package se_project.controller.statistics;

import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class KurtosisStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public KurtosisStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		statistics.setKurtosis(10);
	}
}
