package math;

public class MultiCriterial_AlternativeChooseBuilder extends AlternativeChooseBuilder{
	
	/**
	 * <p>Задание значений входных параметров</p>
	 */
	public void setInputTaskCondition_build(int inputTaskCondition[][][], int alternativeCount){
		
		for(int i=0; i < inputTaskCondition.length; i++){
			for(int j=0; j < inputTaskCondition[i].length; j++){
				for(int k=0; k < inputTaskCondition[i][j].length; k++){
					System.out.println("& " + inputTaskCondition[i][j][k]);
				}				
			}			
		}
		
		alternativeChoose.setInputTaskCondition(inputTaskCondition);
		alternativeChoose.setAlternative(alternativeCount);		
	}
	
	/**
	 * <p>Определение нечеткого отношения предпочтения</p>
	 */
	public void mQ1_build(){
		
		int value[][][] = alternativeChoose.getInputTaskCondition();
		int mQ1Temp[][] = new int[alternativeChoose.getAlternative()][alternativeChoose.getAlternative()];
		
		for(int i=0; i < 1; i++){
			for(int j=0; j < value[i].length; j++){
				for(int k=0; k < value[i][j].length; k++){
					mQ1Temp[j][k] = value[i][j][k];
				}				
			}			
		}
		
		for(int i=1; i < value.length; i++){
			for(int j=0; j < value[i].length; j++){
				for(int k=0; k < value[i][j].length; k++){					
					if(mQ1Temp[j][k] > value[i][j][k]){
						mQ1Temp[j][k] = value[i][j][k];
					}					
				}				
			}			
		}
		
		alternativeChoose.setMQ1(mQ1Temp);System.out.println("# 1.2.2");
	}
	
	/**
	 * <p>Множество недоминируемых альтернатив в множестве (X, Q1)</p>
	 */
	public void mND_Q1_build(){
		
		int mQ1Temp[][] = alternativeChoose.getMQ1();
		int mND_Q1Temp[] = new int [alternativeChoose.getAlternative()];
		
		for(int i=0; i < mND_Q1Temp.length; i++){
			
			int max = 0;
			for(int j=0; j < mQ1Temp.length; j++){
				
				if(i != j){
					if (max < mQ1Temp[j][i] - mQ1Temp[i][j]){
						max = mQ1Temp[j][i] - mQ1Temp[i][j];
					}
				}								
			}
			
			mND_Q1Temp[i] = 1 - max; 
		}
		
		alternativeChoose.setMND_Q1(mND_Q1Temp);System.out.println("# 1.3.2");
	}
	
	/**
	 * <p>Определение функции принадлежности пересечения mQ2(Xi, Yj)</p>
	 */
	public void mQ2_build(){
		
		int value[][][] = alternativeChoose.getInputTaskCondition();
		double mQ2Temp[][] = new double[alternativeChoose.getAlternative()][alternativeChoose.getAlternative()];
		
		for(int j=0; j < value[0].length; j++){
			for(int k=0; k < value[0][0].length; k++){
				
				double sum = 0;
				for(int i=0; i < value.length; i++){
					sum += value[i][j][k];
				}
				
				mQ2Temp[j][k] = (1.0/(1.0*alternativeChoose.getAlternative())) * sum;
			}
		}
		
		alternativeChoose.setMQ2(mQ2Temp);
	}
	
	/**
	 * <p>Множество недоминируемых альтернатив в множестве (X, Q2)</p>
	 */
	public void mND_Q2_build(){
		
		double mQ2Temp[][] = alternativeChoose.getMQ2();
		int mND_Q2Temp[] = new int [alternativeChoose.getAlternative()];
		
		for(int i=0; i < mND_Q2Temp.length; i++){
			
			double max = 0;
			for(int j=0; j < mQ2Temp.length; j++){
				
				if(i != j){
					if (max < mQ2Temp[j][i] - mQ2Temp[i][j]){
						max = mQ2Temp[j][i] - mQ2Temp[i][j];
					}
				}								
			}
			
			if(1 - max >= 1){
				mND_Q2Temp[i] = 1; 
			}else{
				mND_Q2Temp[i] = 0; 
			}
			
		}
		
		alternativeChoose.setMND_Q2(mND_Q2Temp);
	}
	
	/**
	 * <p>Построение результирующего множества недоминируемых альтернатив</p>
	 */
	public void mND_build(){
		
		int mND_Q1Temp[] = alternativeChoose.getMND_Q1(); 
		int mND_Q2Temp[] = alternativeChoose.getMND_Q2();
		
		int mND[] = new int [alternativeChoose.getAlternative()];
		
		for(int i=0; i < mND_Q1Temp.length; i++){
			System.out.println("# 1.6.2===" + mND_Q1Temp[i] + "  " + mND_Q2Temp[i]);
			if(mND_Q1Temp[i] == mND_Q2Temp[i]){
				mND[i] = mND_Q2Temp[i];
			}else{
				mND[i] = 0;
			}
		}
		
		alternativeChoose.setMND(mND);
	}
}
