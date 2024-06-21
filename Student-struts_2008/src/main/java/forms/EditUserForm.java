package forms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.MenuList;
import beans.People;

public class EditUserForm extends ActionForm {	
	private static final long serialVersionUID = 6L;
	
	private People people;
	private boolean stringEdited;
	private ArrayList<MenuList> peopleType;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {		
		
		this.stringEdited = false;		
		
		People peopleTemp = new People();		
		peopleTemp.setId_People(0);		
		peopleTemp.setSurname(" ");
		peopleTemp.setName(" ");
		peopleTemp.setPatronymic(" ");
		peopleTemp.setLogin(" ");
		peopleTemp.setPassword(" ");
		peopleTemp.setPeople_type(" ");
		peopleTemp.setAge(finals.Final.AGE_DEFAULT);
		peopleTemp.setAddress(" ");
		
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

	
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	public boolean isStringEdited() {
		return stringEdited;
	}
	public void setStringEdited(boolean stringEdited) {
		this.stringEdited = stringEdited;
	}
	public ArrayList<MenuList> getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(ArrayList<MenuList> peopleType) {
		this.peopleType = peopleType;
	}		
}
