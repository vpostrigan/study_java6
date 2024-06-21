package beans;

public class Student {
	private int id_People;
	private String resume;
	
	
	public Student() {
	}
	
	public Student(int id_People, String resume) {
		this.id_People = id_People;
		this.resume = resume;
	}

	
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
}
