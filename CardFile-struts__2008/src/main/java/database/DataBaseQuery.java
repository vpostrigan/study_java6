package database;

import beans.ProductTable;

/**
 * <p>Этот класс хранит запросы к БД</p>
 * @author Postrigan V.A.
 * @version 3.0 
 */
public final class DataBaseQuery{

	private final static String               PRODUCT_1 = "Product_1";
	private final static String   PRODUCT_1__ID_PRODUCT = "Product_1.Id_Product";
	private final static String PRODUCT_1__PRODUCT_NAME = "Product_1.Product_Name";
	private final static String  PRODUCT_1__DESCRIPTION = "Product_1.Description";
	private final static String        PRODUCT_1__PRICE = "Product_1.Price";
	private final static String  PRODUCT_1__ID_CATEGORY = "Product_1.Id_Category";
	
	private final static String                CATEGORY_2 = "Category_2";
	private final static String   CATEGORY_2__ID_CATEGORY = "Category_2.Id_Category";
	private final static String CATEGORY_2__CATEGORY_NAME = "Category_2.Category_Name";
	
	private final static String CODE = "code";
	private final static String PRODUCT_NAME= "productName";
	private final static String DESCRIPTION = "description";
	private final static String PRICE = "price";
	private final static String CATEGORY = "category";
	
	/**
	 * <p>Строка запроса к БД (получение всех категорий)</p>
	 */
	public static String query1 = "SELECT " + CATEGORY_2__ID_CATEGORY + "," + 
											  CATEGORY_2__CATEGORY_NAME +
									" FROM " + CATEGORY_2 + 
									" ORDER BY " + CATEGORY_2__ID_CATEGORY;
	
	/**
	 * <p>Строка запроса к БД (получение всех продуктов)</p>
	 */
	public static String query2(String orderBy, boolean reverse){
		
		return 
		"SELECT " + PRODUCT_1__ID_PRODUCT +", "+ 
					PRODUCT_1__PRODUCT_NAME +","+
					PRODUCT_1__DESCRIPTION +", "+ 
					PRODUCT_1__PRICE +", "+
					CATEGORY_2__CATEGORY_NAME +" "+
		"FROM " + PRODUCT_1 + " INNER JOIN " + CATEGORY_2 + " " +
		"ON "+ PRODUCT_1__ID_CATEGORY +" = "+ CATEGORY_2__ID_CATEGORY + " " + 
		getOrderBy(orderBy, reverse);
	}
	
	/**
	 * <p>Этот метод получает строку запроса к БД. (Поиск в продуктах и их описании)</p>
	 * @param search Что будет искатся в БД
	 * @return String query2 Сформированный запрос
	 */
	public static String query2(String search, String orderBy, boolean reverse){ 
		
		return "SELECT " +  PRODUCT_1__ID_PRODUCT + ", " + 
							PRODUCT_1__PRODUCT_NAME + ", " + 
							PRODUCT_1__DESCRIPTION + ", "+ 
							PRODUCT_1__PRICE +", " + 
							CATEGORY_2__CATEGORY_NAME + " " +
				"FROM " + PRODUCT_1 + " INNER JOIN " + CATEGORY_2 + " " +
				"ON "+ PRODUCT_1__ID_CATEGORY +" = " + CATEGORY_2__ID_CATEGORY + " " +
				"WHERE "+ PRODUCT_1__PRODUCT_NAME +" LIKE '%"+search+"%' OR "+
					PRODUCT_1__DESCRIPTION + " LIKE '%"+search+"%' "+ 
				getOrderBy(orderBy, reverse); 
	}
	
	/**
	 * <p>Этот метод получает строку запроса к БД. (Вывод товары определенной категории)</p>
	 * @param idCategory Категория товара
	 * @return String query4 Сформированный запрос
	 */
	public static String query4(String idCategory, String orderBy, boolean reverse){
		
		return "SELECT " + PRODUCT_1__ID_PRODUCT + ", " + 
							PRODUCT_1__PRODUCT_NAME + ", " + 
							PRODUCT_1__DESCRIPTION + ", "+ 
							PRODUCT_1__PRICE +", " + 
							CATEGORY_2__CATEGORY_NAME + " " +
				"FROM " + PRODUCT_1 + " INNER JOIN " + CATEGORY_2 + " " +
				"ON "+ PRODUCT_1__ID_CATEGORY +" = " + CATEGORY_2__ID_CATEGORY + " " +
				"WHERE " + CATEGORY_2__ID_CATEGORY + " = "+ idCategory +	" " +
				getOrderBy(orderBy, reverse);
	}
	
	/**
	 * <p>Этот метод получает строку запроса к БД. 
	 * (Поиск в продуктах и их описании с учетом категории)</p>
	 * @param idCategory Категория товара
	 * @param search Что будет искатся в БД
	 * @return String query4 Сформированный запрос
	 */
	public static String query4(String idCategory, String search, String orderBy, boolean reverse){
		
		return "SELECT " + PRODUCT_1__ID_PRODUCT + ", " + 
							PRODUCT_1__PRODUCT_NAME + ", " + 
							PRODUCT_1__DESCRIPTION + ", "+ 
							PRODUCT_1__PRICE +"," + 
							CATEGORY_2__CATEGORY_NAME + " " +
				"FROM " + PRODUCT_1 + " INNER JOIN " + CATEGORY_2 + " " +
				"ON "+ PRODUCT_1__ID_CATEGORY +" = " + CATEGORY_2__ID_CATEGORY + " " +
				"WHERE (" + CATEGORY_2__ID_CATEGORY + " = "+ idCategory + " " +
				"AND (" + PRODUCT_1__PRODUCT_NAME + " LIKE '%"+search+"%' OR "
					+ PRODUCT_1__DESCRIPTION + " LIKE '%"+search+"%')) "+ 
				getOrderBy(orderBy, reverse);
	}			
	
	
	/**
	 * <p>Формирует окончание строки запроса (Order By)</p>
	 * @param orderBy Столбец по которому будет произведена сортировка
	 * @param reverse Столбец по которому будет произведена обратная сортировка
	 * @return Конец строки запроса
	 */
	public static String getOrderBy(String orderBy, boolean reverse){
		
		try{
			if( orderBy == null || "".equals(orderBy) ){				
				// Если сортировать не нужно, то добавляется пробел
				return " ";
			}else{		
				
				if(orderBy.equals(CODE)) orderBy = PRODUCT_1__ID_PRODUCT;
				if(orderBy.equals(PRODUCT_NAME)) orderBy = PRODUCT_1__PRODUCT_NAME;
				if(orderBy.equals(DESCRIPTION)) orderBy = PRODUCT_1__DESCRIPTION;
				if(orderBy.equals(PRICE)) orderBy = PRODUCT_1__PRICE;
				if(orderBy.equals(CATEGORY)) orderBy = CATEGORY_2__CATEGORY_NAME;
			
				if(reverse){
					return  "ORDER BY " + orderBy + " DESC";
				}else{
					return  "ORDER BY " + orderBy;
				}
			}
		}catch(Exception e){
			return " ";
		}
	}
	
	/**
	 * <p>Запрос INSERT INTO в таблицу Product</p>
	 * @param pt Данные для ввода
	 * @return
	 */
	public static String queryInsetProduct(ProductTable pt){
		
		return "INSERT INTO " + PRODUCT_1 + "("+ PRODUCT_1__ID_PRODUCT + ", "+
			PRODUCT_1__PRODUCT_NAME + ", " + PRODUCT_1__DESCRIPTION + ", " +
			PRODUCT_1__PRICE + ", " + PRODUCT_1__ID_CATEGORY + ") VALUES(" +
			"'" + pt.getCode() + "', '" + pt.getProductName() + "', " +
			"'" + pt.getDescription() + "', '" + pt.getPrice() + "', " +
			"'" + pt.getCategory() + "')";
	}
	
	/**
	 * <p>Запрос DELETE из таблицы Product</p>
	 * @param code Код продукта, который необходимо удалить
	 * @return
	 */
	public static String queryDeleteProduct(int code){		
		return "DELETE FROM " + PRODUCT_1 + " WHERE "+ PRODUCT_1__ID_PRODUCT + "=" + code;
	}
	
	/**
	 * <p>Запрос UPDATE для таблицы Product</p>
	 * @param pt 
	 * @param oldCode Код продукта
	 * @return
	 */
	public static String queryUpdateProduct(ProductTable pt, int oldCode){
		return "UPDATE " + PRODUCT_1 + " " +
					"SET " + PRODUCT_1__ID_PRODUCT + "=" + "'" + pt.getCode() + "'," +
						PRODUCT_1__PRODUCT_NAME + "=" + "'" + pt.getProductName() + "'," +
						PRODUCT_1__DESCRIPTION + "=" + "'" + pt.getDescription() + "'," +
						PRODUCT_1__PRICE 		+ "=" + "'" + pt.getPrice() + "'," +
						PRODUCT_1__ID_CATEGORY + "=" + "'" + pt.getCategory() + "'" +
					"WHERE " + PRODUCT_1__ID_PRODUCT + "=" + "'" + oldCode + "'";		
	}
	
	/**
	 * <p>Запрос на получение нужной строки из таблицы Product</p>
	 * @param code Код продукта
	 * @return
	 */
	public static String queryOneStringProduct(int code){
		return "SELECT " + PRODUCT_1__ID_PRODUCT + ", " + 
							PRODUCT_1__PRODUCT_NAME + ", " + 
							PRODUCT_1__DESCRIPTION + ", "+ 
							PRODUCT_1__PRICE +"," + 
							PRODUCT_1__ID_CATEGORY + " " +
				"FROM " + PRODUCT_1 + " " + 
				"WHERE " + PRODUCT_1__ID_PRODUCT + " = '"+ code + "'";
	}
	
	/**
	 * <p>Запрос для получения списка ID</p>
	 * @return
	 */
	public static String queryFreeIDProduct(){
		return "SELECT " + PRODUCT_1__ID_PRODUCT + " FROM " + PRODUCT_1 ;
	}
}

