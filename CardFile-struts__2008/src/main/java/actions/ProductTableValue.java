package actions;

import java.util.Iterator;
import java.util.Vector;

import beans.ProductTable;
import database.DataBase;
import database.DataBaseConfig;

/**
 * <p>Этот класс получает список товаров из БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class ProductTableValue {
	
	private Vector<ProductTable> productTable;
	
	
	public ProductTableValue(){
		
	}
	
	/**
	 * <p>Получение списка категорий товаров из БД и помещение его в productTable</p>
	 * @param query Запрос к БД
	 * @param dataBaseConfig Настройки соединения с БД
	 * @throws Exception
	 */
	public ProductTableValue(String query, DataBaseConfig dataBaseConfig) throws Exception{
		
		// Получение списка товаров из БД
		Vector<Vector<String> > temp = DataBase.getInstance(dataBaseConfig).getData(query);
		
		Iterator<Vector<String>> iteratorTemp = temp.iterator();
		
		// Преобразование списка товаров к нужному формату
		Vector<ProductTable> productTableValueTemp = new Vector<ProductTable>();		
				
		while(iteratorTemp.hasNext()){			
			Vector<String> tempLine = iteratorTemp.next(); 
			
			// Добавление Id_Product, Product_Name, Description, Price, Id_Category    
			productTableValueTemp.add( new ProductTable(tempLine.get(0), tempLine.get(1),
					tempLine.get(2), tempLine.get(3), tempLine.get(4)) );
		}
		
		productTable = productTableValueTemp;
	}

	
	public Vector<ProductTable> getProductTable() {
		return productTable;
	}
	
	public void setProductTable(Vector<ProductTable> productTable) {
		this.productTable = productTable;
	}
}
