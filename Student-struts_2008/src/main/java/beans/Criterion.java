package beans;

public class Criterion {
	
	private int id_Task;
	private String criterionName;
	
	
	public Criterion() {
		
	}
	
	public Criterion(int id_Task, String criterionName) {
		super();
		this.id_Task = id_Task;
		this.criterionName = criterionName;
	}

	
	public int getId_Task() {
		return id_Task;
	}
	public void setId_Task(int id_Task) {
		this.id_Task = id_Task;
	}
	public String getCriterionName() {
		return criterionName;
	}
	public void setCriterionName(String criterionName) {
		this.criterionName = criterionName;
	}
}
