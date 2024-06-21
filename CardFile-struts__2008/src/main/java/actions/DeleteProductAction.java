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

import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.DeleteProductForm;

/**
 * <p>Этот Action класс выполняет удаление данных из БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class DeleteProductAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.CardFileFinal.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		DeleteProductForm deleteProductForm = (DeleteProductForm) form;
		
		if(form != null){						
						
			if( deleteValue(deleteProductForm, dataBaseConfig) ){
				
				// Код удаленного товара
				request.setAttribute(finals.CardFileFinal.CODE_DELETE, deleteProductForm.getCode());
				
				deleteProductForm.reset(mapping, request);
				
				// Данные удалены, STRING_DELETED = true
				PropertyUtils.setSimpleProperty(deleteProductForm, finals.CardFileFinal.STRING_DELETED, true);
			}else{
				errors.add(finals.CardFileFinal.ERROR_DELETE, new ActionMessage(finals.CardFileFinal.ERROR_DELETE));
			}
		}
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.CardFileFinal.FORWARD_SUCCESS));
	}
	
	
	/**
	 * <p>Удаление данных из БД<p>
	 * @param modifyProductForm Строка для удаления
	 * @param dataBaseConfig Настройки соединения с БД
	 * @return true если запрос выполнен
	 */
	public boolean deleteValue(DeleteProductForm deleteProductForm, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу Product
		String query = DataBaseQuery.queryDeleteProduct( deleteProductForm.getCode() );
		
		boolean queryBnInsert = false;
		try{
			// Отправка запроса на удаление данных
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
