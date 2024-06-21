package forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.CategoryTable;
import beans.ProductTable;

/**
 * <p>Это форма для показа таблицы продуктов</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class MainForm extends ActionForm{
		
	private int category = 0;
	private String search = null;    
	private int chunkSize = finals.CardFileFinal.CHUNK_SIZE_VALUE;
	private int start = 0;
	private String orderBy = null;
	private String reverse = null;
	private Collection<CategoryTable> categoryTableValue;
	private Collection<ProductTable> productTableValue;
	
	
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
    	this.category = 0;
    	this.search = null;    
    	this.chunkSize = finals.CardFileFinal.CHUNK_SIZE_VALUE;
    	this.start = 0;
    	this.orderBy = null;
    	this.reverse = null;
    	this.categoryTableValue = null;    	
    }
		
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();
    	
		// !!! Проверка выполняется, но пользователю не выдается сообщение об ошибке, а
		// устанавливается значение по-умолчанию 
        if (chunkSize < 1){        
        	errors.add(finals.CardFileFinal.CHUNK_SIZE, new ActionMessage(finals.CardFileFinal.ERROR_CHUNK_SIZE, chunkSize));
        	this.setChunkSize(finals.CardFileFinal.CHUNK_SIZE_VALUE);
        }
        
        return null;
    }
	
	
	public Collection<ProductTable> getProductTableValue() {
		return productTableValue;
	}
	public void setProductTableValue(Collection<ProductTable> productTableValue) {
		this.productTableValue = productTableValue;
	}
	public Collection<CategoryTable> getCategoryTableValue() {
		return categoryTableValue;
	}
	public void setCategoryTableValue(Collection<CategoryTable> categoryTableValue) {
		this.categoryTableValue = categoryTableValue;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getChunkSize() {
		return chunkSize;
	}
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getReverse() {
		return reverse;
	}
	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
}
