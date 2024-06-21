package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/**
 * <p>Этот класс получает данные из БД и отправляет запросы на выполнение</p>
 * @author Postrigan V.A.
 * @version 2.0 
 */
public final class DataBase {
	
	/**
	 * <p>Значение которое будет подставлено вместо null</p> 
	 */
	public static final String AMP_NBSP = "&nbsp;";
		
	/**
	 * <p>Экзепляр класса DataBase</p>
	 */
	private static DataBase instance = null;
	
	/**
	 * <p>Имя драйвера соединения с БД</p>
	 */
	private String classForName;
	
    private String url;
	private String login;
	private String pass;

	
	/**
	 * <p>Чтение параметров соединения из файла Config.xml</p>
	 */
    private DataBase(DataBaseConfig dataBaseConfig) {
    	this.classForName = dataBaseConfig.getClassForName();
    	this.url = dataBaseConfig.getUrl();
    	this.login = dataBaseConfig.getLogin();
    	this.pass = dataBaseConfig.getPasswd();    	
    }

    /**
     * <p>Создание класса взаимодействующего с БД</p>
     * @param dataBaseConfig Параметры соединения с БД
     * @return final-класс DataBase
     */
    public synchronized static DataBase getInstance(DataBaseConfig dataBaseConfig) {
    	
    	if (instance == null){    		
        	instance = new DataBase(dataBaseConfig);
        }
            
        return instance;
    }
		
	/**
	 * <p>Этот метод получает данные из БД и заносит их в Vector<Vector<String> ></p>
	 * @param query Строка запроса к БД
	 * @return String data[][] Массив с данными
	 * @exception Exception Ошибки при взаимодействии с БД, чтении файла и
	 * 	использовании null (список пуст)
	 */
	public Vector<Vector<String> > getData(String query) throws Exception{
		
		// Хранилище для полученных данных из БД
		Vector<Vector<String> > vTemp = new Vector<Vector<String>>(); 
		Connection con = null;
		Statement stnt = null;
		
		try{
			// Вызов static поля classForName 
			Class.forName(classForName);
			
			// Создание соединения и выполнение запроса "query"
			con = DriverManager.getConnection(url, login, pass);
			stnt = con.createStatement();
			ResultSet rs = stnt.executeQuery(query);
			
			// Определение количества столбцов в запросе
			int v = rs.getMetaData().getColumnCount();
			String s;
			
			// Запись данных из ResultSet в временное хранилище vTemp
			while(rs.next()){		
			
				// Временное хранилище для строки данных полученной из БД
				Vector<String> vTempLine = new Vector<String>(); 
				for(int i=0; i < v; i++){		
					if ( rs.getObject(i+1) == null || "".equals(rs.getObject(i+1)) ){
						// В случае если полученное значение равно null, оно передается в 
						// JSP-страницу как "&nbsp" (для правильного отображения)
						s = AMP_NBSP;
					}else{
						s = rs.getObject(i+1).toString() ;
					}
				
					// Передача текущего значения во временное хранилище для строки
					vTempLine.addElement(s);
				}			
			
				// Передача текущей, полученной, строки в хранилище
				vTemp.addElement(vTempLine);
				vTempLine = null;
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			// Закрытие соединения
			con.close();
			stnt.close();			
		}		
		
		return vTemp;
	}	
	
	/**
	 * <p>Этот метод находит первый свободный ID в таблице</p>
	 * @param query Запрос
	 * @return Число (свободный ID в таблице)
	 * @throws Exception
	 */
	public int getFreeID( String query ) throws Exception{
		
		Connection con = null;
		Statement stnt = null;
		
		try{
			// Вызов static поля classForName 
			Class.forName(classForName);
			
			// Создание соединения и выполнение запроса "query"
			con = DriverManager.getConnection(url, login, pass);
			stnt = con.createStatement();
			ResultSet rs = stnt.executeQuery(query);
			
			String s = null;			
			Vector<Integer> vTemp = new Vector<Integer>();
			
			// Запись данных из ResultSet в временное хранилище vTemp
			while(rs.next()){		
				s = rs.getObject(1).toString();
				vTemp.addElement( Integer.parseInt(s) );
			}
			
			// Сортировка списка ID по возрастанию 
			Collections.sort(vTemp);
			
			// Поиск свободного ID, если его нету, то добавляется новый 
			Iterator<Integer> vTempIter = vTemp.iterator();
			Integer i = 1;
			while(vTempIter.hasNext()){
				if(vTempIter.next() != i){
					return i;
				}
				i++;
			}
			return i;
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			// Закрытие соединения
			con.close();
			stnt.close();			
		}		
	}
	
	/**
	 * <p>Метод предназначен для выполнения запрос типа INPUT, DELETE, UPDATE</p>
	 * @param query Запрос на выполнения 
	 * @return true если запрос выполнен, иначе - false
	 * @throws Exception
	 */
	public boolean setData( String query ) throws Exception{
		
		Connection con = null;
		Statement stnt = null;
		
		try{
			// Вызов static поля classForName 
			Class.forName(classForName);
			
			// Создание соединения и выполнение запроса "query"
			con = DriverManager.getConnection(url, login, pass);
			stnt = con.createStatement();
			stnt.execute(query);
			
			// Закрытие соединения
			con.close();
			stnt.close();
			return true;
		}catch(Exception e){
			try{
				con.close();
				stnt.close();
			}catch(SQLException sqlE){
				
			}
			return false;
		}
	}
}