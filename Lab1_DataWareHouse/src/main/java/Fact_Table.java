import java.util.Iterator;
import java.util.Vector;

/*
 *  Таблица фактов
 */
class Fact_Table {
	
	public void New_FactTable() throws Exception{
		
		
		FirstLevel();
		Time_2__LEVEL1();
		Time_2__LEVEL2();
		Product_3__LEVEL1();
	}
	
	public void FirstLevel() throws Exception{
		
		/*
		 * Списки Time_ID, Product_ID, Worker_ID, Location_ID
		 */
		Vector<String> Time_2 = DB_Connect.getData3( "Time_2" , "Time_ID" , true, 0);
		Iterator<String> Time_2_iter = Time_2.iterator();
		
		Vector<String> Product_3 = DB_Connect.getData3( "Product_3" , "Product_ID" , true, 0);
		Iterator<String> Product_3_iter = Product_3.iterator();
		
		Vector<String> Worker_4 = DB_Connect.getData( "Worker_4" , "Worker_ID" );
		Iterator<String> Worker_4_iter = Worker_4.iterator();
		
		Vector<String> Location_5 = DB_Connect.getData3( "Location_5" , "Location_ID" ,true,0);
		Iterator<String> Location_5_iter = Location_5.iterator();
		
		
		String Query;
		String Time_ID, Product_ID, Worker_ID, Location_ID;
		int n;
		while( Time_2_iter.hasNext() ){
			
			Time_ID = Time_2_iter.next();
			
			while( Product_3_iter.hasNext() ){
				Product_ID = Product_3_iter.next();
				
				while( Worker_4_iter.hasNext() ){
					Worker_ID = Worker_4_iter.next();
				
					while( Location_5_iter.hasNext() ){
						Location_ID = Location_5_iter.next();
						
						n = Number();
						
						Query = "INSERT INTO Sale_1 Values('" + Time_ID + "','" +
							Product_ID + "','" + Worker_ID + "','" + 
							Location_ID+"','" + n + "','" + Price1(n) + ","+Price2()+"')";			
					System.out.println(Query);
						DB_Connect.setData_Argreration(	Query );
					}
					Location_5_iter = Location_5.iterator();
				}
				Worker_4_iter = Worker_4.iterator();
			}
			Product_3_iter = Product_3.iterator();
			
		}		
	}	
	
	/*
	 * По агрегатам
	 */
	public void Time_2__LEVEL1() throws Exception{
				
		/*
		 * Список Time_ID
		 */
		String Q = "SELECT Year, Month, Time_ID FROM Time_2 WHERE LEVEL_ = 1";
		Object Time_2[][] = DB_Connect.getData( Q, 4 );
		
		for(int i=0; i < Time_2.length; i++){
			Q = "SELECT Sum(Quantity), Sum(Price) FROM Sale_1, Time_2 "+
				"WHERE Year="+Time_2[i][0]+" AND Month="+Time_2[i][1]+
				" AND Sale_1.Time_ID=Time_2.Time_ID" ;
			Object SUM[][] = DB_Connect.getData( Q, 3 );
			
			
			Q = "INSERT INTO Sale_1 Values('"+ Time_2[i][2] +"','"+ 0 + "','" + 0 +
						"','" + 0 +"','" + N1(SUM[0][0]) + "','" + N2(SUM[0][1]) + "')";			
			
			DB_Connect.setData_Argreration(	Q );
		}
		
		
		
		
		/*
		
		
		/*
		 * Список Product_ID
		 */
		//Vector<String> Product_3 = DB_Connect.getData3( "Product_3" , "Product_ID" , false, 2);
		//Iterator<String> Product_3_iter = Product_3.iterator();
		
		/*
		 * Список Worker_ID
		 */
		//Vector<String> Worker_4 = DB_Connect.getData( "Worker_4" , "Worker_ID" );
		//Iterator<String> Worker_4_iter = Worker_4.iterator();
		
		/*
		 * Список Location_ID
		 */
		/*Vector<String> Location_5 = DB_Connect.getData3( "Location_5" ,"Location_ID",false,2);
		Iterator<String> Location_5_iter = Location_5.iterator();
		
		String Query;
		String Product_ID, Worker_ID, Location_ID;
		int n;
		while( Time_2_iter.hasNext() ){
			
			if ( Product_3_iter.hasNext() ){
				Product_ID = Product_3_iter.next();
			}else{
				Product_3_iter = Product_3.iterator();
				Product_ID = Product_3_iter.next();
			}
			
			if ( Worker_4_iter.hasNext() ){
				Worker_ID = Worker_4_iter.next();
			}else{
				Worker_4_iter = Worker_4.iterator();
				Worker_ID = Worker_4_iter.next();
			}
			
			if ( Location_5_iter.hasNext() ){
				Location_ID = Location_5_iter.next();
			}else{
				Location_5_iter = Worker_4.iterator();
				Location_ID = Location_5_iter.next();
			}
			
			String qwe = Time_2_iter.next();
			
			String Y = DB_Connect.getString("SELECT Year FROM Time_2 WHERE Time_ID = " + qwe);			
			String M = DB_Connect.getString("SELECT Month FROM Time_2 WHERE Time_ID = " + qwe);
			
			String Q1 = "SELECT SUM(QUANTITY) FROM Sale_1 WHERE EXISTS " + 
			"(SELECT Sale_1.Time_ID FROM Time_2,Sale_1 WHERE (Time_2.Year ="+Y
			+") AND (Time_2.Month="+M+"))";	
			
			n = DB_Connect.getSUM(Q1);
						
			Query = "INSERT INTO Sale_1 Values('" + qwe + "','" +
				Product_ID + "','" + Worker_ID + "','" + Location_ID+"','" + n + "','" +
				Price(n) + "')";			
			System.out.println("1");	
			DB_Connect.setData_Argreration(	Query );
			
		}	*/
	}
	
	public void Time_2__LEVEL2() throws Exception{
		
		/*
		 * Список Time_ID
		 */
		String Q = "SELECT Year, Time_ID FROM Time_2 WHERE LEVEL_ = 2";
		Object Time_2[][] = DB_Connect.getData( Q, 3 );
		
		for(int i=0; i < Time_2.length; i++){
			Q = "SELECT Sum(Sale_1.Quantity), Sum(Sale_1.Price) FROM Time_2, Sale_1 "+
				"WHERE Year="+Time_2[i][0]+
				" AND Time_2.Time_ID=Sale_1.Time_ID AND Time_2.LEVEL_ = 1" ;
			Object SUM[][] = DB_Connect.getData( Q, 3 );
			
			
			Q = "INSERT INTO Sale_1 Values('"+ Time_2[i][1] +"','"+  0 + "','" + 0 +
						"','" + 0 +"','" + N1(SUM[0][0]) + "','" + N2(SUM[0][1]) + "')";			
			
			DB_Connect.setData_Argreration(	Q );
		}
	}
	
	public void Product_3__LEVEL1() throws Exception{
				
		/*
		 * Список Time_ID
		 */
		String Q = "SELECT Product_Group,Trademark,Product_ID FROM Product_3 WHERE LEVEL_=1";
		Object Product_3[][] = DB_Connect.getData( Q, 4 );
		System.out.print("1");
		for(int i=0; i < Product_3.length; i++){
			Q = "SELECT Sum(Quantity), Sum(Price) FROM Sale_1, Product_3 "+
				"WHERE Product_Group='"+Product_3[i][0]+"' AND Trademark='"+Product_3[i][1]+
				"' AND Sale_1.Product_ID=Product_3.Product_ID" ;
			System.out.print(Q);
			Object SUM[][] = DB_Connect.getData( Q, 3 );
			System.out.print("2");
			
			Q = "INSERT INTO Sale_1 Values('"+ 0 +"','"+ Product_3[i][2] + "','" + 0 +
						"','" + 0 +"','" + N1(SUM[0][0]) + "','" + N2(SUM[0][1]) + "')";			
			System.out.print(Q);
			DB_Connect.setData_Argreration(	Q );
		}
	}
	
	public int N1(Object n){
		Integer i = new Integer(n.toString().substring(0,n.toString().length()-2));
		
		return i.intValue();
	}
	
	public String N2(Object n){
		Double i = new Double(n.toString());
		double d = i.doubleValue();
		int d_ = (int)d*100;
		Double d_l = d_/100.0;
		String s = d_l.toString();
		
		return s.replace('.', ',');
	}
	
	public int Number(){
		return (int)Math.round(Math.random()*10)+1;
	}
	
	public int Price1(int n){				
		return (int) (n*(Math.random()*20))+1;
	}
	
	public int Price2(){
		int p = (int) (Math.random()*100);
		
		if(p >= 100){
			p = 99; 
		}		
		
		return p;
	}
	
	
	/*
	 * Очистка всей таблицы фактов
	 */	
	public void Delete_FactTable() throws Exception{		
		
		DB_Connect.setData_Argreration(	"DELETE FROM Sale_1" );
	}
}
