package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.Student;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.ResumeForm;

public class ResumeAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
	
		ActionErrors errors = new ActionErrors();
		ResumeForm resumeForm = (ResumeForm) form;
				
		if(form != null){
			if( editValue(resumeForm.getStudent(), dataBaseConfig) ){
				
				resumeForm.reset(mapping, request);
				
			}else{
				errors.add(finals.Final.ERROR_EDIT, new ActionMessage(finals.Final.ERROR_EDIT));
			}
		}
		
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
	
	/**
	 * <p>Изменение данных в БД</p>
	 * @param modifyProductForm Данные передаваемые в БД
	 * @param dataBaseConfig Настройки соединения с БД
	 * @return true если запрос выполнен нормально
	 */
	public boolean editValue(Student sForm, DataBaseConfig dataBaseConfig){	
		
		String query = DataBaseQuery.updateStudent(sForm);
		
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на внесение изменений
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
