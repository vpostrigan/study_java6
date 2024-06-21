package plugins;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import database.DataBaseConfig;

/**
 * <p>Этот класс достает параметры соединения из файла database-config.properties</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class DataBaseConfigPlugIn implements PlugIn{
	
	/**
	 * <p>Путь к файлу с параметрами соединения с БД</p>
	 */
	private final String PATH_NAME = "/WEB-INF/database-config.properties";	
	private final String DATA_BASE_CONFIG = "dataBaseConfig"; 
	
	private final String JDBC_DRIVER = "JDBCDriver";
	private final String USER_NAME = "username";
	private final String PASS_WORD = "password";
	private final String URL = "url";
	private final String UNICODE = "unicode";
	private final String CHARACTER_ENCODING = "characterEncoding";
	
	private String JDBCDriver;
	private String userName;
	private String passwd;
	private String url;
	
	
	public void init(ActionServlet servlet, ModuleConfig conf) throws ServletException{
		
		// Получение пути к конфигурационному файлу
		String path = servlet.getServletContext().getRealPath( PATH_NAME );
		
		Properties ps = new Properties();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(path);
		}catch(FileNotFoundException e){
			throw new ServletException(e);
		}
		
		try{		
			
			if(fis != null){
				// Чтение параметров из конфигурационного файла		
				ps.load(fis);
			}
			
			JDBCDriver = ps.getProperty( JDBC_DRIVER );
			userName = ps.getProperty( USER_NAME );			
			passwd = ps.getProperty( PASS_WORD );
			url = ps.getProperty( URL );
			String unicode = ps.getProperty( UNICODE );
			String characterEncoding = ps.getProperty( CHARACTER_ENCODING );
			
			url = url + "useUnicode=" + unicode + "&characterEncoding=" + characterEncoding;
			
		}catch(IOException e){
			
			throw new ServletException(e);		
		}finally{			
			
			try{
				// Закрытие файла
				fis.close();
			}catch(IOException e){
				
				throw new ServletException(e);
			}
		}
				
		// передача параметров соединения с БД в контекст сервлета
		servlet.getServletContext().setAttribute( DATA_BASE_CONFIG, 
				 new DataBaseConfig(JDBCDriver, userName, passwd, url) );
	}
	
	public void destroy(){
		
	}	
}
