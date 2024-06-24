package beans;

public class People {
	private int id_People;
	private String surname;
	private String name;
	private String patronymic;
	private String login;
	private String password;
	private String people_type;
	private int age;
	private String address;
	private String avatar;
	
	
	public People(){
		
	}
	
	public People(int id_People, String surname, String name, String patronymic, String login, String password,
			String people_type, int age, String address, String avatar) {
		this.id_People = id_People;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.login = login;
		this.password = password;
		this.people_type = people_type;
		this.age = age;
		this.address = address;
		this.avatar = avatar;
	}

	
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPeople_type() {
		return people_type;
	}
	public void setPeople_type(String people_type) {
		this.people_type = people_type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}	
}
