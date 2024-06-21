package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import beans.PeopleEmail;
import beans.Student;
import database.DataBase;
import database.DataBaseConfig;

public class ResumeLookForm extends ActionForm{
	private static final long serialVersionUID = 10L;	
	
	private Student student = new Student();
	private ArrayList<PeopleEmail> people_Receiver;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Получение параметров соединения с БД
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		try{
			// Список пользователей
			ArrayList<PeopleEmail> peopleTypeTemp = new ArrayList<PeopleEmail>();			
			String users = database.DataBaseQuery.getStudent_4_Resume();
			Vector<Vector<String>> temp2 = DataBase.getInstance(dataBaseConfig).getData(users);
				
			Iterator<Vector<String>> tempIt2 = temp2.iterator();
			while(tempIt2.hasNext()){
				Vector<String> tempLine2 = tempIt2.next();
					
				PeopleEmail p = new PeopleEmail( Integer.parseInt( tempLine2.get(0)), 
						(tempLine2.get(1) + " " + tempLine2.get(2) + " " + tempLine2.get(3)) );
					
				peopleTypeTemp.add(p);
			}
				
			this.people_Receiver = peopleTypeTemp;			
		}catch(Exception e){
			this.people_Receiver = null;
		}	
	}
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
        return null;
    }

	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public ArrayList<PeopleEmail> getPeople_Receiver() {
		return people_Receiver;
	}
	public void setPeople_Receiver(ArrayList<PeopleEmail> people_Receiver) {
		this.people_Receiver = people_Receiver;
	}	
}
