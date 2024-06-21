package journey.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import journey.semantic.JourneySesame;
import journey.semantic.JourneySesameBeanColumns;
import journey.utils.Util;
import journey.web.bean.City;
import journey.web.bean.TravelObject;
import journey.web.bean.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openrdf.model.Value;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.ntriples.NTriplesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Postrigan V.A.
 */
@Controller
public class CityController {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value = { "/city*" }, method = RequestMethod.GET)
	public String getCity(ModelMap map, @RequestParam(value = "city", required = true)
			String city, @RequestParam(value = "cityName", required = true)
			String cityName, @RequestParam(value = "type", required = true)
			String type) {
		
		city = Util.getLastValueFromURL(city);
		cityName = Util.getLastValueFromURL(cityName);
		type = Util.getLastValueFromURL(type);
		
		logger.info("JONI: city:" + city + " ; cityName:" + cityName + "; type:" + type);
		
		try{
			map.addAttribute("typies", this.getDataWithType(city, type) );
			map.addAttribute("city", city);
			map.addAttribute("cityValue", this.getCityValue(city));
			map.addAttribute("typeSelected", type);
		} catch (Exception e) {
			logger.error("JONE: Getting data from a repository: " + e.getMessage());
		}
		
		return "city";
	}
	
	private Collection<Type> getDataWithType(String city, String type) 
			throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		String[] select;
		
		String from = null;
		if (Util.isEmpty(type)){
			select = new String[] {"TravelObjectType", "name"};
			from = " FROM {" + city + "} journey:TravelObjectType {TravelObjectType} journey:name {name} ";
		} else {
			select = new String[] {"name"};
			from = " FROM {" + city + "} journey:TravelObjectType {" + type + "} journey:name {name} Where "+type+
					" LIKE \"*"+type+ "\"";
		}
		
		JourneySesameBeanColumns jsb = JourneySesame.getInstance().getData(select, from);
		
		Collection<Collection<Value>> column = jsb.getColumn();
		Collection<Type> typies = new ArrayList<Type>();
		
		for (Collection<Value> c : column) {
			Iterator<Value> columnIterator = c.iterator();
			
			while (columnIterator.hasNext()) {
				String idString = null;
				if (Util.isEmpty(type)){
					idString = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				} else {
					idString = type;
				}
				String name = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				
				Type type3 = new Type(idString, name, this.getTravelObjects(city, idString));
				typies.add(type3);
				
				//if(Util.checkCollection_Type(typies, type2)) {
					
				//}
			}
		}
		return typies;
	}
	
	private Collection<TravelObject> getTravelObjects(String city, String travelObjectTypeID) 
			throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		
		String[] select = {"TravelObject", "name", "address", "longitude", "latitude", "web_site", 
			"telephone", "price", "image"};
	
		String from = " FROM {" + city + "} journey:TravelObjectType {" + travelObjectTypeID + "} " +
				"journey:TravelObject {TravelObject} journey:name {name}; " +
													" journey:address {address}; " +
													" journey:longitude {longitude}; " +
													" journey:latitude {latitude}; " +
													" journey:web_site {web_site}; " +
													" journey:telephone {telephone}; " +
													" journey:price {price}; " +
													" journey:image {image} " +
				" WHERE " + travelObjectTypeID + " LIKE \"*" + travelObjectTypeID + "\"";
	
		JourneySesameBeanColumns jsb = JourneySesame.getInstance().getData(select, from);
		
		Collection<Collection<Value>> column = jsb.getColumn();
		Collection<TravelObject> travelObjects = new ArrayList<TravelObject>();
		
		for (Collection<Value> c : column) {
			Iterator<Value> columnIterator = c.iterator();
			
			while (columnIterator.hasNext()) {
				String idString = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String name = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String address = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );					
				double longitude = Double.parseDouble( columnIterator.next().stringValue() );
				double latitude = Double.parseDouble( columnIterator.next().stringValue() );
				String web_site = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String telephone = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String price = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String image = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				
				TravelObject travelObject = new TravelObject(idString, name, address, longitude, latitude, 
						travelObjectTypeID, web_site, telephone, price, image);
				
				//if(Util.checkCollection_TravelObject(travelObjects, travelObject)){
				travelObjects.add(travelObject);
				//}
			}
		}
		return travelObjects;
	}
	
	private City getCityValue(String cityId) 
			throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		String[] select = {"name", "latitude", "longitude", "mapZoom"};
		String from = " FROM {" + cityId + "} journey:name {name}; " +
				"journey:latitude {latitude}; journey:longitude {longitude}; journey:mapZoom {mapZoom} ";
		
		JourneySesameBeanColumns jsb = JourneySesame.getInstance().getData(select, from);
		Collection<Collection<Value>> column = jsb.getColumn();
		
		for (Collection<Value> c : column) {
			Iterator<Value> columnIterator = c.iterator();
			
			while (columnIterator.hasNext()) {
				String name = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				double latitude = Double.parseDouble( columnIterator.next().stringValue() );
				double longitude = Double.parseDouble( columnIterator.next().stringValue() );
				int mapZoom = Integer.parseInt( columnIterator.next().stringValue() );
				
				return new City(null, name, null, longitude, latitude, mapZoom, null);
			}
		}
		return null;
	}
}
