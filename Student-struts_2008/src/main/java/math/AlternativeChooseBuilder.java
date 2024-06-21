package math;

public abstract class AlternativeChooseBuilder {
	
	protected AlternativeChoose alternativeChoose;

	public abstract void setInputTaskCondition_build(int inputTaskCondition[][][], int alternativeCount);
	
	public abstract void mQ1_build();	
	public abstract void mND_Q1_build();
	
	public abstract void mQ2_build();
	public abstract void mND_Q2_build();
	
	public abstract void mND_build();
	
	
	public AlternativeChoose getAlternativeChoose() {
		return alternativeChoose;
	}

	public void newAlternativeChoose() {
		this.alternativeChoose = new AlternativeChoose();
	}
	
	
}
