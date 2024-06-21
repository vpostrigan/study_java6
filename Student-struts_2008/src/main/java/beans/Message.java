package beans;

public class Message {
	
	private int id_Message;	
	private int id_People_Creator;
	private int id_People_Receiver;
	private String theme;
	private String message;
	private String message_Date;
	
	private String login;
	private String name;
	
	public Message() {
		
	}
	
	public Message(String message_Date, String theme, String message) {
		this.message_Date = message_Date;
		this.theme = theme;
		this.message = message;		
	}
	
	public Message(int id_Message, String message_Date, String theme, String login, String name) {	
		this.id_Message = id_Message;
		this.theme = theme;
		this.message_Date = message_Date;
		this.login = login;
		this.name = name;
	}
	
	public Message(int id_Message, String message_Date, int id_People_Creator, int id_People_Receiver, 
			String theme, String message) {	
		this.id_Message = id_Message;
		this.id_People_Creator = id_People_Creator;
		this.id_People_Receiver = id_People_Receiver;
		this.theme = theme;
		this.message = message;
		this.message_Date = message_Date;
	}
	
	
	
	public int getId_Message() {
		return id_Message;
	}
	public void setId_Message(int id_Message) {
		this.id_Message = id_Message;
	}
	public int getId_People_Creator() {
		return id_People_Creator;
	}
	public void setId_People_Creator(int id_People_Creator) {
		this.id_People_Creator = id_People_Creator;
	}
	public int getId_People_Receiver() {
		return id_People_Receiver;
	}
	public void setId_People_Receiver(int id_People_Receiver) {
		this.id_People_Receiver = id_People_Receiver;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage_Date() {
		return message_Date;
	}
	public void setMessage_Date(String message_Date) {
		this.message_Date = message_Date;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

