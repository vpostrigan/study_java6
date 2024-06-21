package beans;

/**
 * <p>Bean-класс содержит код товара, название, описание, цену и категорию товара</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class ProductTable {
	private String code;
	private String productName;
	private String description;
	private String price;
	private String category;
	
	public ProductTable(){
		
	}
	
	public ProductTable(String code, String productName, String description, String price,
			String category){
		this.code = code;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
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
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}
