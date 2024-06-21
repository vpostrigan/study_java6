package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.Alternative;
import beans.Criterion;
import beans.Task;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.TaskConditionForm;

public class TaskConditionAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
		
		ActionErrors errors = new ActionErrors();
		TaskConditionForm taskConditionForm = (TaskConditionForm) form;		
		HttpSession session = request.getSession();
		
		String login = (String)session.getAttribute(finals.Final.LOGIN);
		
		String queryId = database.DataBaseQuery.authorizationLogin(login);	
		int id = 0;
		try{
			id = Integer.parseInt( (DataBase.getInstance(dataBaseConfig).getData(queryId)).get(0).get(0) );
		}catch(Exception e){
			
		}
		
		String buttonAdd = request.getParameter(finals.Final.BUTTON_ADD);
		String buttonShow = request.getParameter(finals.Final.BUTTON_SHOW);
		
		String buttonDeleteAlternative = request.getParameter(finals.Final.BUTTON_DELETE_ALTERNATIVE);
		String buttonAddAlternative = request.getParameter(finals.Final.BUTTON_ADD_ALTERNATIVE);
		
		String buttonDeleteCriterion = request.getParameter(finals.Final.BUTTON_DELETE_CRITERION);
		String buttonAddCriterion = request.getParameter(finals.Final.BUTTON_ADD_CRITERION);
		
		if(form != null){
			
			if(buttonAdd != null){
				// buttonAdd was clicked
				
				boolean check = true;
				// Проверка поля 'newTask'
		        if (taskConditionForm.getNewTask() == null || "".equals(taskConditionForm.getNewTask())){
		        	errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        	check = false;
		        }
				 
				
		        if(check){
				
		        	Date d = new Date();
		        	DateFormat df = new SimpleDateFormat(finals.Final.SIMPLE_DATE_FORMAT);
							   
		        	try{
		        		if(taskConditionForm.getNewTask().length() > 0){
		        			String taskFreeId = DataBaseQuery.getTask_13_FreeID();
		        			int id_Task = DataBase.getInstance(dataBaseConfig).getFreeID(taskFreeId);
					
		        			Task task = new Task(id_Task, taskConditionForm.getNewTask(), ""+df.format(d), id);			
					
		        			if( insertValue(task, dataBaseConfig) ){							
		        				taskConditionForm.reset(mapping, request);
		        			}else{
		        				errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        			}		
		        		}else{
		        			errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        		}
		        	}catch(Exception e){
		        		errors.add(finals.Final.ERROR_INSERT, new ActionMessage(finals.Final.ERROR_INSERT));
		        	}
		        }
			}
			
			if(buttonShow != null){
				// buttonShow was clicked
				
				try{
					int selectedTask = Integer.parseInt(taskConditionForm.getSelectedTask());	
					
					if(selectedTask > 0){						
						String task = DataBaseQuery.getTask_13_One_Id_Task(selectedTask);
						
						Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(task);
					
						Task t = new Task(Integer.parseInt(temp.get(0).get(0)), temp.get(0).get(1), 
								temp.get(0).get(2), Integer.parseInt(temp.get(0).get(3)));	
						
						PropertyUtils.setSimpleProperty(taskConditionForm, finals.Final.TASK, t);
						
						
						ArrayList<Alternative> alternativeTemp = new ArrayList<Alternative>();	
						try{
							// Список альтернатив									
							String alter = database.DataBaseQuery.getAlternative_14_One(selectedTask);
							
							Vector<Vector<String>> temp2 = DataBase.getInstance(dataBaseConfig).getData(alter);
							
							Iterator<Vector<String>> tempIt2 = temp2.iterator();
							while(tempIt2.hasNext()){
								Vector<String> tempL = tempIt2.next();
								
								Alternative a = new Alternative(Integer.parseInt(tempL.get(0)), tempL.get(1));
								
								alternativeTemp.add(a);
							}
							
							PropertyUtils.setSimpleProperty(taskConditionForm, finals.Final.ALTERNATIVE, alternativeTemp);			
						}catch(Exception e){
							PropertyUtils.setSimpleProperty(taskConditionForm, finals.Final.ALTERNATIVE, alternativeTemp);
						}
						
						ArrayList<Criterion> criterionTemp = new ArrayList<Criterion>();	
						try{
							// Список критериев								
							String alter = database.DataBaseQuery.getCriterion_15_One(selectedTask);
							
							Vector<Vector<String>> temp2 = DataBase.getInstance(dataBaseConfig).getData(alter);
								
							Iterator<Vector<String>> tempIt2 = temp2.iterator();
							while(tempIt2.hasNext()){
								Vector<String> tempL = tempIt2.next();
								
								Criterion a = new Criterion(Integer.parseInt(tempL.get(0)), tempL.get(1));
								
								criterionTemp.add(a);
							}
							
							PropertyUtils.setSimpleProperty(taskConditionForm, finals.Final.CRITERION, criterionTemp);			
						}catch(Exception e){
							PropertyUtils.setSimpleProperty(taskConditionForm, finals.Final.CRITERION, criterionTemp);
						}
					}else{
						
					}
				}catch(Exception e){
					
				}
			}
			
			if(buttonDeleteAlternative != null){
				// buttonDeleteAlternative was clicked
				
				try{
					int selectedTask = Integer.parseInt(taskConditionForm.getSelectedTask());
										
					String temp[] = taskConditionForm.getSelectedItemsAlternative();
					for(int i=0; i < temp.length; i++){						
						if( deleteValue(selectedTask, temp[i], dataBaseConfig) ){
							
						}else{
							errors.add(finals.Final.ERROR_ALTERNATIVE_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}					
				}catch(Exception e){
					errors.add(finals.Final.ERROR_ALTERNATIVE_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}
			
			if(buttonAddAlternative != null){
				// buttonAddAlternative was clicked
				
				boolean check = true;
				// Проверка поля 'newAlternative'
		        if (taskConditionForm.getNewAlternative() == null || "".equals(taskConditionForm.getNewAlternative())){
		        	errors.add(finals.Final.ERROR_ALTERNATIVE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_ALTERNATIVE));
		        	check = false;
		        }
				 
				
		        if(check){
				
		        	try{
		        		int selectedTask = Integer.parseInt(taskConditionForm.getSelectedTask());
					
		        		if(taskConditionForm.getNewAlternative().length() > 0){					
						
		        			Alternative a = new Alternative(selectedTask, taskConditionForm.getNewAlternative());			
					
		        			if( insertValue(a, dataBaseConfig) ){							
		        				taskConditionForm.reset(mapping, request);
		        			}else{
		        				errors.add(finals.Final.ERROR_ALTERNATIVE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_ALTERNATIVE));
		        			}		
		        		}else{
		        			errors.add(finals.Final.ERROR_ALTERNATIVE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_ALTERNATIVE));
		        		}
		        	}catch(Exception e){
		        		errors.add(finals.Final.ERROR_ALTERNATIVE_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_ALTERNATIVE));
		        	}
		        }
			}
			
			if(buttonDeleteCriterion != null){
				// buttonDeleteCriterion was clicked
				
				try{
					int selectedTask = Integer.parseInt(taskConditionForm.getSelectedTask());
					
					String temp[] = taskConditionForm.getSelectedItemsCriterion();
					for(int i=0; i < temp.length; i++){						
						if( deleteValue2(selectedTask, temp[i], dataBaseConfig) ){
							
						}else{
							errors.add(finals.Final.ERROR_CRITERION_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
						}	
					}					
				}catch(Exception e){
					errors.add(finals.Final.ERROR_CRITERION_DELETE, new ActionMessage(finals.Final.ERROR_DELETE));
				}
			}
			
			if(buttonAddCriterion != null){
				// buttonAddCriterion was clicked
				
				boolean check = true;
				// Проверка поля 'newCriterion'
		        if (taskConditionForm.getNewCriterion() == null || "".equals(taskConditionForm.getNewCriterion())){
		        	errors.add(finals.Final.ERROR_CRITERION_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_CRITERION));
		        	check = false;
		        }
				
				
		        if(check){
				
		        	try{
		        		int selectedTask = Integer.parseInt(taskConditionForm.getSelectedTask());
					
		        		if(taskConditionForm.getNewCriterion().length() > 0){					
						
		        			Criterion c = new Criterion(selectedTask, taskConditionForm.getNewCriterion());			
					
		        			if( insertValue(c, dataBaseConfig) ){							
		        				taskConditionForm.reset(mapping, request);
		        			}else{
		        				errors.add(finals.Final.ERROR_CRITERION_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_CRITERION));
		        			}		
		        		}else{
		        			errors.add(finals.Final.ERROR_CRITERION_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_CRITERION));
		        		}
		        	}catch(Exception e){
		        		errors.add(finals.Final.ERROR_CRITERION_ADD, new ActionMessage(finals.Final.ERROR_MESSAGE_CRITERION));
		        	}
		        }
			}
				
		}
	
		
		if (!errors.isEmpty()) {
	        saveErrors(request, errors);
	        return (mapping.getInputForward());
	    }
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
	
	/**
	 * <p>Вставка данных Задача</p>
	 */
	public boolean insertValue(Task t, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу				
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.insertTask_13(t);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Вставка данных Альтернатива</p>
	 */
	public boolean insertValue(Alternative a, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу				
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.insertAlternative_14(a);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Вставка данных Критерий</p>
	 */
	public boolean insertValue(Criterion c, DataBaseConfig dataBaseConfig){
		
		// Запрос на вставку данных в таблицу				
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.insertCriterion_15(c);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Удаление альтернативы</p>
	 */
	public boolean deleteValue(int code, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных из таблицы				
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.deleteAlternative_14(code, value);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
	
	/**
	 * <p>Удаление критерия</p>
	 */
	public boolean deleteValue2(int code, String value, DataBaseConfig dataBaseConfig){
		
		// Запрос на удаление данных из таблицы					
		boolean queryBnInsert = false;
		try{			
			String query = DataBaseQuery.deleteCriterion_15(code, value);
			queryBnInsert = DataBase.getInstance(dataBaseConfig).setData(query);
			
			return queryBnInsert;
		}catch(Exception e){
			return queryBnInsert;
		}
	}
}
