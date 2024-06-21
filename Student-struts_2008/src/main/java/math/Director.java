package math;

public class Director {
	private AlternativeChooseBuilder alternativeChooseBuilder;

	/**
	 * <p>Метод решает задачу (по частям)</p>
	 * 
	 * @param inputTaskCondition - Исходный данные
	 * @param alternativeCount - Число альтернатив
	 */
	public void constructResult(int inputTaskCondition[][][], int alternativeCount){
		
		alternativeChooseBuilder.newAlternativeChoose();
		
		alternativeChooseBuilder.setInputTaskCondition_build(inputTaskCondition, alternativeCount);
		
		alternativeChooseBuilder.mQ1_build();
		alternativeChooseBuilder.mQ2_build();
		
		alternativeChooseBuilder.mND_Q1_build();
		alternativeChooseBuilder.mND_Q2_build();
		
		alternativeChooseBuilder.mND_build();
	}
	
	
	public AlternativeChooseBuilder getAlternativeChooseBuilder() {
		return alternativeChooseBuilder;
	}	
	public void setAlternativeChooseBuilder(AlternativeChooseBuilder alternativeChooseBuilder) {
		this.alternativeChooseBuilder = alternativeChooseBuilder;
	}	
	
	public AlternativeChoose getResult() {
		return alternativeChooseBuilder.getAlternativeChoose();
	}
}
