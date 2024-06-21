package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.People;
import database.DataBase;
import database.DataBaseConfig;

public class PeopleForm extends ActionForm{
	private static final long serialVersionUID = 3L;
	
	private int start = 0;
	private int chunkSize = finals.Final.CHUNK_SIZE_VALUE;
	private ArrayList<People> people;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
    	// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		try{
			String query = database.DataBaseQuery.getPeopleAll();
			
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
			
			Iterator<Vector<String>> tempIt = temp.iterator();
			ArrayList<People> tempPeople = new ArrayList<People>();
			
			while(tempIt.hasNext()){
				Vector<String> tempLine = tempIt.next();
				int passwd = tempLine.get(5).length();
				
				People p = new People(Integer.parseInt(tempLine.get(0)), tempLine.get(1), tempLine.get(2),tempLine.get(3),
						tempLine.get(4), ""+passwd, tempLine.get(6), Integer.parseInt(tempLine.get(7)), tempLine.get(8), 
						tempLine.get(9));
				
				tempPeople.add(p);
			}
			
			this.people = tempPeople; 
			
		}catch(Exception e){			
			this.people = null;
		}
    }
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {    	
		ActionErrors errors = new ActionErrors();    
		
		// !!! Проверка выполняется, но пользователю не выдается сообщение об ошибке, а
		// устанавливается значение по-умолчанию 
        if (chunkSize < 1){        
        	errors.add(finals.Final.CHUNK_SIZE, new ActionMessage(finals.Final.ERROR_CHUNK_SIZE, chunkSize));
        	this.setChunkSize(finals.Final.CHUNK_SIZE_VALUE);
        }
		
        return null;
    }

	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public ArrayList<People> getPeople() {
		return people;
	}
	public void setPeople(ArrayList<People> people) {
		this.people = people;
	}
	public int getChunkSize() {
		return chunkSize;
	}
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}	
}
