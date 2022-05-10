package se_project.controller.statistics;

public class StatisticFactory {
	public StatisticFactory() {}

	public Statistic createStatistic(String type) {
		if(type.equals("min")) {
			return new MinStat();
		}
		if(type.equals("max")) {
			return new MaxStat();
		}
		if(type.equals("mean")) {
			return new MeanStat();
		}
		if(type.equals("stdDeviation")) {
			return new StdDeviationStat();
		}
		if(type.equals("variance")) {
			return new VarianceStat();
		}
		if(type.equals("percentiles")) {
			return new PercentilesStat();
		}
		if(type.equals("skewness")) {
			return new SkewnessStat();
		}
		if(type.equals("kurtosis")) {
			return new KurtosisStat();
		}
		if(type.equals("median")) {
			return new MedianStat();
		}
		return null;
	}
}
