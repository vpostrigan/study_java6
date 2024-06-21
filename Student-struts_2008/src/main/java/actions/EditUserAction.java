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
import org.apache.struts.action.ActionMessage;

import beans.People;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.EditUserForm;

public class EditUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		EditUserForm editUserForm = (EditUserForm) form;
		
		// Нажатая кнопка
		String buttonEdit = request.getParameter(finals.Final.BUTTON_EDIT);
		String buttonShow = request.getParameter(finals.Final.BUTTON_SHOW);
		
		if(buttonEdit != null){
			// buttonEdit was clicked
			
			if(form != null){
				if( editValue(editUserForm, dataBaseConfig) ){
					
					// Код и название измененного товара
					request.setAttribute(finals.Final.CODE_COMMIT_EDIT, editUserForm.getPeople().getId_People());
					request.setAttribute(finals.Final.NAME_COMMIT_EDIT, editUserForm.getPeople().getSurname());
					
					editUserForm.reset(mapping, request);
					
					// Данные изменены, STRING_EDITED = true
					PropertyUtils.setSimpleProperty(editUserForm, finals.Final.STRING_EDITED, true);
				}else{
					errors.add(finals.Final.ERROR_EDIT, new ActionMessage(finals.Final.ERROR_EDIT));
				}
			}
		}
		else if(buttonShow != null){
			// buttonShow was clicked
			
			// Запрос на получение данных по введенному коду из таблицы Product
			String query = DataBaseQuery.getProductOneRecord(editUserForm.getPeople().getId_People());
			
			try{
				Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
				
				People p = new People();
				p.setId_People( Integer.parseInt((temp.elementAt(0)).elementAt(0)) );
				p.setSurname( (temp.elementAt(0)).elementAt(1) );
				p.setName( (temp.elementAt(0)).elementAt(2) );
				p.setPatronymic( (temp.elementAt(0)).elementAt(3) );
				p.setLogin( (temp.elementAt(0)).elementAt(4) );
				p.setPassword( (temp.elementAt(0)).elementAt(5) );
				p.setPeople_type( (temp.elementAt(0)).elementAt(6) );
				p.setAge( Integer.parseInt((temp.elementAt(0)).elementAt(7)) );
				p.setAddress( (temp.elementAt(0)).elementAt(8) );
				
				// Заполение формы
				PropertyUtils.setSimpleProperty(editUserForm, finals.Final.PEOPLE, p);
			}catch(Exception e){
				
				editUserForm.reset(mapping, request);
				
				// Дозаполнить форму нельзя, отсутствует такая запись
				errors.add(finals.Final.ERROR_CODE_NO_VALUE_FOR_SHOW_EDIT, new ActionMessage(finals.Final.ERROR_NO_VALUE));
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
	public boolean editValue(EditUserForm editUserForm, DataBaseConfig dataBaseConfig){
		
		int oldCode = editUserForm.getPeople().getId_People();
		
		People p = new People();
		p.setId_People( editUserForm.getPeople().getId_People() );
		p.setSurname( editUserForm.getPeople().getSurname() );
		p.setName( editUserForm.getPeople().getName() );
		p.setPatronymic( editUserForm.getPeople().getPatronymic() );
		p.setLogin( editUserForm.getPeople().getLogin() );
		p.setPassword( editUserForm.getPeople().getPassword() );
		p.setPeople_type( editUserForm.getPeople().getPeople_type() );
		p.setAge( editUserForm.getPeople().getAge() );
		p.setAddress( editUserForm.getPeople().getAddress() );
		
		// Запрос на обновление данных
		String query = DataBaseQuery.updatePeople(p, oldCode);
		
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
