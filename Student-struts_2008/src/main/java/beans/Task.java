package beans;

public class Task {
	private int id_Task;
	private String name;
	private String date;
	private int id_People;
	
	public Task() {
	}
	
	public Task(int id_Task, String name, String date, int id_People) {
		this.id_Task = id_Task;
		this.name = name;
		this.date = date;
		this.id_People = id_People;
	}
	
	
	public int getId_Task() {
		return id_Task;
	}
	public void setId_Task(int id_Task) {
		this.id_Task = id_Task;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
}
