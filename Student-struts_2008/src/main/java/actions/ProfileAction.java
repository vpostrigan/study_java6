package actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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

import beans.People;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.ProfileForm;

public class ProfileAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		ProfileForm myForm = (ProfileForm)form;		
		HttpSession session = request.getSession();
		
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		String queryId = database.DataBaseQuery.authorizationLogin(login);	
		int id = 0;
		try{
			id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
		}catch(Exception e){
			
		}
		
		if(form != null){
			
			String buttonUpload = request.getParameter(finals.Final.BUTTON_UPLOAD);
			String buttonEdit = request.getParameter(finals.Final.BUTTON_EDIT);
			
			String buttonDeleteEmail = request.getParameter(finals.Final.BUTTON_DELETE_EMAIL);
			String buttonAddEmail = request.getParameter(finals.Final.BUTTON_ADD_EMAIL);
			
			String buttonDeletePhone = request.getParameter(finals.Final.BUTTON_DELETE_PHONE);
			String buttonAddPhone = request.getParameter(finals.Final.BUTTON_ADD_PHONE);
			
			String buttonDeleteEmployment = request.getParameter(finals.Final.BUTTON_DELETE_EMPLOYMENT);
			String buttonAddEmployment = request.getParameter(finals.Final.BUTTON_ADD_EMPLOYMENT);
			
			if(buttonEdit != null){
				// buttonEdit was clicked
				
				if( editValue(myForm, dataBaseConfig) ){
					
					myForm.reset(mapping, request);
					
					// Данные изменены, STRING_EDITED = true
					try{
						PropertyUtils.setSimpleProperty(myForm, finals.Final.STRING_EDITED, true);
					}catch(Exception e){
						
					}
				}else{
					errors.add(finals.Final.ERROR_EDIT, new ActionMessage(finals.Final.ERROR_EDIT));
				}
			}
			else if(buttonUpload != null){
				// buttonUpload was clicked
				
				FormFile file = myForm.getFile();
				
				InputStream in = null;
				OutputStream out = null;
				
				try{
					String path = servlet.getServletContext().getRealPath( finals.Final.PATH_NAME );
					
					File f = new File(path, session.getAttribute(finals.Final.LOGIN).toString());
					f.mkdir();
					String avatar=finals.Final.PATH_STUDENT + session.getAttribute(finals.Final.LOGIN).toString()+"/"+file.getFileName();
					
					File realFile = new File(f.getPath() + "\\" + file.getFileName());
					
					// Передача данных
					in = file.getInputStream();
					out = new BufferedOutputStream( new FileOutputStream( realFile ) );
        	
					byte[] buffer = new byte[512];
					while (in.read(buffer) != -1){
						out.write(buffer);
					}
								
					
					// Запрос на изменение аватары
					String updateAvatar = DataBaseQuery.updatePeopleAvatar(avatar, id);			
					
					try{
						// Отправка запроса на удаление данных
						DataBase.getInstance(dataBaseConfig).setData( updateAvatar );
					}catch(Exception e){
						
					}
					
					myForm.reset(mapping, request);
					
				}catch(Exception e){
					errors.add(finals.Final.ERROR_FILE_EDIT, new ActionMessage(finals.Final.ERROR_FILE));
				}finally{
			
					try{
						if(out != null) out.close();
						if(in != null) in.close();
					}catch(Exception e){
						
					}	
				}
			}else if(buttonDeleteEmail != null){
				// buttonDeleteEmail was clicked
				
				try{
					String temp[] = myForm.getSelectedItemsEmail();
					for(int i=0; i < temp.length; i++){
						
						if( deletePeopleEmail(id, temp[i], dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_EMAIL_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}
				}catch(Exception e){
					errors.add(finals.Final.ERROR_EMAIL_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}else if(buttonAddEmail != null){
				// buttonAddEmail was clicked
				
				try{
					if(!"".equals(myForm.getAddEmail())){
						if( addPeopleEmail(id, myForm.getAddEmail(), dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_EMAIL_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
						}	
					}else{ throw new Exception(); }
				}catch(Exception e){
					errors.add(finals.Final.ERROR_EMAIL_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
				}
			}else if(buttonDeletePhone != null){
				// buttonDeletePhone was clicked
				
				try{
					String temp[] = myForm.getSelectedItemsPhone();
					for(int i=0; i < temp.length; i++){	
						
						if( deletePeoplePhone(id, temp[i], dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_PHONE_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}
				}catch(Exception e){
					errors.add(finals.Final.ERROR_PHONE_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}else if(buttonAddPhone != null){
				// buttonAddPhone was clicked
				
				try{
					if(!"".equals(myForm.getAddPhone())){
						if( addPeoplePhone(id, myForm.getAddPhone(), dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_PHONE_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
						}	
					}else{ throw new Exception(); }	
				}catch(Exception e){
					errors.add(finals.Final.ERROR_PHONE_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
				}
			}else if(buttonDeleteEmployment != null){
				// buttonDeleteEmployment was clicked
				
				try{
					String temp[] = myForm.getSelectedItemsEmployment();
					for(int i=0; i < temp.length; i++){	
						
						if( deletePeopleEmployment(id, temp[i], dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_EMPLOYMENT_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}
				}catch(Exception e){
					errors.add(finals.Final.ERROR_EMPLOYMENT_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}else if(buttonAddEmployment != null){
				// buttonAddEmployment was clicked
				
				try{
					if(!"".equals(myForm.getAddEmployment())){
						if( addPeopleEmployment(id, myForm.getAddEmployment(), dataBaseConfig) ){							
							myForm.reset(mapping, request);
						}else{
							errors.add(finals.Final.ERROR_EMPLOYMENT_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
						}	
					}else{ throw new Exception(); }										
				}catch(Exception e){
					errors.add(finals.Final.ERROR_EMPLOYMENT_ADD, new ActionMessage(finals.Final.ERROR_INSERT));
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
	 * <p>Изменение данных в БД</p>
	 * @return true если запрос выполнен нормально
	 */
	public boolean editValue(ProfileForm profileForm, DataBaseConfig dataBaseConfig){
		
		int oldCode = profileForm.getPeople().getId_People();
		
		People p = new People();
		p.setId_People( profileForm.getPeople().getId_People() );
		p.setSurname( profileForm.getPeople().getSurname() );
		p.setName( profileForm.getPeople().getName() );
		p.setPatronymic( profileForm.getPeople().getPatronymic() );
		p.setLogin( profileForm.getPeople().getLogin() );
		p.setPassword( profileForm.getPeople().getPassword() );
		p.setPeople_type( profileForm.getPeople().getPeople_type() );
		p.setAge( profileForm.getPeople().getAge() );
		p.setAddress( profileForm.getPeople().getAddress() );
				
		// Запрос на вставку данных в таблицу
		String query = DataBaseQuery.updatePeople(p, oldCode);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	public boolean deletePeopleEmail(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных
		String query = DataBaseQuery.deletePeople_Email_19(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	public boolean addPeopleEmail(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу
		String query = DataBaseQuery.insertPeople_Email_19(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	//
	public boolean deletePeoplePhone(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных
		String query = DataBaseQuery.deletePeople_Phone_2(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	public boolean addPeoplePhone(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу
		String query = DataBaseQuery.insertPeople_Phone_2(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	//
	public boolean deletePeopleEmployment(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных
		String query = DataBaseQuery.deletePeople_Employment_20(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	public boolean addPeopleEmployment(int id, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу
		String query = DataBaseQuery.insertPeople_Employment_20(id, value);
		return queryBnInsert(query, dataBaseConfig);
	}
	
	
	public boolean queryBnInsert(String query, DataBaseConfig dataBaseConfig){
		boolean queryInsert = false;
		try{
			// Отправка запроса на внесение изменений
			queryInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			return queryInsert;
		}catch(Exception e){
			return queryInsert;
		}
	}
}
