package forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <p>Это Form класс для авторизации пользователя</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class LoginForm extends ActionForm {
	private static final long serialVersionUID = 1L;
		
	private String login = "";
	private String password = "";
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {		
		this.login = null;
		this.password = null;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();
    	        
        // Проверка поля 'login'
        if (login == null || "".equals(login)){
        	errors.add(finals.Final.ERROR_AUTHORIZATION_LOGIN, new ActionMessage(finals.Final.ERROR_AUTHORIZATION_LOGIN));
        }
                
        if (errors.isEmpty()) return null;        
        return errors;
    }
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
