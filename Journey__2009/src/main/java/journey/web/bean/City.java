package journey.web.bean;

import java.util.Collection;

public class City extends BeanSTRING {
	
	private String name;
	
	private String country;
	
	private double longitude;
	
	private double latitude;
	
	private int mapZoom;
	
	private Collection<Type> typies;

	public City(String idString, String name, String country, double longitude,
			double latitude, int mapZoom, Collection<Type> typies) {
		super(idString);
		this.name = name;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.mapZoom = mapZoom;
		this.typies = typies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public int getMapZoom() {
		return mapZoom;
	}

	public void setMapZoom(int mapZoom) {
		this.mapZoom = mapZoom;
	}

	public Collection<Type> getTypies() {
		return typies;
	}

	public void setTypies(Collection<Type> typies) {
		this.typies = typies;
	}	
}
