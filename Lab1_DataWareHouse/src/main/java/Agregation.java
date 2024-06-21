import java.util.Iterator;
import java.util.Vector;

/*
 * Агрегаты таблицы
 */
class Agregation {
	
	public void CreateNew_Agregate() throws Exception{
		
		Time_2();
		Product_3();
		Location_5();
	}
	
	public void Time_2() throws Exception{
		
		LEVEL_1("SELECT DISTINCT Year, Month FROM Time_2", 
				"Time_2", "Time_ID",
				"INSERT INTO Time_2 Values ('",  "','-1','1')");
				
		LEVEL_2("Time_2", "Year", "Time_ID", 
				"INSERT INTO Time_2 Values ('", 
				"','-1','-1','2')");
	}
	
	public void Product_3() throws Exception{
		
		LEVEL_1("SELECT DISTINCT Product_Group, Trademark FROM Product_3", 
				"Product_3", "Product_ID",
				"INSERT INTO Product_3 Values ('",  "','-------','1')");
				
		LEVEL_2("Product_3", "Product_Group", "Product_ID", 
				"INSERT INTO Product_3 Values ('", 
				"','-------','-------','2')");			
	}
	
	public void Location_5() throws Exception{
		
		LEVEL_1("SELECT DISTINCT Region, Town FROM Location_5", 
				"Location_5", "Location_ID",
				"INSERT INTO Location_5 Values ('",  "','-------','1')");
				
		LEVEL_2("Location_5", "Region", "Location_ID", 
				"INSERT INTO Location_5 Values ('", 
				"','-------','-------','2')");		
	}
	
	public void LEVEL_1(String Query_Base, String Q1, String Q2, String Query_1,
			String Query_2) throws Exception{

		String Query;
		
		Query = Query_Base; // 2 par
		Object data[][] = DB_Connect.getData(Query, 3);
		
		for(int i=0; i < data.length; i++){
			int Location_5_COUNT = DB_Connect.getID(Q1, Q2);
						
			Query = Query_1 + Location_5_COUNT + "','" + data[i][0] + "','" + data[i][1] 
			             + Query_2;
			
			DB_Connect.setData_Argreration(	Query );
			
		}
	}
	
	public void LEVEL_2(String Query_Base, String Q1, String Q2, String Query_1,
			String Query_2) throws Exception{
		
		Vector<String> v = DB_Connect.getData( Query_Base , Q1 ); // 1 par
		Iterator<String> v_iter = v.iterator();
		
		while(v_iter.hasNext()){
			
			int COUNT = DB_Connect.getID( Query_Base , Q2 );
			String ColN = v_iter.next();
			
			DB_Connect.setData_Argreration(	Query_1 + COUNT + "','" + ColN + Query_2 );
		}		
	}	
	
	/*
	 * Удаление всех агрегатов
	 */
	public void DeleteAll_Agregate() throws Exception{
		
		String Query;
		
		Query = "DELETE FROM Time_2 WHERE LEVEL_ <> 0";
		DB_Connect.setData_Argreration(	Query );
		
		Query = "DELETE FROM Product_3 WHERE LEVEL_ <> 0";
		DB_Connect.setData_Argreration(	Query );
		
		Query = "DELETE FROM Location_5 WHERE LEVEL_ <> 0";
		DB_Connect.setData_Argreration(	Query );
	}
}
