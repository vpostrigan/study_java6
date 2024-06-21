package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.Forum;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.MainForm;

public class MainAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();		
		MainForm mainForm = (MainForm)form;
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		String buttonDelete = request.getParameter(finals.Final.BUTTON_DELETE);
		String buttonAdd = request.getParameter(finals.Final.BUTTON_ADD);
		
		String queryId = database.DataBaseQuery.authorizationLogin(login);	
		int id = 0;
		try{
			id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
		}catch(Exception e){
			
		}
		
		
		if(form != null){
			
			if(buttonDelete != null){
				// buttonDelete was clicked
				
				try{
					String temp[] = mainForm.getSelectedItems();
					for(int i=0; i < temp.length; i++){						
						if( deleteValue(Integer.parseInt(temp[i]), dataBaseConfig) ){
							mainForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}
				}catch(Exception e){
					System.out.println(e);
					errors.add(finals.Final.ERROR_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}
			
			if(buttonAdd!= null){
				// buttonAdd was clicked
				
				boolean check = true;
				// Проверка поля 'newForumPassword'
		        if (mainForm.getNewForumName() == null || "".equals(mainForm.getNewForumName())){
		        	errors.add(finals.Final.ERROR_FORUM_NAME_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_FORUM_NAME));
		        	check = false;
		        }
				
		        // Проверка поля 'newForumPassword'
		        if (mainForm.getNewForumPassword() == null || "".equals(mainForm.getNewForumPassword())){
		        	errors.add(finals.Final.ERROR_FORUM_PASSWORD_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_PASSWORD));
		        	check = false;
		        }  
				
		        if(check){
		        	try{
						Date d = new Date();
					    DateFormat df = new SimpleDateFormat(finals.Final.SIMPLE_DATE_FORMAT);
						
						String query = database.DataBaseQuery.getForum_8_FreeID();
						int newForumId = DataBase.getInstance(dataBaseConfig).getFreeID(query);
						
						if(mainForm.getNewForumName().length() > 0){	
							Forum f = new Forum(newForumId, mainForm.getNewForumName(), mainForm.getNewForumPassword(), 
									id, ""+df.format(d));		
						
							if( insertValue(f, dataBaseConfig) ){							
								mainForm.reset(mapping, request);
							}else{
								errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
							}		
						}else{
							errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
						}
					}catch(Exception e){
						errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
					}
		        }				
			}
		}		
		
		if (!errors.isEmpty()) {
	    	saveErrors(request, errors);	    	
	    	return (mapping.getInputForward());
		}
		
		return mapping.findForward(finals.Final.FORWARD_SUCCESS);
	}
	
	
	/**
	 * <p>Вставка данных</p>
	 */
	public boolean insertValue(Forum f, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу				
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.insertForum_8(f);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Deleting data</p>
	 */
	public boolean deleteValue(int code, DataBaseConfig dataBaseConfig){
		
		boolean queryBnInsert = false;
		try{			
			String query3 = DataBaseQuery.deleteComment_Object_17(code);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query3);
			
			String query2 = DataBaseQuery.deleteComment_9(code);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query2);
			
			String query = DataBaseQuery.deleteForum_8(code);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			
			System.out.println(e);
			return queryBnInsert;
		}
	}
}
