package math;

public class AlternativeChoose {
	
	private int inputTaskCondition[][][];	
	private int alternativeCount;
	
	private int mQ1[][];
	private int mND_Q1[];
	
	private double mQ2[][];
	private int mND_Q2[];
	
	private int mND[];
	
	
	public int[][][] getInputTaskCondition() {
		return inputTaskCondition;
	}
	public void setInputTaskCondition(int inputTaskCondition[][][]) {
		this.inputTaskCondition = inputTaskCondition;
	}	
	public int getAlternative() {
		return alternativeCount;
	}
	public void setAlternative(int alternativeCount) {
		this.alternativeCount = alternativeCount;
	}
	public int[][] getMQ1() {
		return mQ1;
	}
	public void setMQ1(int[][] mq1) {
		mQ1 = mq1;
	}
	public int[] getMND_Q1() {
		return mND_Q1;
	}
	public void setMND_Q1(int[] mnd_q1) {
		mND_Q1 = mnd_q1;
	}
	public double[][] getMQ2() {
		return mQ2;
	}
	public void setMQ2(double[][] mq2) {
		mQ2 = mq2;
	}
	public int[] getMND_Q2() {
		return mND_Q2;
	}
	public void setMND_Q2(int[] mnd_q2) {
		mND_Q2 = mnd_q2;
	}
	public int[] getMND() {
		return mND;
	}
	public void setMND(int[] mnd) {
		mND = mnd;
	}		
}
