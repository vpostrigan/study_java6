import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;


class DB_Connect {
	
	 private final static String Class_forName =
			 //"sun.jdbc.odbc.JdbcOdbcDriver";
			"net.ucanaccess.jdbc.UcanaccessDriver";
	// private final static String url_ ="jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=";
	private final static String url_ ="jdbc:ucanaccess://";
	
	public static Object[][] getData(String Query, int v) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");

		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(Query);
		
		
		Vector<Vector<String>> v_temp = new Vector<Vector<String>>(); 
				
		String d;
		while(rs.next()){		
			
			Vector<String> v_temp_Line = new Vector<String>(); 
			for(int i=0; i < v-1; i++){				
				d = rs.getObject(i+1).toString();
				v_temp_Line.addElement(d);
			}			
			v_temp.addElement(v_temp_Line);
			v_temp_Line = null;
		}
		
		Iterator<Vector<String>> It_v_temp = v_temp.iterator();
		
		Object data[][] = new Object[v_temp.size()][v-1];
		
		int i=0;
		while(It_v_temp.hasNext()){
			
			Iterator<String> It_v_temp_Line = (It_v_temp.next()).iterator();
			
			int j=0;
			while(It_v_temp_Line.hasNext()){
				data[i][j++] = It_v_temp_Line.next();
			}
			
			i++;			
		}
		
		st.close();
		con.close();
		
		return data; 
	}
	
	public static int getID(String TableName, String ColName) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery( "SELECT "+ ColName +" FROM " + TableName);
		
		LinkedList<Integer> v = new LinkedList<Integer>();
		while(rs.next()){
			v.add( rs.getInt(1) );
		}
		
		
		Collections.sort(v);
		
		Iterator<Integer> v_it = v.iterator();
		
		int i=0;
		boolean b = false;
		while(v_it.hasNext()){
			i++;
			if ( !(v_it.next() == i) ){
				b = true;
				break;
			}
		}
		
		if(!b){
			i++;
		}
				
		rs.close();
		con.close();
		
		return i;
	}
	
	public static Vector<String> getData(String TableName, String ColName) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery( "SELECT DISTINCT " + ColName + " FROM " + TableName );
		
		Vector<String> Data = new Vector<String>();
		while(rs.next()){
			Data.addElement(rs.getObject(1).toString());
		} 
				
		rs.close();
		con.close();
		
		return Data;
	}
	
	public static Vector<String> getData_2(String TableName,String[] ColName) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery( "SELECT DISTINCT " + ColName + " FROM " + TableName );
		
		Vector<String> Data = new Vector<String>();
		while(rs.next()){
			Data.addElement(rs.getObject(1).toString());
		} 
				
		rs.close();
		con.close();
		
		return Data;
	}
	
	public static Vector<String> getData3(String TableName, String ColName, boolean b, int z)
		throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		String Query;
		if(b){
			Query = "SELECT DISTINCT " + ColName +" FROM "+ TableName +" WHERE LEVEL_ = "+z;
		}else{
			Query = "SELECT DISTINCT " + ColName +" FROM "+ TableName +" WHERE LEVEL_ = "+z;
		}
		
		ResultSet rs = st.executeQuery( Query );
		
		Vector<String> Data = new Vector<String>();
		while(rs.next()){
			Data.addElement(rs.getObject(1).toString());
		} 
				
		rs.close();
		con.close();
		
		return Data;
	}
	
	public static void setData_Argreration(String Query) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		st.execute(Query);
		
		st.close();
		con.close();
	}
	
	public static Vector<String> getData_(String Q) throws Exception{
	
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery( Q );
		
		Vector<String> Data = new Vector<String>();
		while(rs.next()){
			Data.addElement(rs.getObject(1).toString());
		}
		
		st.close();
		con.close();
		
		return Data;
	}
	
	
	
	
	public static int getSUM(String Q) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery( Q );
		rs.next();
		return rs.getInt(1);
	}
		
	public static String getString(String Q) throws Exception{
		
		String url = url_.concat(Lab1_Main.path + "\\file.mdb;password=1");
		
		Class.forName(Class_forName);
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
				
		ResultSet rs = st.executeQuery( Q );
		rs.next();
		String er = rs.getObject(1).toString();
		
		return er;
	}	
}
