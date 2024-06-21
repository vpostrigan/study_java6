package beans;

public class PeopleEmail {
	private int id_People;
	private String email;
	
	
	public PeopleEmail(){
		
	}
	
	public PeopleEmail(int id_People, String email){
		this.id_People = id_People;
		this.email = email;
	}

	
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
