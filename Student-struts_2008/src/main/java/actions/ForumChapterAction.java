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

import beans.Comment;
import beans.DataObject;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.ForumChapterForm;

public class ForumChapterAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		ForumChapterForm forumChapterForm = (ForumChapterForm) form;
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		String buttonAdd = request.getParameter(finals.Final.BUTTON_ADD);
		String buttonUpload = request.getParameter(finals.Final.BUTTON_UPLOAD);
		
		String queryId = database.DataBaseQuery.authorizationLogin(login);	
		int id_People = 0;
		try{
			id_People = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
		}catch(Exception e){
			
		}
		
		int start = 0;
		int chunkSize = 0;
		
		if(form != null){			
			
			start = forumChapterForm.getStart();
			chunkSize = forumChapterForm.getChunkSize();
			
			if(buttonAdd != null){
				// buttonAdd was clicked
				
				try{
					if( newMessage(forumChapterForm, id_People, session, dataBaseConfig) ){							
							forumChapterForm.reset(mapping, request);
					}else{
						errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
					}					
				}catch(Exception e){
					errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
				}
			}
		
		
			if(buttonUpload != null){
				// buttonUpload was clicked

				FormFile file = forumChapterForm.getFile();
				
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
					
					ArrayList<DataObject> dataObject;
					if( forumChapterForm.getNewComment().getDataObject() != null){
						
						dataObject = forumChapterForm.getNewComment().getDataObject();
					}else{
						dataObject = new ArrayList<DataObject>();
					}
					
					int idData_Object_21 = 0;
					try{
						String freeId = DataBaseQuery.getData_Object_21_FreeID();
						idData_Object_21 = DataBase.getInstance(dataBaseConfig).getFreeID(freeId);
					
						String insert = DataBaseQuery.insertData_Object_21(idData_Object_21, value);
						DataBase.getInstance(dataBaseConfig).setData(insert);
					}catch(Exception e){
						errors.add(finals.Final.ERROR_FILE_EDIT, new ActionMessage(finals.Final.ERROR_FILE));
					}	
					
					DataObject dO = new DataObject(idData_Object_21, value, value.substring(value.lastIndexOf('/') + 1));
					dataObject.add(dO);
										
					forumChapterForm.getNewComment().setDataObject(dataObject);
					
					try{
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.START, start);
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.CHUNK_SIZE, chunkSize);
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.NEW_COMMENT, forumChapterForm.getNewComment());						
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.ID_CHAPTER, forumChapterForm.getId_Chapter());
						
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.ID_OBJECT, dO.getId_Object());
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.OBJECT_NAME, dO.getObjectName());
						PropertyUtils.setSimpleProperty(forumChapterForm, finals.Final.OBJECT, dO.getObject());
						forumChapterForm.reset(mapping, request);
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
		}
		
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
	
	
	
	/**
	 * <p>Добавление нового сообщения</p>
	 */
	public boolean newMessage(ForumChapterForm forumChapterForm, int id_People, HttpSession session, 
			DataBaseConfig dataBaseConfig){		
		Date d = new Date();
	    DateFormat df = new SimpleDateFormat(finals.Final.SIMPLE_DATE_FORMAT);
	    
		try{
			int id_Chapter = forumChapterForm.getId_Chapter();
			int commentNumber = forumChapterForm.getComment().size()+1;
			String commentValue = forumChapterForm.getNewComment().getCommentValue();
			String commentDate = "" + df.format(d);
			
			String login = (String)session.getAttribute(finals.Final.LOGIN);
			String queryId = database.DataBaseQuery.authorizationLogin(login);	
			int id = 0;
			try{
				id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
			}catch(Exception e){
				
			}
			
			Comment c = new Comment(id_Chapter, commentNumber, id, commentValue, commentDate, "", "");
			String in = DataBaseQuery.insertComment_9(c);
			DataBase.getInstance(dataBaseConfig).setData(in);
			   
			if ( forumChapterForm.getId_Object() != 0 ){
				String inid_Chap = 
					DataBaseQuery.insertComment_Object_17(id_Chapter, commentNumber, forumChapterForm.getId_Object());
				DataBase.getInstance(dataBaseConfig).setData(inid_Chap);
			}
		}catch(Exception e){
			
		}	
		
		return true;
	}
}
