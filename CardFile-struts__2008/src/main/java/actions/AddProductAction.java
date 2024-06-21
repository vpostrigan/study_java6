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

import beans.ProductTable;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.AddProductForm;

/**
 * <p>Этот Action класс выполняет вставку данных в БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class AddProductAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.CardFileFinal.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		AddProductForm modifyProductForm = (AddProductForm) form;
		
		if(form != null){						
						
			if( insertValue(modifyProductForm, dataBaseConfig) ){
				
				// Код и название добавленного товара
				request.setAttribute(finals.CardFileFinal.CODE_ADD, modifyProductForm.getCode());
				request.setAttribute(finals.CardFileFinal.PRODUCT_NAME_ADD, modifyProductForm.getProductName());
				
				modifyProductForm.reset(mapping, request);
				
				// Данные добавлены, STRING_INSERTED = true
				PropertyUtils.setSimpleProperty(modifyProductForm, finals.CardFileFinal.STRING_INSERTED, true);
			}else{
				errors.add(finals.CardFileFinal.ERROR_INSERT, new ActionMessage(finals.CardFileFinal.ERROR_INSERT));
			}
		}
				
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.CardFileFinal.FORWARD_SUCCESS));
	}
	
	
	/**
	 * <p>Добавление данных в БД</p>
	 * @param modifyProductForm Строка для добавления
	 * @param dataBaseConfig Настройки соединения с БД
	 * @return true если запрос выполнен
	 */
	public boolean insertValue(AddProductForm modifyProductForm, DataBaseConfig dataBaseConfig){
		
		ProductTable productTable = new ProductTable(); 
		productTable.setCode("" + modifyProductForm.getCode());
		productTable.setProductName(modifyProductForm.getProductName());
		productTable.setDescription(modifyProductForm.getDescription());
		productTable.setPrice("" + modifyProductForm.getPrice());
		productTable.setCategory("" + modifyProductForm.getCategory());
		
		// Запрос на вставку данных в таблицу Product
		String query = DataBaseQuery.queryInsetProduct(productTable);
		
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на вставку данных
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
