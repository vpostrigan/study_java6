package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.People;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.AddUserForm;

/**
 * <p>Этот Action класс выполняет добавление нового пользователя</p>
 * @author Postrigan V.A.
 * @version 1.0
 */
public class AddUserAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		AddUserForm modifyUserForm = (AddUserForm) form;
		
		if(form != null){									
			
			if( insertValue(modifyUserForm.getPeople(), dataBaseConfig) ){
				
				// Код и название добавленного товара
				request.setAttribute(finals.Final.CODE_COMMIT_ADD, modifyUserForm.getPeople().getId_People());
				request.setAttribute(finals.Final.NAME_COMMIT_ADD, modifyUserForm.getPeople().getSurname());
				
				modifyUserForm.reset(mapping, request);
				
				// Данные добавлены, STRING_INSERTED = true
				PropertyUtils.setSimpleProperty(modifyUserForm, finals.Final.STRING_INSERTED, true);
			}else{
				errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
			}
		}
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
	
	
	/**
	 * <p>Добавление данных в БД</p>
	 * @return true если запрос выполнен
	 */
	public boolean insertValue(People p, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу Product
		p.setAvatar(finals.Final.AVATAR_DEFAULT);
		String query = DataBaseQuery.insertPeople(p);
		String query2 = "";
		
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на вставку данных
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			if(p.getPeople_type().equals(finals.Final.PEOPLE_TYPE_ADMIN)){
				// Запрос на вставку данных в таблицу ADMIN
				query2 = DataBaseQuery.insertAdmin(p.getId_People());
				queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query2);
			}
			if(p.getPeople_type().equals(finals.Final.PEOPLE_TYPE_STUDENT)){
				// Запрос на вставку данных в таблицу STUDENT
				query2 = DataBaseQuery.insertStudent(p.getId_People(), "");
				queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query2);
			}
			if(p.getPeople_type().equals(finals.Final.PEOPLE_TYPE_TEACHER)){
				// Запрос на вставку данных в таблицу TEACHER
				query2 = DataBaseQuery.insertTeacher(p.getId_People());
				queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query2);
			}
			if(p.getPeople_type().equals(finals.Final.PEOPLE_TYPE_EMPLOYEE)){
				// Запрос на вставку данных в таблицу EMPLOYEE
				query2 = DataBaseQuery.insertEmployee(p.getId_People());
				queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query2);
			}
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
