package forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import actions.CategoryTableValue;
import beans.CategoryTable;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;

/**
 * <p>Этот форма для добавления продуктов</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class AddProductForm extends ActionForm {
	
	private int code;
	private String productName;
	private String description;
	private String price;
	private int category;
	
	Collection<CategoryTable> categoryTableValue = null;
	
	private boolean stringInserted;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {		
		this.productName = null;
		this.description = null;
		this.price = null;
		this.category = 0;	
		this.stringInserted = false;
		
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.CardFileFinal.DATA_BASE_CONFIG);
		
		try{
			String query = DataBaseQuery.queryFreeIDProduct(); 
			// Установка свободного ID для нового продукта
			this.code = DataBase.getInstance(dataBaseConfig).getFreeID(query);
		}catch(Exception e){
			this.code = 0;
		}
		
		try{
			// Список категорий
			this.categoryTableValue = 
				(new CategoryTableValue(DataBaseQuery.query1, dataBaseConfig)).getCategoryTable();			
		}catch(Exception e){
			this.categoryTableValue = null;
		}
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();
    	
        // Проверка поля 'code'
        if (code < 1){        
        	errors.add(finals.CardFileFinal.ERROR_CODE_ADD, new ActionMessage(finals.CardFileFinal.ERROR_MODIFY_PRODUCT__CODE, code));
        }
        
        // Проверка поля 'productName'
        if (productName == null || "".equals(productName)){
        	errors.add(finals.CardFileFinal.ERROR_PRODUCT_NAME_ADD, new ActionMessage(finals.CardFileFinal.ERROR_MODIFY_PRODUCT__PRODUCT_NAME, productName));
        }
        
        // Проверка поля 'price'
        try{
        	float priceValue = Float.parseFloat(price);
        	if (priceValue < 0){    
        		// Цена не может быть отрицательной
            	errors.add(finals.CardFileFinal.ERROR_PRICE_ADD, new ActionMessage(finals.CardFileFinal.ERROR_MODIFY_PRODUCT__PRICE_NUMBER, price));
            }
        }catch(Exception e){
        	// Вместо цены введена строка
        	errors.add(finals.CardFileFinal.ERROR_PRICE_ADD, new ActionMessage(finals.CardFileFinal.ERROR_MODIFY_PRODUCT__PRICE_STRING, price));
        }
        
        if (errors.isEmpty()) return null;        
        return errors;
    }

	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}	
	public boolean isStringInserted() {
		return stringInserted;
	}
	public void setStringInserted(boolean stringInserted) {
		this.stringInserted = stringInserted;
	}
	public Collection<CategoryTable> getCategoryTableValue() {
		return categoryTableValue;
	}
	public void setCategoryTableValue(Collection<CategoryTable> categoryTableValue) {
		this.categoryTableValue = categoryTableValue;
	}	
}
