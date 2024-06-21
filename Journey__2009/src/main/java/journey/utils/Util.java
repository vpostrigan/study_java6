package journey.utils;

import java.util.Collection;

import journey.web.bean.TravelObject;
import journey.web.bean.Type;

public class Util {
	
	public static boolean checkCollection_Type(Collection<Type> c, Type o){
		
		for(Type type : c){
			if( type.getIdString().equalsIgnoreCase(o.getIdString()) ){
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean checkCollection_TravelObject(Collection<TravelObject> travelObjects, 
			TravelObject newTravelObject){
		
		for(TravelObject travelObject : travelObjects){
			if( travelObject.getIdString().equalsIgnoreCase(newTravelObject.getIdString()) ){				
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/** Checks if string is null or empty */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}
	
	/**
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getLastValueFromURL(String urlString) {
		
		if (urlString.lastIndexOf('/') > -1){
			return urlString.substring(urlString.lastIndexOf('/')+1);
		}
		
		return urlString;
	}
}
