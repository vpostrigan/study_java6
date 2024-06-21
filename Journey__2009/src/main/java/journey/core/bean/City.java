package journey.core.bean;

public class City extends Bean {

	private static final long serialVersionUID = 6541006361574222523L;
	
	protected String name;
	protected String statecode;
	protected long country;
	protected long continent;
    protected String blurb;
    protected String coverImage;
    
	public City(long id, String name, String statecode, long country, long continent,
			String blurb, String coverImage) {
		super(id);
		this.name = name;
		this.statecode = statecode;
		this.country = country;
		this.continent = continent;
		this.blurb = blurb;
		this.coverImage = coverImage;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public long getCountry() {
		return country;
	}
	public void setCountry(long country) {
		this.country = country;
	}
	public long getContinent() {
		return continent;
	}
	public void setContinent(long continent) {
		this.continent = continent;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
}
