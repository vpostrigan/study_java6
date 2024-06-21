package forms;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import beans.Student;
import database.DataBase;
import database.DataBaseConfig;

public class ResumeForm extends ActionForm{
	private static final long serialVersionUID = 9L;	
	
	private Student student;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Получение параметров соединения с БД
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		try{
			String queryId = database.DataBaseQuery.authorizationLogin(login);			
			int id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
			
			String student = database.DataBaseQuery.getStudent_4(id);
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(student);
						
			// Логин + резюме
			Student stud = new Student(id, temp.get(0).get(0));	
			
			this.student = stud;			
		}catch(Exception e){
			this.student = null;
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
}
