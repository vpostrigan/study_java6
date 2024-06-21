package beans;

public class PeoplePhone {
	private int id_People;
	private String phone;
	
	
	public PeoplePhone(){
		
	}
	
	public PeoplePhone(int id_People, String phone){
		this.id_People = id_People;
		this.phone = phone;
	}

	
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
}

