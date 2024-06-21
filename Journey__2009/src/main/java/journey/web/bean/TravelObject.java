package journey.web.bean;

public class TravelObject extends BeanSTRING {
	
	private String name;
	
	private String city;
	
	private double longitude;
	
	private double latitude;
	
	private String type;
	
	private String web_site;
	
	private String telephone;
	
	private String price;
	
	private String image;

	
	public TravelObject(String idString, String name, String city,
			double longitude, double latitude, String type, String web_site,
			String telephone, String price, String image) {
		super(idString);
		this.name = name;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.type = type;
		this.web_site = web_site;
		this.telephone = telephone;
		this.price = price;
		this.image = image;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeb_site() {
		return web_site;
	}

	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
