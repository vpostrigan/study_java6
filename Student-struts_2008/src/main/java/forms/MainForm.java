package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import beans.Forum;
import database.DataBase;
import database.DataBaseConfig;

public class MainForm extends ActionForm {
	private static final long serialVersionUID = 2L;
	
	private String userName;
	private int received;
	private int sent;
	
	// Forum
	private String selectedItems[] = {};
	private String newForumName;
	private String newForumPassword;
	private ArrayList<Forum> forum;
	
	private int start = 0;
	private int chunkSize = finals.Final.CHUNK_SIZE_VALUE;
	private boolean peopleIsAdmin = false;
	private boolean peopleIsStudent = false;
	private boolean peopleIsTeacher = false;
	private boolean peopleIsEmployee = false;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.newForumName = null;
		this.newForumPassword = null;
		this.selectedItems = null;
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		String passwd = (String)session.getAttribute(finals.Final.PASSWORD);
		
		ArrayList<Forum> forumTemp = new ArrayList<Forum>();		
		try{			
			String query = database.DataBaseQuery.getForum_8();
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
			
			Iterator<Vector<String>> tempIt = temp.iterator();						
			while(tempIt.hasNext()){
				Vector<String> tempLine = tempIt.next();
				int pass = tempLine.get(2).length();
				
				Forum m = new Forum(Integer.parseInt(tempLine.get(0)), tempLine.get(1), ""+pass, 
						Integer.parseInt(tempLine.get(3)), tempLine.get(4), tempLine.get(5));
				
				forumTemp.add(m);
			}
			this.forum = forumTemp;
		}catch(Exception e){
			this.forum = forumTemp;
		}
		
		try{
			Vector<Vector<String>> temp = 
				DataBase.getInstance(dataBaseConfig).getData(database.DataBaseQuery.getPeople1(login, passwd));
			// Пользователь
			this.userName = login;
			String peopleType = temp.get(0).get(1);
			
			if(peopleType.equals(finals.Final.PEOPLE_TYPE_ADMIN)){
				this.peopleIsAdmin = true;
			}
			if(peopleType.equals(finals.Final.PEOPLE_TYPE_STUDENT)){
				this.peopleIsStudent = true;
			}
			if(peopleType.equals(finals.Final.PEOPLE_TYPE_TEACHER)){
				this.peopleIsTeacher = true;
			}
			if(peopleType.equals(finals.Final.PEOPLE_TYPE_EMPLOYEE)){
				this.peopleIsEmployee = true;
			}
		}catch(Exception e){
			
		}
			
		try{				
			String queryId = database.DataBaseQuery.authorizationLogin(login);			
			int id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
			
			String creator_Count = database.DataBaseQuery.getMessage_7_Creator_Count(id);			
			this.sent = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(creator_Count)).get(0).get(0) );
			
			String receiver_Count = database.DataBaseQuery.getMessage_7_Receiver_Count(id);			
			this.received = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(receiver_Count)).get(0).get(0) );
		}catch(Exception e){
			
		}			
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {    	
		return null;
    }
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<Forum> getForum() {
		return forum;
	}
	public void setForum(ArrayList<Forum> forum) {
		this.forum = forum;
	}
	public int getChunkSize() {
		return chunkSize;
	}
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String[] getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	public String getNewForumName() {
		return newForumName;
	}
	public void setNewForumName(String newForumName) {
		this.newForumName = newForumName;
	}
	public String getNewForumPassword() {
		return newForumPassword;
	}
	public void setNewForumPassword(String newForumPassword) {
		this.newForumPassword = newForumPassword;
	}
	public boolean isPeopleIsAdmin() {
		return peopleIsAdmin;
	}
	public void setPeopleIsAdmin(boolean peopleIsAdmin) {
		this.peopleIsAdmin = peopleIsAdmin;
	}
	public boolean isPeopleIsStudent() {
		return peopleIsStudent;
	}
	public void setPeopleIsStudent(boolean peopleIsStudent) {
		this.peopleIsStudent = peopleIsStudent;
	}
	public boolean isPeopleIsTeacher() {
		return peopleIsTeacher;
	}
	public void setPeopleIsTeacher(boolean peopleIsTeacher) {
		this.peopleIsTeacher = peopleIsTeacher;
	}
	public boolean isPeopleIsEmployee() {
		return peopleIsEmployee;
	}
	public void setPeopleIsEmployee(boolean peopleIsEmployee) {
		this.peopleIsEmployee = peopleIsEmployee;
	}
	public int getReceived() {
		return received;
	}
	public void setReceived(int received) {
		this.received = received;
	}
	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}		
}
