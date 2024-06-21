package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import beans.People;
import beans.PeopleEmail;
import beans.PeopleEmployment;
import beans.PeoplePhone;
import database.DataBase;
import database.DataBaseConfig;

public class ProfileForm extends ActionForm {
	private static final long serialVersionUID = 7L;
	
	private FormFile file;
	private People people;
	
	private ArrayList<PeoplePhone> peoplePhone;
	private ArrayList<PeopleEmail> peopleEmail;
	private ArrayList<PeopleEmployment> peopleEmployment;
	
	private boolean stringEdited;
	
	private String addPhone;
	private String addEmail;
	private String addEmployment;
	
	// Массивы с выбранными элементами для удаления 
	private String selectedItemsPhone[] = {};
	private String selectedItemsEmail[] = {};
	private String selectedItemsEmployment[] = {};

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.stringEdited = false;
		
		this.addPhone = "";
		this.addEmail = "";
		this.addEmployment = "";
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		String passwd = (String)session.getAttribute(finals.Final.PASSWORD);
		int id = 0;
		
		try{
			String queryId = database.DataBaseQuery.authorizationLogin(login);			
			id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
			
			String query = database.DataBaseQuery.getProductOneRecord(id);			
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
			
			String avatar = "";
			if(temp.get(0).get(9).equals("&nbsp;")){
				avatar = finals.Final.PATH_AVATAR_DEFAULT;
			}else{
				avatar = temp.get(0).get(9);
			}
			
			People p = new People(Integer.parseInt(temp.get(0).get(0)), temp.get(0).get(1), temp.get(0).get(2),
					temp.get(0).get(3), temp.get(0).get(4), ""+passwd, temp.get(0).get(6), 
					Integer.parseInt(temp.get(0).get(7)), temp.get(0).get(8), avatar);
						
			this.people = p;
		}catch(Exception e){			
			this.people = null;
		}
		
		try{	
			this.peoplePhone = getValuePhone(id, dataBaseConfig); 			
		}catch(Exception e){			
			this.peoplePhone = null;
		}
		
		try{	
			this.peopleEmail = getValueEmail(id, dataBaseConfig); 			
		}catch(Exception e){			
			this.peopleEmail = null;
		}
		
		try{	
			this.peopleEmployment = getValueEmployment(id, dataBaseConfig); 			
		}catch(Exception e){			
			this.peopleEmployment = null;
		}
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {    	
		ActionErrors errors = new ActionErrors();
    	
        // Проверка поля 'code'
        if (people.getId_People() < 1){        
        	errors.add(finals.Final.ERROR_CODE_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__CODE, people.getId_People()));
        }
        
        // Проверка поля 'surname'
        if (people.getSurname() == null || "".equals(people.getSurname())){
        	errors.add(finals.Final.ERROR_PEOPLE_SURNAME_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__SURNAME));
        }
        
        // Проверка поля 'name'
        if (people.getName() == null || "".equals(people.getName())){
        	errors.add(finals.Final.ERROR_PEOPLE_NAME_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__NAME));
        }
        
        // Проверка поля 'patronymic'
        if (people.getPatronymic() == null || "".equals(people.getPatronymic())){
        	errors.add(finals.Final.ERROR_PEOPLE_PATRONYMIC_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__PATRONYMIC));
        }
                
        // Проверка поля 'login'
        if (people.getLogin() == null || "".equals(people.getLogin())){
        	errors.add(finals.Final.ERROR_PEOPLE_LOGIN_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__LOGIN));
        }
        
        // Проверка поля 'password'
        if (people.getPassword() == null || "".equals(people.getPassword())){
        	errors.add(finals.Final.ERROR_PEOPLE_PASSWORD_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__PASSWORD));
        }
        
        // Проверка поля 'age'
        if (people.getAge() < 1900){        
        	errors.add(finals.Final.ERROR_AGE_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__AGE, people.getId_People()));
        }
        
        // Проверка поля 'address'
        if (people.getAddress() == null || "".equals(people.getAddress())){
        	errors.add(finals.Final.ERROR_PEOPLE_ADDRESS_EDIT, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__ADDRESS));
        }
       
        if (errors.isEmpty()) return null;        
        return errors;
    }
	
	
	/**
	 * <p>Список телефонов пользователя</p>
	 */
	public ArrayList<PeoplePhone> getValuePhone(int id, DataBaseConfig dataBaseConfig) throws Exception{
		String query = database.DataBaseQuery.getPeople_Phone_2(id);			
		Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
					
		Iterator<Vector<String>> tempIt = temp.iterator();
		ArrayList<PeoplePhone> tempPeoplePhone = new ArrayList<PeoplePhone>();
		
		while(tempIt.hasNext()){
			Vector<String> tempLine = tempIt.next();			
			PeoplePhone p = new PeoplePhone(Integer.parseInt(tempLine.get(0)), tempLine.get(1));
			
			tempPeoplePhone.add(p);
		}
		
		return tempPeoplePhone;
	}
	
	/**
	 * <p>Список E-mail пользователя</p>
	 */
	public ArrayList<PeopleEmail> getValueEmail(int id, DataBaseConfig dataBaseConfig) throws Exception{
		String query = database.DataBaseQuery.getPeople_Email_19(id);			
		Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
					
		Iterator<Vector<String>> tempIt = temp.iterator();
		ArrayList<PeopleEmail> tempPeopleEmail = new ArrayList<PeopleEmail>();
		
		while(tempIt.hasNext()){
			Vector<String> tempLine = tempIt.next();			
			PeopleEmail p = new PeopleEmail(Integer.parseInt(tempLine.get(0)), tempLine.get(1));
			
			tempPeopleEmail.add(p);
		}
		
		return tempPeopleEmail;
	}
	
	/**
	 * <p>Список занятости пользователя</p>
	 */
	public ArrayList<PeopleEmployment> getValueEmployment(int id, DataBaseConfig dataBaseConfig) throws Exception{
		String query = database.DataBaseQuery.getPeople_Employment_20(id);			
		Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
					
		Iterator<Vector<String>> tempIt = temp.iterator();
		ArrayList<PeopleEmployment> tempPeopleEmployment = new ArrayList<PeopleEmployment>();
		
		while(tempIt.hasNext()){
			Vector<String> tempLine = tempIt.next();			
			PeopleEmployment p = new PeopleEmployment(Integer.parseInt(tempLine.get(0)), tempLine.get(1));
			
			tempPeopleEmployment.add(p);
		}
		
		return tempPeopleEmployment;
	}
	
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	public ArrayList<PeoplePhone> getPeoplePhone() {
		return peoplePhone;
	}
	public void setPeoplePhone(ArrayList<PeoplePhone> peoplePhone) {
		this.peoplePhone = peoplePhone;
	}
	public ArrayList<PeopleEmail> getPeopleEmail() {
		return peopleEmail;
	}
	public void setPeopleEmail(ArrayList<PeopleEmail> peopleEmail) {
		this.peopleEmail = peopleEmail;
	}
	public ArrayList<PeopleEmployment> getPeopleEmployment() {
		return peopleEmployment;
	}
	public void setPeopleEmployment(ArrayList<PeopleEmployment> peopleEmployment) {
		this.peopleEmployment = peopleEmployment;
	}
	public boolean isStringEdited() {
		return stringEdited;
	}
	public void setStringEdited(boolean stringEdited) {
		this.stringEdited = stringEdited;
	}	
	public String[] getSelectedItemsPhone() {
		return selectedItemsPhone;
	}
	public void setSelectedItemsPhone(String[] selectedItemsPhone) {
		this.selectedItemsPhone = selectedItemsPhone;
	}
	public String[] getSelectedItemsEmail() {
		return selectedItemsEmail;
	}
	public void setSelectedItemsEmail(String[] selectedItemsEmail) {
		this.selectedItemsEmail = selectedItemsEmail;
	}
	public String[] getSelectedItemsEmployment() {
		return selectedItemsEmployment;
	}
	public void setSelectedItemsEmployment(String[] selectedItemsEmployment) {
		this.selectedItemsEmployment = selectedItemsEmployment;
	}
	public String getAddPhone() {
		return addPhone;
	}
	public void setAddPhone(String addPhone) {
		this.addPhone = addPhone;
	}
	public String getAddEmail() {
		return addEmail;
	}
	public void setAddEmail(String addEmail) {
		this.addEmail = addEmail;
	}
	public String getAddEmployment() {
		return addEmployment;
	}
	public void setAddEmployment(String addEmployment) {
		this.addEmployment = addEmployment;
	}	
}
