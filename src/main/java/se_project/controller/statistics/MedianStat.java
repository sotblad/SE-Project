package se_project.controller.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class MedianStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public MedianStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		List<Double> gradesList = new ArrayList<>();
		
		for(StudentRegistration student : list) {
			gradesList.add(student.getGrade());
		}
		Collections.sort(gradesList);
		
		double midIndex = Math.ceil(gradesList.size()/2);
		double median = gradesList.get((int)midIndex);
		
		if(gradesList.size() % 2 == 0)
			median = (median + gradesList.get((int)midIndex-1))/2;
			
		statistics.setMedian((float)median);
	}
}
