package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.LoginForm;

/**
 * <p>Этот Action класс выполняет авторизацию</p>
 * @author Postrigan V.A.
 * @version 1.0
 */
public class LoginAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionErrors errors = new ActionErrors();
		LoginForm loginForm = (LoginForm)form;
		
		if(form != null){
			
			// Получение параметров соединения с БД 
			DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
				servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
			
			
			// Запрос на проверку идентификации
			String authorizationLogin = DataBaseQuery.authorizationLogin(loginForm.getLogin());
			String authorizationPassword = DataBaseQuery.authorizationPassword(loginForm.getLogin(), loginForm.getPassword());
			
			// Авторизация
			try{
				if (DataBase.getInstance(dataBaseConfig).getData(authorizationLogin).size() == 0){
					
					// Неправильный Логин
					errors.add(finals.Final.ERROR_AUTHORIZATION_INCORRECT_LOGIN, 
							new ActionMessage(finals.Final.ERROR_AUTHORIZATION_INCORRECT_LOGIN));
				}
				
				if (DataBase.getInstance(dataBaseConfig).getData(authorizationPassword).size() == 0){
					
					// Неправильный Пароль
					errors.add(finals.Final.ERROR_AUTHORIZATION_INCORRECT_PASSWORD, 
							new ActionMessage(finals.Final.ERROR_AUTHORIZATION_INCORRECT_PASSWORD));
				}
			}catch(Exception e){
				errors.add(finals.Final.ERROR_DB, new ActionMessage(finals.Final.ERROR_DB));
			}
		}
				
		if (!errors.isEmpty()) {
	    	saveErrors(request, errors);
	    	return (mapping.getInputForward());
	    }
		
		HttpSession session = request.getSession();
		session.setAttribute(finals.Final.LOGIN, loginForm.getLogin());
		session.setAttribute(finals.Final.PASSWORD, loginForm.getPassword());
		
		return mapping.findForward(finals.Final.FORWARD_SUCCESS);
	}
}
