package beans;

public class DataObject {
	private int id_Object;
	private String object;
	private String objectName;
	
	
	public DataObject(){
		
	}

	public DataObject(int id_Object, String object, String objectName) {
		this.id_Object = id_Object;
		this.object = object;
		this.objectName = objectName;
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
