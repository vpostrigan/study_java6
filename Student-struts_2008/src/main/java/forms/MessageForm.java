package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import beans.MenuList;
import beans.Message;
import beans.PeopleEmail;
import database.DataBase;
import database.DataBaseConfig;

public class MessageForm extends ActionForm{
	private static final long serialVersionUID = 8L;
	
	private int id_Message;	
	private ArrayList<Message> message;
	
	// Чтение сообщения
	private Message messageRead;
	private ArrayList<MenuList> menuList;
	
	// Новое сообщение
	private Message messageNew;
	private ArrayList<MenuList> menuListNew;
	private ArrayList<PeopleEmail> people_Receiver;
	
	/**
	 * <p> Список сообщениу которые необходимо удалить</p>
	 */
	private String selectedItems[] = {};
	private int start = 0;
	private int chunkSize = finals.Final.CHUNK_SIZE_VALUE;
	
	private boolean newMessage = false;
	
	private FormFile file;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id_Message = 0;
		this.newMessage = false;
		this.menuList = null;
		
		Message messageNew = new Message();
		this.messageNew = messageNew;
		
		// Получение параметров соединения с БД
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		ArrayList<Message> messageTemp = new ArrayList<Message>();		
		try{
			String queryId = database.DataBaseQuery.authorizationLogin(login);			
			int id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
			
			String creator_Count = database.DataBaseQuery.getMessage_7(id);
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(creator_Count);
			
			Iterator<Vector<String>> tempIt = temp.iterator();						
			while(tempIt.hasNext()){
				Vector<String> tempLine = tempIt.next();
				
				Message m = new Message(Integer.parseInt(tempLine.get(0)), tempLine.get(1), tempLine.get(2), 
						tempLine.get(3), tempLine.get(4) + " " + tempLine.get(5));
				
				messageTemp.add(m);
			}
			this.message = messageTemp;
		}catch(Exception e){
			this.message = messageTemp;
		}	
			
		try{
			// Список пользователей
			ArrayList<PeopleEmail> peopleTypeTemp = new ArrayList<PeopleEmail>();			
			String users = database.DataBaseQuery.getPeopleAll();
			Vector<Vector<String>> temp2 = DataBase.getInstance(dataBaseConfig).getData(users);
				
			Iterator<Vector<String>> tempIt2 = temp2.iterator();
			while(tempIt2.hasNext()){
				Vector<String> tempLine2 = tempIt2.next();
					
				PeopleEmail p = new PeopleEmail( Integer.parseInt( tempLine2.get(0)), 
						(tempLine2.get(1) + " " + tempLine2.get(2) + " " + tempLine2.get(3)) );
					
				peopleTypeTemp.add(p);
			}
				
			this.people_Receiver = peopleTypeTemp;			
		}catch(Exception e){
			this.people_Receiver = null;
		}				
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();    
		
		// !!! Проверка выполняется, но пользователю не выдается сообщение об ошибке, а
		// устанавливается значение по-умолчанию 
        if (chunkSize < 1){        
        	errors.add(finals.Final.CHUNK_SIZE, new ActionMessage(finals.Final.ERROR_CHUNK_SIZE, chunkSize));
        	this.setChunkSize(finals.Final.CHUNK_SIZE_VALUE);
        }
		
        return null;
    }
	
	
	public ArrayList<Message> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<Message> message) {
		this.message = message;
	}
	public String[] getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getChunkSize() {
		return chunkSize;
	}
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	public int getId_Message() {
		return id_Message;
	}
	public void setId_Message(int id_Message) {
		this.id_Message = id_Message;
	}
	public Message getMessageRead() {
		return messageRead;
	}
	public void setMessageRead(Message messageRead) {
		this.messageRead = messageRead;
	}
	public boolean isNewMessage() {
		return newMessage;
	}
	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}
	public ArrayList<MenuList> getMenuList() {
		return menuList;
	}
	public void setMenuList(ArrayList<MenuList> menuList) {
		this.menuList = menuList;
	}
	public Message getMessageNew() {
		return messageNew;
	}
	public void setMessageNew(Message messageNew) {
		this.messageNew = messageNew;
	}
	public ArrayList<MenuList> getMenuListNew() {
		return menuListNew;
	}
	public void setMenuListNew(ArrayList<MenuList> menuListNew) {
		this.menuListNew = menuListNew;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public ArrayList<PeopleEmail> getPeople_Receiver() {
		return people_Receiver;
	}
	public void setPeople_Receiver(ArrayList<PeopleEmail> people_Receiver) {
		this.people_Receiver = people_Receiver;
	}				
}

