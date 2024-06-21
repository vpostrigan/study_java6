package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.ForumAuthoForm;

public class ForumAuthoAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
				
		ForumAuthoForm forumAuthoForm = (ForumAuthoForm)form;
		
		if(form != null){
			
			String query = DataBaseQuery.getForum_8_pass(forumAuthoForm.getChapId(), forumAuthoForm.getChapPass());
			
			try{
				DataBase.getInstance(dataBaseConfig).getData(query).get(0).get(0);	
				
				ActionForward forward = mapping.findForward(finals.Final.FORWARD_SUCCESS);
				StringBuffer path = new StringBuffer(forward.getPath());
				
				boolean isQuery = (path.indexOf("?") >= 0);
				if(isQuery) path.append("&amp;id_Chapter=" + forumAuthoForm.getChapId());
				else path.append("?id_Chapter=" + forumAuthoForm.getChapId());
					
				return new ActionForward(path.toString());
			}catch(Exception e){
				return mapping.findForward("error");
			}
		}
		
		return mapping.findForward("error");
	}

}
