package forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import beans.Alternative;
import beans.Criterion;
import beans.Task;
import database.DataBase;
import database.DataBaseConfig;

public class TaskConditionForm extends ActionForm{
	private static final long serialVersionUID = 11L;	
	
	private String newTask;
	private String selectedTask;
	
	private Task task = new Task();
	private ArrayList<Task> taskAll;
	
	// Alternative
	private String newAlternative;
	private ArrayList<Alternative> alternative = new ArrayList<Alternative>();
	
	// Criterion
	private String newCriterion;
	private ArrayList<Criterion> criterion = new ArrayList<Criterion>();
	
	private String selectedItemsAlternative[] = {};
	private String selectedItemsCriterion[] = {};
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.newTask = null;
		this.newAlternative = null;
		this.newCriterion = null;
		this.selectedTask = null;		
		
		// Получение параметров соединения с БД
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig) 
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		String queryId = database.DataBaseQuery.authorizationLogin(login);	
		int id = 0;
		try{
			id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
		}catch(Exception e){
			
		}
		
		try{
			// Список задач
			ArrayList<Task> taskTemp = new ArrayList<Task>();			
			String tasks = database.DataBaseQuery.getTask_13_One_Id_people(id);
			
			Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(tasks);
			
			Iterator<Vector<String>> tempIt = temp.iterator();
			while(tempIt.hasNext()){
				Vector<String> tempL = tempIt.next();
				
				Task t = new Task(Integer.parseInt(tempL.get(0)), tempL.get(1), tempL.get(2), Integer.parseInt(tempL.get(3)));
				
				taskTemp.add(t);
			}
			
			this.taskAll = taskTemp;			
		}catch(Exception e){
			this.taskAll = null;
		}
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		return null;
    }

		
	public String getSelectedTask() {
		return selectedTask;
	}
	public void setSelectedTask(String selectedTask) {
		this.selectedTask = selectedTask;
	}
	public String getNewTask() {
		return newTask;
	}
	public void setNewTask(String newTask) {
		this.newTask = newTask;
	}
	public ArrayList<Task> getTaskAll() {
		return taskAll;
	}
	public void setTaskAll(ArrayList<Task> taskAll) {
		this.taskAll = taskAll;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public ArrayList<Alternative> getAlternative() {
		return alternative;
	}
	public void setAlternative(ArrayList<Alternative> alternative) {
		this.alternative = alternative;
	}
	public ArrayList<Criterion> getCriterion() {
		return criterion;
	}
	public void setCriterion(ArrayList<Criterion> criterion) {
		this.criterion = criterion;
	}
	public String[] getSelectedItemsAlternative() {
		return selectedItemsAlternative;
	}
	public void setSelectedItemsAlternative(String[] selectedItemsAlternative) {
		this.selectedItemsAlternative = selectedItemsAlternative;
	}
	public String getNewAlternative() {
		return newAlternative;
	}
	public void setNewAlternative(String newAlternative) {
		this.newAlternative = newAlternative;
	}
	public String getNewCriterion() {
		return newCriterion;
	}
	public void setNewCriterion(String newCriterion) {
		this.newCriterion = newCriterion;
	}
	public String[] getSelectedItemsCriterion() {
		return selectedItemsCriterion;
	}
	public void setSelectedItemsCriterion(String[] selectedItemsCriterion) {
		this.selectedItemsCriterion = selectedItemsCriterion;
	}	
}


