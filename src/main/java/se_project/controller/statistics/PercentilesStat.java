package se_project.controller.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import se_project.controller.Singleton;
import se_project.entity.Statistics;
import se_project.entity.StudentRegistration;

public class PercentilesStat implements Statistic {
	private Statistics statistics = Singleton.statistics;

	public PercentilesStat() {
		
	}
	
	@Override
	public void execute(List<StudentRegistration> list) {
		List<Double> gradesList = new ArrayList<>();
		HashMap<Integer, Double> percentiles = new HashMap<>();
		Collections.sort(list, new Comparator<StudentRegistration>(){
			public int compare(StudentRegistration o1, StudentRegistration o2){
				return (int)(o1.getGrade() - o2.getGrade());
			}
		});

		double[] d = new double[list.size()];
		for(int i = 0;i<list.size();i++) {
			d[i] = list.get(i).getGrade();
		}
		Percentile p = new Percentile();
		p.setData(d);
		
		for(int i = 1;i<=100;i+=1) {
			percentiles.put(i, p.evaluate(i));
		}
		percentiles = 
				percentiles.entrySet().stream()
			    .sorted(Entry.comparingByKey())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));

		statistics.setPercentiles(percentiles);
	}
}
