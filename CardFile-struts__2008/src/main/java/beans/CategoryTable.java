package beans;

/**
 * <p>Bean-класс содержит номер категории товара и ее название</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class CategoryTable {
	
	private String idCategory;
	private String categoryName;
	
	public CategoryTable(){
		
	}
	
	public CategoryTable(String idCategory, String categoryName){
		this.idCategory = idCategory;
		this.categoryName = categoryName;		
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
}
