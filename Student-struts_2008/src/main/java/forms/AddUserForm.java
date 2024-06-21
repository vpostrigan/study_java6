package forms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.MenuList;
import beans.People;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;

/**
 * <p>Это Form класс для добавления нового пользователя</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class AddUserForm extends ActionForm {
	private static final long serialVersionUID = 4L;
	
	private People people;
	private boolean stringInserted;
	private ArrayList<MenuList> peopleType;	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {				
		this.stringInserted = false;
		
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		People peopleTemp = new People();
		try{
			String query = DataBaseQuery.getPeopleFreeID(); 
			// Установка свободного ID для нового пользователя
			peopleTemp.setId_People(DataBase.getInstance(dataBaseConfig).getFreeID(query));
		}catch(Exception e){
			peopleTemp.setId_People(0);
		}
		this.people = peopleTemp;
		
		try{
			// Список тип пользователя
			ArrayList<MenuList> peopleTypeTemp = new ArrayList<MenuList>();			
			peopleTypeTemp.add(new MenuList(finals.Final.PEOPLE_TYPE_STUDENT, finals.Final.PEOPLE_TYPE_STUDENT));
			peopleTypeTemp.add(new MenuList(finals.Final.PEOPLE_TYPE_TEACHER, finals.Final.PEOPLE_TYPE_TEACHER));
			peopleTypeTemp.add(new MenuList(finals.Final.PEOPLE_TYPE_EMPLOYEE, finals.Final.PEOPLE_TYPE_EMPLOYEE));
			peopleTypeTemp.add(new MenuList(finals.Final.PEOPLE_TYPE_ADMIN, finals.Final.PEOPLE_TYPE_ADMIN));
			
			this.peopleType = peopleTypeTemp;			
		}catch(Exception e){
			this.peopleType = null;
		}
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();
    	
        // Проверка поля 'code'
        if (people.getId_People() < 1){        
        	errors.add(finals.Final.ERROR_CODE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__CODE, people.getId_People()));
        }
        
        // Проверка поля 'surname'
        if (people.getSurname() == null || "".equals(people.getSurname())){
        	errors.add(finals.Final.ERROR_PEOPLE_SURNAME_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__SURNAME));
        }
        
        // Проверка поля 'name'
        if (people.getName() == null || "".equals(people.getName())){
        	errors.add(finals.Final.ERROR_PEOPLE_NAME_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__NAME));
        }
        
        // Проверка поля 'patronymic'
        if (people.getPatronymic() == null || "".equals(people.getPatronymic())){
        	errors.add(finals.Final.ERROR_PEOPLE_PATRONYMIC_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__PATRONYMIC));
        }
                
        // Проверка поля 'login'
        if (people.getLogin() == null || "".equals(people.getLogin())){
        	errors.add(finals.Final.ERROR_PEOPLE_LOGIN_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__LOGIN));
        }
        
        // Проверка поля 'password'
        if (people.getPassword() == null || "".equals(people.getPassword())){
        	errors.add(finals.Final.ERROR_PEOPLE_PASSWORD_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__PASSWORD));
        }
        
        // Проверка поля 'age'
        if (people.getAge() < 1900){        
        	errors.add(finals.Final.ERROR_AGE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__AGE, people.getId_People()));
        }
        
        // Проверка поля 'address'
        if (people.getAddress() == null || "".equals(people.getAddress())){
        	errors.add(finals.Final.ERROR_PEOPLE_ADDRESS_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_USER__ADDRESS));
        }
       
        if (errors.isEmpty()) return null;        
        return errors;
    }
	
	
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	public boolean isStringInserted() {
		return stringInserted;
	}
	public void setStringInserted(boolean stringInserted) {
		this.stringInserted = stringInserted;
	}
	public ArrayList<MenuList> getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(ArrayList<MenuList> peopleType) {
		this.peopleType = peopleType;
	}	
}
