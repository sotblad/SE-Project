package se_project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import se_project.controller.statistics.Statistic;
import se_project.controller.statistics.StatisticFactory;
import se_project.entity.Statistics;

public class Singleton {
	private static Singleton instance;
	public static Statistics statistics;
	public static HashMap<String, Statistic> supportedStatistics;

    private Singleton() {
    	statistics = new Statistics();
    	supportedStatistics = new HashMap<String, Statistic>();
    	StatisticFactory statisticFactory = new StatisticFactory();
    	List<String> statisticsList = Arrays.asList(
				new String[] {
						"min",
						"max",
						"mean",
						"stdDeviation",
						"variance",
						"percentiles",
						"skewness",
						"kurtosis",
						"median"
				}
		);
    	
    	for(int i = 0; i < statisticsList.size(); i++) {
			String statistic = statisticsList.get(i);
			Singleton.supportedStatistics.put(statistic, statisticFactory.createStatistic(statistic));
		}
    }

    public static Singleton getInstance() {
    	if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}