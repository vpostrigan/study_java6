package actions;

import java.util.Iterator;
import java.util.Vector;

import beans.CategoryTable;
import database.DataBase;
import database.DataBaseConfig;

/**
 * <p>Этот класс получает список категорий товаров из БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class CategoryTableValue {
	
	private Vector<CategoryTable> categoryTable;
	
	public CategoryTableValue(){
		
	}
	
	/**
	 * <p>Получение списка категорий товаров из БД и помещение его в categoryValue</p>
	 * @param query Запрос к БД
	 * @param dataBaseConfig Настройки соединения с БД
	 * @throws Exception
	 */
	public CategoryTableValue(String query, DataBaseConfig dataBaseConfig) throws Exception{
		
		// Получение списка категорий товаров из БД
		Vector<Vector<String> > temp = DataBase.getInstance(dataBaseConfig).getData(query);
		
		Iterator<Vector<String>> iteratorTemp = temp.iterator();
		
		// Преобразование списка категорий товаров к нужному формату
		Vector<CategoryTable> categoryValueTemp = new Vector<CategoryTable>();
		
		while(iteratorTemp.hasNext()){			
			Vector<String> tempLine = iteratorTemp.next(); 
			
			// Добавление Id_Category, Category_Name 
			categoryValueTemp.add( new CategoryTable(tempLine.get(0), tempLine.get(1)) );
		}
		
		categoryTable = categoryValueTemp;
	}

	
	public Vector<CategoryTable> getCategoryTable() {
		return categoryTable;
	}	
	public void setCategoryTable(Vector<CategoryTable> categoryTable) {
		this.categoryTable = categoryTable;
	}
}
