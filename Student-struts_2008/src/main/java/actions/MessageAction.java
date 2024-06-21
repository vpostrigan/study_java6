package actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import beans.MenuList;
import beans.Message;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.MessageForm;

public class MessageAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();		
		MessageForm messsageForm = (MessageForm)form;
		HttpSession session = request.getSession();
		
		String buttonDelete = request.getParameter(finals.Final.BUTTON_DELETE);
		String buttonAdd = request.getParameter(finals.Final.BUTTON_ADD);
		String buttonUpload = request.getParameter(finals.Final.BUTTON_UPLOAD);
		
		int start = 0;
		int chunkSize = 0;
		
		if(form != null){
			
			start = messsageForm.getStart();
			chunkSize = messsageForm.getChunkSize();
			
			if(messsageForm.getId_Message() != 0){
				int id_Message = messsageForm.getId_Message();
				
				// Чтение выбранного сообщения
				String query = database.DataBaseQuery.getMessage_Message(id_Message);				
				Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
				
				Message messageRead = new Message(temp.get(0).get(0), temp.get(0).get(1), temp.get(0).get(2));
				
				String query2 = database.DataBaseQuery.getData_Object_21(id_Message);				
				Vector<Vector<String>> temp2 = DataBase.getInstance(dataBaseConfig).getData(query2);
				
				Iterator<Vector<String>> temp2It = temp2.iterator();	
				
				ArrayList<MenuList> menuList = new ArrayList<MenuList>();
				while(temp2It.hasNext()){
					Vector<String> tempLine = temp2It.next();
					
					String name = tempLine.get(0).substring( tempLine.get(0).lastIndexOf('/') + 1 );
					
					MenuList m = new MenuList(tempLine.get(0), name);
					
					menuList.add(m);
				}
				
				try{
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.START, start);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.CHUNK_SIZE, chunkSize);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MESSAGE_READ, messageRead);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MENU_LIST, menuList);
				}catch(Exception e){
					
				}
			}
			
			if(messsageForm.isNewMessage() == true){
				// Новое сообщение
				
				Message messageNew = new Message();
				ArrayList<MenuList> menuListNew = new ArrayList<MenuList>();
				
				try{
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.START, start);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.CHUNK_SIZE, chunkSize);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MESSAGE_NEW, messageNew);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MENU_LIST_NEW, menuListNew);
				}catch(Exception e){
					
				}
			}
			
			if(buttonDelete != null){
				// buttonDelete was clicked
				
				try{
					String temp[] = messsageForm.getSelectedItems();
					
					for(int i=0; i < temp.length; i++){			
						
						if( delete(Integer.parseInt(temp[i]), dataBaseConfig) ){							
							messsageForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}
					}
				}catch(Exception e){
					errors.add(finals.Final.ERROR_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}
			
			if(buttonAdd != null){
				// buttonAdd was clicked
				
				boolean check = true;
				// Проверка поля 'theme'
		        if ( messsageForm.getMessageNew().getTheme() == null || "".equals(messsageForm.getMessageNew().getTheme()) ){
		        	errors.add(finals.Final.ERROR_MESSAGE_NEW_THEME_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_NEW_THEME));
		        	check = false;
		        }
				
		        if(check){
				
		        	try{					
		        		if( newMessage(messsageForm, session, dataBaseConfig) ){							
							messsageForm.reset(mapping, request);
		        		}else{
		        			errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        		}					
		        	}catch(Exception e){
		        		errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        	}	
		        }
			}
		}
		
		if(buttonUpload != null){
			// buttonUpload was clicked

			FormFile file = messsageForm.getFile();
			
			InputStream in = null;
			OutputStream out = null;
			
			try{
				String path = servlet.getServletContext().getRealPath( finals.Final.PATH_NAME );
				
				File f = new File(path, session.getAttribute(finals.Final.LOGIN).toString());
				f.mkdir();
				String value=finals.Final.PATH_STUDENT + session.getAttribute(finals.Final.LOGIN).toString()+"/"+file.getFileName();
				
				File realFile = new File(f.getPath() + "\\" + file.getFileName());
				
				// Передача данных
				in = file.getInputStream();
				out = new BufferedOutputStream( new FileOutputStream( realFile ) );
				
				byte[] buffer = new byte[512];
				while (in.read(buffer) != -1){
					out.write(buffer);
				}
				
				ArrayList<MenuList> menuListNew = (ArrayList<MenuList>) session.getAttribute(finals.Final.MENU_LIST_NEW);
				if( messsageForm.getMenuListNew() != null ){
					menuListNew = messsageForm.getMenuListNew();
				}else{
					menuListNew = new ArrayList<MenuList>();
				}
				
				session.setAttribute(finals.Final.MENU_LIST_NEW, menuListNew);
				
				MenuList m = new MenuList( value, value.substring(value.lastIndexOf('/') + 1) );
				menuListNew.add(m);
				session.setAttribute(finals.Final.MENU_LIST_NEW, menuListNew);
				
				Message messageNew = new Message();
				
				try{
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.START, start);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.CHUNK_SIZE, chunkSize);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MESSAGE_NEW, messageNew);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.MENU_LIST_NEW, menuListNew);
					PropertyUtils.setSimpleProperty(messsageForm, finals.Final.NEW_MESSAGE, true);
				}catch(Exception e){
					errors.add(finals.Final.ERROR_FILE_EDIT, new ActionMessage(finals.Final.ERROR_FILE));
				}	
				
				
				try{
					String freeId = DataBaseQuery.getData_Object_21_FreeID();
					int id = DataBase.getInstance(dataBaseConfig).getFreeID(freeId);
					
					String insert = DataBaseQuery.insertData_Object_21(id, value);
					DataBase.getInstance(dataBaseConfig).setData(insert);
				}catch(Exception e){
					errors.add(finals.Final.ERROR_FILE_EDIT, new ActionMessage(finals.Final.ERROR_FILE));
				}				
			}catch(Exception e){
				errors.add(finals.Final.ERROR_FILE_EDIT, new ActionMessage(finals.Final.ERROR_FILE));
			}finally{
				
				try{
					if(out != null) out.close();
					if(in != null) in.close();
				}catch(Exception e){
					
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
	 * <p>Удаление данных из БД<p>
	 */
	public boolean delete(int code, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных из таблиц
		String deleteMessage_7 = DataBaseQuery.deleteMessage_7(code);		
		String deleteMessage_Object_23 = DataBaseQuery.deleteMessage_Object_23(code);
		
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на удаление данных
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteMessage_Object_23 );
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData( deleteMessage_7 );
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Добавление нового сообщения</p>
	 */
	public boolean newMessage(MessageForm messageForm, HttpSession session, DataBaseConfig dataBaseConfig){		
		Date d = new Date();
	    DateFormat df = new SimpleDateFormat(finals.Final.SIMPLE_DATE_FORMAT);
		
		String attach = null;
		try{
			attach = ((ArrayList<MenuList>) session.getAttribute(finals.Final.MENU_LIST_NEW)).get(0).getMenuItem();
		}catch(Exception e){
			
		}
		
		try{
			if(attach == null || "".equals(attach)){
				String freeId_Message = DataBaseQuery.getMessage_7_FreeID();
				int id_Message = DataBase.getInstance(dataBaseConfig).getFreeID(freeId_Message);
				
				String login = (String)session.getAttribute(finals.Final.LOGIN);
				String queryId = database.DataBaseQuery.authorizationLogin(login);	
				int id = 0;
				try{
					id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
				}catch(Exception e){
					
				}
				
				messageForm.getMessageNew().setId_Message(id_Message);
				messageForm.getMessageNew().setId_People_Creator(id);
				
				messageForm.getMessageNew().setMessage_Date(""+df.format(d));
				
				String insertMessage = DataBaseQuery.insertMessage_7(messageForm.getMessageNew());
				
				DataBase.getInstance(dataBaseConfig).setData(insertMessage);
			}else{
				String query = DataBaseQuery.getData_Object_21_One(attach);
				
				Vector<Vector<String>> s = DataBase.getInstance(dataBaseConfig).getData(query);
				
				String freeId_Message = DataBaseQuery.getMessage_7_FreeID();
				int id_Message = DataBase.getInstance(dataBaseConfig).getFreeID(freeId_Message);
				
				String login = (String)session.getAttribute(finals.Final.LOGIN);
				String queryId = database.DataBaseQuery.authorizationLogin(login);	
				int id = 0;
				try{
					id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
				}catch(Exception e){
					
				}
				
				messageForm.getMessageNew().setId_Message(id_Message);
				messageForm.getMessageNew().setId_People_Creator(id);
				
				messageForm.getMessageNew().setMessage_Date(""+df.format(d));
				
				String insertMessage = DataBaseQuery.insertMessage_7(messageForm.getMessageNew());
				
				DataBase.getInstance(dataBaseConfig).setData(insertMessage);
				
				String insertMessage_Object = DataBaseQuery.insertMessage_Object_23( id_Message, 
						Integer.parseInt(s.get(0).get(0)) );
				DataBase.getInstance(dataBaseConfig).setData(insertMessage_Object);
			}			
		}catch(Exception e){
			
		}	
		
		return true;
	}
}
