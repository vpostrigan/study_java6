package beans;

public class Forum {
	private int id_Chapter;
	private String chapterName;
	private String chapterPassword;
	private int id_People;
	private String chapterDate;
	
	private String login;
	
	
	public Forum() {
		
	}
	
	public Forum(int id_Chapter, String chapterName, String chapterPassword, int id_People, String chapterDate) {
		this.id_Chapter = id_Chapter;
		this.chapterName = chapterName;
		this.chapterPassword = chapterPassword;
		this.id_People = id_People;
		this.chapterDate = chapterDate;
	}
	
	public Forum(int id_Chapter, String chapterName, String chapterPassword, int id_People, String chapterDate, String l) {
		this.id_Chapter = id_Chapter;
		this.chapterName = chapterName;
		this.chapterPassword = chapterPassword;
		this.id_People = id_People;
		this.chapterDate = chapterDate;
		this.login = l;
	}
	
	public int getId_Chapter() {
		return id_Chapter;
	}
	public void setId_Chapter(int id_Chapter) {
		this.id_Chapter = id_Chapter;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getChapterPassword() {
		return chapterPassword;
	}
	public void setChapterPassword(String chapterPassword) {
		this.chapterPassword = chapterPassword;
	}
	public int getId_People() {
		return id_People;
	}
	public void setId_People(int id_People) {
		this.id_People = id_People;
	}
	public String getChapterDate() {
		return chapterDate;
	}
	public void setChapterDate(String chapterDate) {
		this.chapterDate = chapterDate;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}	
}
