package journey.web.bean;

import java.util.Collection;

public class Type extends BeanSTRING {
	
	private String name;
	
	private Collection<TravelObject> travelObjects;

	
	public Type(String idString, String name,
			Collection<TravelObject> travelObjects) {
		super(idString);
		this.name = name;
		this.travelObjects = travelObjects;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<TravelObject> getTravelObjects() {
		return travelObjects;
	}

	public void setTravelObjects(Collection<TravelObject> travelObjects) {
		this.travelObjects = travelObjects;
	}	
}
