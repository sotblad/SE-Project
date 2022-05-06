package se_project.controller.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class SkewnessStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public SkewnessStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		List<Double> gradesList = new ArrayList<>();
		double sum = 0;
		double stdDeviation = 0;
		
		for(StudentRegistration student : list) {
			gradesList.add(student.getGrade());
			sum += student.getGrade();
		}
		Collections.sort(gradesList);
		
		double mean = sum/list.size();
	
		sum = 0;
		double sam = 0;
		for(int i = 0;i<list.size();i++) {
			sum += Math.pow(list.get(i).getGrade()-mean, 2);
			sam += Math.pow(list.get(i).getGrade()-mean, 3);
		}
		System.out.println(sam);
		stdDeviation = Math.sqrt(sum/(list.size()-1));

		double res = sam/((list.size()-1)*Math.pow(stdDeviation, 3));
	//	System.out.println(res*(list.size()/(list.size()-1)));
		//System.out.println((list.size()/(list.size()-1)));
		
		
		statistics.setSkewness((float)res);
	}
}
