package actions;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Student;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.ResumeLookForm;

public class ResumeLookAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		ResumeLookForm resumeLookForm = (ResumeLookForm) form;
		
		if(form != null){
			
			int id = resumeLookForm.getStudent().getId_People();
			
			if(id > 0){				
				// buttonShow was clicked
				
				// Запрос на получение данных по введенному коду из таблицы
				String query = DataBaseQuery.getStudent_4(id);
				
				try{
					Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
					
					Student s = new Student();
					s.setId_People( id );
					s.setResume( (temp.elementAt(0)).elementAt(0) );
					
					// Заполение формы
					PropertyUtils.setSimpleProperty(resumeLookForm, finals.Final.STUDENT, s);
				}catch(Exception e){
					
				}
			}
		}
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
}
