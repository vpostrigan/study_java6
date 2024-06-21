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

import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.DeleteUserForm;

public class DeleteUserAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		DeleteUserForm deleteProductForm = (DeleteUserForm) form;
		
		if(form != null){
			if( deleteValue(deleteProductForm.getCode(), dataBaseConfig) ){
				
				// Код удаленного товара
				request.setAttribute(finals.Final.CODE_COMMIT_DELETE, deleteProductForm.getCode());
				
				deleteProductForm.reset(mapping, request);
				
				// Данные удалены, STRING_DELETED = true
				PropertyUtils.setSimpleProperty(deleteProductForm, finals.Final.STRING_DELETED, true);
			}else{
				errors.add(finals.Final.ERROR_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
			}
		}
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
	
	/**
	 * <p>Удаление данных из БД<p>
	 */
	public boolean deleteValue(int code, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных из таблицы Product
		String deleteAdmin = DataBaseQuery.deleteAdmin(code);
		String deleteStudent = DataBaseQuery.deleteStudent(code);
		String deleteTeacher = DataBaseQuery.deleteTeacher(code);
		String deleteEmployee = DataBaseQuery.deleteEmployee(code);
		
		String deletePeople_Phone_2 = DataBaseQuery.deletePeople_Phone_2(code);
		String deletePeople_Email_19 = DataBaseQuery.deletePeople_Email_19(code);
		String deletePeople_Employment_20 = DataBaseQuery.deletePeople_Employment_20(code);
		
		String deletePeople = DataBaseQuery.deletePeople(code);
						
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на удаление данных
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteAdmin );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteStudent );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteTeacher );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteEmployee );
			
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deletePeople_Phone_2 );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deletePeople_Email_19 );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deletePeople_Employment_20 );
			
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deletePeople );
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
