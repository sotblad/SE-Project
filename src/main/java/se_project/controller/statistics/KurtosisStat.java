package se_project.controller.statistics;

import java.util.ArrayList;
import java.util.Collections;
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
		List<Double> gradesList = new ArrayList<>();
		int n = list.size();
		double sum = 0;
		double sam = 0;
		double stdDeviation = 0;
		
		for(StudentRegistration student : list) {
			gradesList.add(student.getGrade());
			sum += student.getGrade();
		}
		Collections.sort(gradesList);
		
		double mean = sum/n;
	
		sum = 0;
		for(int i = 0;i<n;i++) {
			sum += Math.pow(list.get(i).getGrade()-mean, 2);
		}
		stdDeviation = Math.sqrt(sum/(n-1));
		
		for(int i = 0;i<n;i++) {
			sam += Math.pow((list.get(i).getGrade()-mean)/stdDeviation, 4);
		}

		double res = sam*(n*(n+1))/((n-1)*(n-2)*(n-3));	
		
		statistics.setKurtosis((float)res);
	}
}
