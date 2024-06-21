package journey.core.bean;

public class Item extends Bean {

	private static final long serialVersionUID = 3472716276067072497L;
	
    protected String name;
    protected String city;
    protected String state;
    protected String country;
    protected String zip;
    protected String street1;
    protected String street2;
    protected float latitude;
    protected float longitude;
    protected String phone;
    protected String fax;
    protected String email;
    protected String url;
    protected long owning_city;
    
	public Item(long id, String name, String city, String state, String country,
			String zip, String street1, String street2, float latitude,
			float longitude, String phone, String fax, String email,
			String url, long owning_city) {
		super(id);
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.street1 = street1;
		this.street2 = street2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.url = url;
		this.owning_city = owning_city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getOwning_city() {
		return owning_city;
	}

	public void setOwning_city(long owning_city) {
		this.owning_city = owning_city;
	}
}
