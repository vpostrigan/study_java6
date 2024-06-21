package beans;

import java.util.ArrayList;

public class Comment {
	private int id_Chapter;
	private int commentNumber;
	private int id_People;
	private String commentValue;
	private String commentDate;
	
	private String login;
	private String avatar;
	
	private ArrayList<DataObject> dataObject;
	
	
	public Comment() {
		
	}

	public Comment(int id_Chapter, int commentNumber, int id_People, String commentValue, 
			String commentDate, String login, String avatar, ArrayList<DataObject> dataObject) {		
		this.id_Chapter = id_Chapter;
		this.commentNumber = commentNumber;
		this.id_People = id_People;
		this.commentValue = commentValue;
		this.commentDate = commentDate;
		this.login = login;
		this.avatar = avatar;
		this.dataObject = dataObject;
	}

	public Comment(int id_Chapter, int commentNumber, int id_People, String commentValue, 
			String commentDate, String login, String avatar) {		
		this.id_Chapter = id_Chapter;
		this.commentNumber = commentNumber;
		this.id_People = id_People;
		this.commentValue = commentValue;
		this.commentDate = commentDate;
		this.login = login;
		this.avatar = avatar;
	}

	public int getId_Chapter() {
		return id_Chapter;
	}
	public void setId_Chapter(int id_Chapter) {
		this.id_Chapter = id_Chapter;
	}
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public ArrayList<DataObject> getDataObject() {
		return dataObject;
	}
	public void setDataObject(ArrayList<DataObject> dataObject) {
		this.dataObject = dataObject;
	}
}
