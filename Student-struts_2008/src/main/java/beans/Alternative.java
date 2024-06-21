package beans;

public class Alternative {
	
	private int id_Task;
	private String alternativeName;
		
	
	public Alternative() {
		
	}
	
	public Alternative(int id_Task, String alternativeName) {
		this.id_Task = id_Task;
		this.alternativeName = alternativeName;
	}
	
	
	public int getId_Task() {
		return id_Task;
	}
	public void setId_Task(int id_Task) {
		this.id_Task = id_Task;
	}
	public String getAlternativeName() {
		return alternativeName;
	}
	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}
}
