package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import beans.Comment;
import beans.DataObject;
import database.DataBase;
import database.DataBaseConfig;

public class ForumChapterForm extends ActionForm{
	private static final long serialVersionUID = 13L;
	
	private int id_Chapter = 0;
	private String chapterName;	
	private int start = 0;
	private int chunkSize = finals.Final.CHUNK_SIZE_VALUE;
	
	private ArrayList<Comment> comment;
	
	// Comment 
	private Comment newComment = new Comment(); 
	private FormFile file;
	
	private int id_Object;
	private String object;
	private String objectName;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
				
		try{
			if (this.id_Chapter == 0){
				String idChapter = request.getParameter(finals.Final.ID_CHAPTER).toString();
				
				if(!idChapter.equals("") || idChapter != null){
					this.id_Chapter = Integer.parseInt(idChapter);
				}	
			}
			String query = database.DataBaseQuery.getForum_8_name(this.id_Chapter);
			this.chapterName = (DataBase.getInstance(dataBaseConfig).getData(query)).get(0).get(0);
		}catch(Exception e){
			
		}
		
		ArrayList<Comment> commentTemp = new ArrayList<Comment>();		
		try{			
			String query = database.DataBaseQuery.getComment_9_oneChapter(this.id_Chapter);
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
			
			int i = 1;
			Iterator<Vector<String>> tempIt = temp.iterator();						
			while(tempIt.hasNext()){
				Vector<String> tempLine = tempIt.next();
				
				Comment c = new Comment( Integer.parseInt(tempLine.get(0)), Integer.parseInt(tempLine.get(1)), 
						Integer.parseInt(tempLine.get(2)), tempLine.get(3), tempLine.get(4), tempLine.get(5), 
						tempLine.get(6), getDataObject(Integer.parseInt(tempLine.get(0)), i++, dataBaseConfig));
								
				commentTemp.add(c);
			}
			this.comment = commentTemp;
		}catch(Exception e){
			System.out.println(e);
			this.comment = commentTemp;
		}
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		return null;
    }
	
	
	/**
	 * <p>Список приложенных файлов</p>
	 * @param id - Номер раздела
	 * @param number - Номер комментария
	 * @param dataBaseConfig - Настройки соединения с БД
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList<DataObject> getDataObject(int id, int number, DataBaseConfig dataBaseConfig) throws Exception{

		String query = database.DataBaseQuery.getComment_Object_17(id, number);
		Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(query);
		
		ArrayList<DataObject> daOb = new ArrayList<DataObject>();
		Iterator<Vector<String>> tempIt = temp.iterator();	
		int i=1;
		while(tempIt.hasNext()){
			Vector<String> tempLine = tempIt.next();
			String object = tempLine.get(0);
			
			DataObject d = new DataObject(i++, object, object.substring( object.lastIndexOf('/')+1 ));
			
			daOb.add(d);
		}
		
		return daOb;
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
	public int getId_Chapter() {
		return id_Chapter;
	}
	public void setId_Chapter(int id_Chapter) {
		this.id_Chapter = id_Chapter;
	}
	public ArrayList<Comment> getComment() {
		return comment;
	}
	public void setComment(ArrayList<Comment> comment) {
		this.comment = comment;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public Comment getNewComment() {
		return newComment;
	}
	public void setNewComment(Comment newComment) {
		this.newComment = newComment;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;		
	}
	public int getId_Object() {
		return id_Object;
	}
	public void setId_Object(int id_Object) {
		this.id_Object = id_Object;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}	
}
