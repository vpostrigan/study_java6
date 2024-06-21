package database;

/**
 * <p>Этот bean-класс хранит параметры соединения с БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public class DataBaseConfig {
	
	/**
	 * <p>Имя драйвера соединения с БД</p>
	 */
	private String classForName;
	
	/**
	 * <p>Логин пользователя БД</p>
	 */
	private String login;
	
	/**
	 * <p>Пароль пользователя БД</p>
	 */
	private String passwd;
	
	/**
	 * <p>Адрес соединения с БД</p>
	 */
	private String url;
	
	public DataBaseConfig(String classForName, String login, String passwd, String url){
		this.classForName = classForName;
		this.login = login;
		this.passwd = passwd;
		this.url = url;
	}
	
	public String getClassForName() {
		return classForName;
	}
	
	public void setClassForName(String driver) {
		this.classForName = driver;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}	
}
