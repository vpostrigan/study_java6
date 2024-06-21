package actions;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.CategoryTable;
import beans.ProductTable;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.MainForm;

/**
 * <p>Этот Action класс выводит таблицу с продуктами</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class MainAction extends Action{
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.CardFileFinal.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		MainForm mainForm = (MainForm)form;
		
		int category = 0;
		String search = "";
		int chunkSize = finals.CardFileFinal.CHUNK_SIZE_VALUE;
		int start = 0;
		String orderBy = "";
		String reverse = "";
		Vector<CategoryTable> categoryTableValue = null;
		Collection<ProductTable> productTableValue = null;
		
		if(form != null){						
			category = mainForm.getCategory();
			search = mainForm.getSearch();
			chunkSize = mainForm.getChunkSize();
			start = mainForm.getStart();
			orderBy = mainForm.getOrderBy();
			reverse = mainForm.getReverse();
			productTableValue = mainForm.getProductTableValue();
		}
		
		boolean error = false;
		
		// Коллекия категорий товара		
		try{			
			categoryTableValue =			 
				(new CategoryTableValue(DataBaseQuery.query1, dataBaseConfig)).getCategoryTable();
			
			// Звездочкой (*) обозначаются все товары
			categoryTableValue.add(0, new CategoryTable(finals.CardFileFinal.ZERO, finals.CardFileFinal.ASTERISK) );			
		
			request.setAttribute(finals.CardFileFinal.CATEGORY_TABLE_VALUE, categoryTableValue);
		}catch(Exception e){
			error = true;
		}
		
		// Коллекия товаров
		try{			
			String q = this.getProductValueQuery(category, search, orderBy, reverse);
			productTableValue = new ProductTableValue( q, dataBaseConfig ).getProductTable();	
			
			request.setAttribute(finals.CardFileFinal.PRODUCT_TABLE_VALUE, productTableValue);
			request.setAttribute(finals.CardFileFinal.PRODUCT_TABLE_SIZE, productTableValue.size());
		}catch(Exception e){
			error = true;		
		}
				
		request.setAttribute(finals.CardFileFinal.CATEGORY, category);
		request.setAttribute(finals.CardFileFinal.SEARCH, search);
		request.setAttribute(finals.CardFileFinal.START, start);
		request.setAttribute(finals.CardFileFinal.CHUNK_SIZE, chunkSize);
		request.setAttribute(finals.CardFileFinal.ORDER_BY, orderBy);
		request.setAttribute(finals.CardFileFinal.REVERSE, reverse);
		
		
		// Ошибка при работе с БД	
		if(error) errors.add(finals.CardFileFinal.ERROR_DB, new ActionMessage(finals.CardFileFinal.ERROR_DB));
		
	    if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }	    
		
		return (mapping.findForward(finals.CardFileFinal.FORWARD_SUCCESS));
	}
	
	
	/**
	 * <p>Формирование строки запроса для получения коллекции продуктов</p>
	 * 
	 * @param selectedCategory Выбранная категория
	 * @param searchValue Строка поиска в БД
	 * @param orderBy Поле по которому будет происходить сортировка
	 * @param reverseValue Сортировка будет происходить в убывающем порядке 
	 * @return Запрос на получение товаров
	 */
	public String getProductValueQuery(int selectedCategory, String searchValue,
			String orderBy, String reverseValue){
		
		boolean reverse = false;
		
		// Проверка в какую сторону сортировать столбец, или не сортировать вовсе
		try{
			if( reverseValue != null || !("".equals(reverseValue)) ){
				if(reverseValue.equals(orderBy)){
					reverse = true;
				}
			}
		}catch(Exception e){
			
		}
		
		// Поиск нужного запроса
		if( 0 == selectedCategory ){
			if( searchValue == null || "".equals(searchValue) ){						
				return DataBaseQuery.query2(orderBy, reverse);
			}else{
				return DataBaseQuery.query2(searchValue, orderBy, reverse);
			}
		}else{
			if( searchValue == null || "".equals(searchValue) ){
				return DataBaseQuery.query4(""+selectedCategory, orderBy, reverse);
			}else{
				return DataBaseQuery.query4(""+selectedCategory, searchValue, orderBy, reverse);
			}				 
		}
	}
	
}
