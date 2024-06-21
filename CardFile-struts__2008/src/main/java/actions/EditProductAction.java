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

import beans.ProductTable;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.EditProductForm;

/**
 * <p>Этот Action класс выполняет изменение данных в БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class EditProductAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.CardFileFinal.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		EditProductForm editProductForm = (EditProductForm) form;
		
		// Нажатая кнопка
		String buttonEdit = request.getParameter(finals.CardFileFinal.BUTTON_EDIT);
		String buttonShow = request.getParameter(finals.CardFileFinal.BUTTON_SHOW);
		
		if(buttonEdit != null){
			// buttonEdit was clicked
			
			if(form != null){						
			
				if( editValue(editProductForm, dataBaseConfig) ){
					
					// Код и название измененного товара
					request.setAttribute(finals.CardFileFinal.CODE_EDIT, editProductForm.getCode());
					request.setAttribute(finals.CardFileFinal.PRODUCT_NAME__EDIT, editProductForm.getProductName());
					
					editProductForm.reset(mapping, request);
					
					// Данные изменены, STRING_EDITED = true
					PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.STRING_EDITED, true);
				}else{
					errors.add(finals.CardFileFinal.ERROR_EDIT, new ActionMessage(finals.CardFileFinal.ERROR_EDIT));
				}
			}
		}
		else if(buttonShow != null){
			// buttonShow was clicked
			
			// Запрос на получение данных по введенному коду из таблицы Product
			String query = DataBaseQuery.queryOneStringProduct(editProductForm.getCode());
			
			try{
				Vector<Vector<String>> Temp = 
					DataBase.getInstance(dataBaseConfig).getData(query);
				
				int code = Integer.parseInt((Temp.elementAt(0)).elementAt(0));
				String productName = (Temp.elementAt(0)).elementAt(1);
				String description = (Temp.elementAt(0)).elementAt(2);
				String price = (Temp.elementAt(0)).elementAt(3);
				int category = Integer.parseInt((Temp.elementAt(0)).elementAt(4));
				
				// Заполение формы
				PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.CODE, code);
				PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.PRODUCT_NAME, productName);
				PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.DESCRIPTION, description);
				PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.PRICE, price);
				PropertyUtils.setSimpleProperty(editProductForm, finals.CardFileFinal.CATEGORY, category);
			}catch(Exception e){
				
				editProductForm.reset(mapping, request);
				
				// Дозаполнить форму нельзя, отсутствует такой товар
				errors.add(finals.CardFileFinal.ERROR_CODE_EDIT_NO_VALUE_FOR_SHOW, new ActionMessage(finals.CardFileFinal.ERROR_NO_VALUE));
			}
		}
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.CardFileFinal.FORWARD_SUCCESS));
	}
	
	
	/**
	 * <p>Изменение данных в БД</p>
	 * @param modifyProductForm Данные передаваемые в БД
	 * @param dataBaseConfig Настройки соединения с БД
	 * @return true если запрос выполнен нормально
	 */
	public boolean editValue(EditProductForm editProductForm, DataBaseConfig dataBaseConfig){
		
		int oldCode = editProductForm.getCode();
		
		ProductTable productTable = new ProductTable(); 		
		productTable.setCode("" + editProductForm.getCode());
		productTable.setProductName(editProductForm.getProductName());
		productTable.setDescription(editProductForm.getDescription());
		productTable.setPrice("" + editProductForm.getPrice());
		productTable.setCategory("" + editProductForm.getCategory());
		
		// Запрос на вставку данных в таблицу Product
		String query = DataBaseQuery.queryUpdateProduct(productTable, oldCode);
		
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
