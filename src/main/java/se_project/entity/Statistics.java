package se_project.entity;

public class Statistics {
	private float min;
	private float max;
	private float mean;
	private float stDeviation;
	private float variance;
	private float percentiles;
	private float skewness;
	private float kurtosis;
	private float median;
	
	public Statistics(float min, float max, float mean, float stDeviation, float variance, float percentiles,
			float skewness, float kurtosis, float median) {
		super();
		this.min = min;
		this.max = max;
		this.mean = mean;
		this.stDeviation = stDeviation;
		this.variance = variance;
		this.percentiles = percentiles;
		this.skewness = skewness;
		this.kurtosis = kurtosis;
		this.median = median;
	}
	
	public Statistics() {
		
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMean() {
		return mean;
	}

	public void setMean(float mean) {
		this.mean = mean;
	}

	public float getStDeviation() {
		return stDeviation;
	}

	public void setStDeviation(float stDeviation) {
		this.stDeviation = stDeviation;
	}

	public float getVariance() {
		return variance;
	}

	public void setVariance(float variance) {
		this.variance = variance;
	}

	public float getPercentiles() {
		return percentiles;
	}

	public void setPercentiles(float percentiles) {
		this.percentiles = percentiles;
	}

	public float getSkewness() {
		return skewness;
	}

	public void setSkewness(float skewness) {
		this.skewness = skewness;
	}

	public float getKurtosis() {
		return kurtosis;
	}

	public void setKurtosis(float kurtosis) {
		this.kurtosis = kurtosis;
	}

	public float getMedian() {
		return median;
	}

	public void setMedian(float median) {
		this.median = median;
	}

	@Override
	public String toString() {
		return "Statistics [min=" + min + ", max=" + max + ", mean=" + mean + ", stDeviation=" + stDeviation
				+ ", variance=" + variance + ", percentiles=" + percentiles + ", skewness=" + skewness + ", kurtosis="
				+ kurtosis + ", median=" + median + "]";
	}	
}
