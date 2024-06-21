package beans;

public class PeopleEmployment {
	private int id_People;
	private String employment;
	
	
	public PeopleEmployment() {	
		
	}
	
	public PeopleEmployment(int id_People, String employment) {		
		this.id_People = id_People;
		this.employment = employment;
	}
	
	
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
}
