package finals;

/**
 * <p>Final класс для хранения констант</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public final class CardFileFinal {
	
	public static final String DATA_BASE_CONFIG = "dataBaseConfig";
	
	// Размер выборки
	public static final int CHUNK_SIZE_VALUE = 10;
	
	// * All Product
	public static final String ZERO = "0";
	public static final String ASTERISK = "*";	
	
	// FORWARDS ----------------------------------------------------------------------	
		
		public static final String FORWARD_SUCCESS = "success";
	// ----------------------------------------------------------------------
	
	// BEANS ----------------------------------------------------------------------
		
		public static final String SEARCH = "search";
		public static final String ORDER_BY = "orderBy";
		public static final String REVERSE = "reverse";	
		public static final String CHUNK_SIZE = "chunkSize";
		public static final String START = "start";
		
		// Product	
		public static final String CODE = "code";
		public static final String PRODUCT_NAME = "productName";
		public static final String DESCRIPTION = "description";
		public static final String PRICE = "price";
		public static final String CATEGORY = "category";
				
		public static final String CATEGORY_TABLE_VALUE = "categoryTableValue";
		public static final String PRODUCT_TABLE_VALUE = "productTableValue";	
		public static final String PRODUCT_TABLE_SIZE = "productTableSize";
	// ----------------------------------------------------------------------
	
	// ACTIONS ----------------------------------------------------------------------
		
		// Action edit {
			public static final String CODE_ADD = "code_add";
			public static final String PRODUCT_NAME_ADD = "productName_add";	
		
			public static final String CODE_DELETE = "code_delete";
		
			public static final String CODE_EDIT = "code_edit";
			public static final String PRODUCT_NAME__EDIT = "productName_edit";
		// }		
		
		// Action edit button
		public static final String BUTTON_EDIT = "buttonEdit";
		public static final String BUTTON_SHOW = "buttonShow";
				
		// Action edit success	
		public static final String STRING_DELETED = "stringDeleted";
		public static final String STRING_INSERTED = "stringInserted";
		public static final String STRING_EDITED = "stringEdited";
	// ----------------------------------------------------------------------
	
	// ERRORS ----------------------------------------------------------------------
	
		public static final String ERROR_DB = "error.db";			
		public static final String ERROR_NO_VALUE = "error.noValue";
		public static final String ERROR_CHUNK_SIZE = "error.chunkSize";
	
		// Modify product messages {
			public static final String ERROR_MODIFY_PRODUCT__CODE = "error.modifyProduct.code";
			public static final String ERROR_MODIFY_PRODUCT__PRODUCT_NAME = "error.modifyProduct.productName";
			public static final String ERROR_MODIFY_PRODUCT__PRICE_NUMBER = "error.modifyProduct.price.number";
			public static final String ERROR_MODIFY_PRODUCT__PRICE_STRING = "error.modifyProduct.price.string";
		// }
			
		// Insert Product {
			public static final String ERROR_CODE_ADD = "error.code.add";
			public static final String ERROR_PRODUCT_NAME_ADD = "error.productName.add";
			public static final String ERROR_PRICE_ADD = "error.price.add";
			
			public static final String ERROR_INSERT = "error.insert";
		// }
			
		// Delete Product {
			public static final String ERROR_CODE_DELETE = "error.code.delete";
			
			public static final String ERROR_DELETE = "error.delete";
		// }	
			
		// Edit Product {
			public static final String ERROR_CODE_EDIT = "error.code.edit";
			public static final String ERROR_CODE_EDIT_NO_VALUE_FOR_SHOW = "error.code.edit.noValueForShow";
			public static final String ERROR_PRODUCT_NAME_EDIT = "error.productName.edit";
			public static final String ERROR_PRICE_EDIT = "error.price.edit";
			
			public static final String ERROR_EDIT = "error.edit";
		// }		
	// ----------------------------------------------------------------------
}
