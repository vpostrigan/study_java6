package journey.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import journey.semantic.JourneySesame;
import journey.semantic.JourneySesameBeanColumns;
import journey.utils.Util;
import journey.web.bean.City;
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

/**
 * Home page controller
 */
@Controller
public class HomeController {
	
	private final Log logger = LogFactory.getLog(this.getClass());
		
	@RequestMapping(value = { "/index*" }, method = RequestMethod.GET)
	public String getHomePage(ModelMap map) {
		
		logger.info("JONE: HomeController");
		
		try{
			String[] select = {"name", "City", "longitude", "latitude", "mapZoom"};
			
			String from = " FROM {Ukraine} journey:City {City} journey:name {name};" +
														" journey:longitude {longitude};" +
														" journey:latitude {latitude}; " +
														" journey:mapZoom {mapZoom}";

			JourneySesameBeanColumns jsb = JourneySesame.getInstance().getData(select, from);
			
			Collection<Collection<Value>> column = jsb.getColumn();
			Collection<City> cities = new ArrayList<City>();
			
			for (Collection<Value> c : column) {
				Iterator<Value> columnIterator = c.iterator();
				
				while (columnIterator.hasNext()) {
					String name = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
					String idString = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
					
					double longitude = Double.parseDouble( columnIterator.next().stringValue() );
					double latitude = Double.parseDouble( columnIterator.next().stringValue() );
					int mapZoom = Integer.parseInt( columnIterator.next().stringValue() );
										
					City city = new City(idString, name, "Ukraine", longitude, latitude, 
							mapZoom, getTypies(idString));
					cities.add(city);
				}
			}
			
			map.addAttribute("cities", cities);			
			
		} catch (Exception e) {
			logger.error("JONE: Getting data from a repository: " + e.getMessage());			
		}
		
		return "index";
				
		//String priwet = new String("\u041F\u0440\u0438\u0432\u0435\u0442!");
		//byte[] utf8Bytes = priwet.getBytes("UTF8");
		//String priwet2 = new String(utf8Bytes,"UTF8");
					
		//cities.add(new BeanSTRING(new String(sn.getBytes("ISO-8859-1"), "UTF-8") ));
		//cities.add(new BeanSTRING(new String(sn.getBytes("UTF-8"), "UTF-8") ));		 
				
		//logger.info("*"+ new String(c.stringValue().getBytes("UTF8"), "UTF8") );
		//logger.info("*"+ new String(c.stringValue().getBytes("UTF-8"), "UTF-8") );
		//logger.info("*"+ new String(c.stringValue().getBytes("UTF-16"), "UTF-16") );
		//cities.add(new BeanSTRING( new String(c.stringValue().getBytes("ISO-8859-1"), "UTF-8") ));
		//cities.add(new BeanSTRING( new String(c.stringValue().getBytes("UTF-8"), "UTF-8") ));
		//cities.add(new BeanSTRING( new String(c.stringValue().getBytes("ISO-8859-1"), "ISO-8859-1") ));
		//cities.add(new BeanSTRING( new String(c.stringValue().getBytes("UTF-8"), "ISO-8859-1") ));
				
		//cities.add(new BeanSTRING(new String(c.toString().getBytes("ISO-8859-1"), "UTF-8")));			
	}
	
	private Collection<Type> getTypies(String city) 
			throws RepositoryException, MalformedQueryException, QueryEvaluationException{
		// City typies
		String[] select = {"TravelObjectType", "name"};
		String from = " FROM {"+city.substring(city.lastIndexOf('/')+1)+"} journey:TravelObjectType {TravelObjectType} " +
					" journey:name {name}";
			
		JourneySesameBeanColumns jsb = JourneySesame.getInstance().getData(select, from);
		Collection<Collection<Value>> column = jsb.getColumn();
		Collection<Type> typies  = new ArrayList<Type>();
		
		for (Collection<Value> c : column) {
			Iterator<Value> columnIterator = c.iterator();
			
			while (columnIterator.hasNext()) {
				String idString = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				String typeName = NTriplesUtil.unescapeString( columnIterator.next().stringValue() );
				
				if(Util.checkCollection_Type(typies, new Type(idString, typeName, null))){
					typies.add(new Type(idString, typeName, null));
				}				
			}
		}
		
		return typies;
	}
}
