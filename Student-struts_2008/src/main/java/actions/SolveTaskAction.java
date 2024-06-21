package actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import math.AlternativeChoose;
import math.AlternativeChooseBuilder;
import math.Director;
import math.MultiCriterial_AlternativeChooseBuilder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import beans.Alternative;
import beans.Criterion;
import beans.PeopleEmail;
import beans.Task;
import database.DataBase;
import database.DataBaseConfig;
import database.DataBaseQuery;
import forms.SolveTaskForm;

public class SolveTaskAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Получение параметров соединения с БД 
		DataBaseConfig dataBaseConfig = (database.DataBaseConfig)
			servlet.getServletContext().getAttribute(finals.Final.DATA_BASE_CONFIG);
	
		ActionErrors errors = new ActionErrors();
		SolveTaskForm solveTaskForm = (SolveTaskForm) form;		
				
		String buttonShow = request.getParameter(finals.Final.BUTTON_SHOW);	
		String buttonSolve = request.getParameter(finals.Final.BUTTON_SOLVE);
		
		if(form != null){
						
			ArrayList<Criterion> criterionTemp = new ArrayList<Criterion>();
			ArrayList<Alternative> alternativeTemp = new ArrayList<Alternative>();
			int selectedTask = 0;
			try{
				// Выбранная задача
				selectedTask = Integer.parseInt(solveTaskForm.getSelectedTask());	
				
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
					
					PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.ALTERNATIVE, alternativeTemp);			
				}catch(Exception e){
					PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.ALTERNATIVE, alternativeTemp);
				}
				
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
					
					PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.CRITERION, criterionTemp);			
				}catch(Exception e){
					PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.CRITERION, criterionTemp);
				}
			}catch(Exception e){
				
			}
			
			
			if(buttonShow != null){
				// buttonShow was clicked
				
				try{
					if(selectedTask > 0){
						String task = DataBaseQuery.getTask_13_One_Id_Task(selectedTask);
						
						Vector<Vector<String>> temp = DataBase.getInstance(dataBaseConfig).getData(task);
						
						Task t = new Task(Integer.parseInt(temp.get(0).get(0)), temp.get(0).get(1), 
								temp.get(0).get(2), Integer.parseInt(temp.get(0).get(3)));	
						
						PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.TASK, t);
					}else{
						
					}
				}catch(Exception e){
					
				}
			}
			
			if(buttonSolve != null){
				// buttonSolve was clicked
				
				try{
					int rows = solveTaskForm.getAlternative().size();
					int counts = solveTaskForm.getCriterion().size();
					
					
					String temp[] = solveTaskForm.getValue();
					int inputTaskCondition[][][] = new int[counts][rows][rows];
					
					int x = 0;
					for(int i=0; i < counts; i++){
						for(int j=0; j < rows; j++){
							for(int k=0; k < rows; k++){
								inputTaskCondition[i][j][k] = Integer.parseInt(temp[x++]);
							}
						}
					}
					
					
					Director director = new Director();
					AlternativeChooseBuilder alterBuilder = new MultiCriterial_AlternativeChooseBuilder(); 
					
					director.setAlternativeChooseBuilder(alterBuilder);
					director.constructResult(inputTaskCondition, rows);
										
					AlternativeChoose alternativeChoose = director.getResult();
					
					
					ArrayList<PeopleEmail> result = new ArrayList<PeopleEmail>();
					int r[] = alternativeChoose.getMND();
					
					for(int i=0; i < r.length; i++){
						PeopleEmail p = new PeopleEmail(i+1, ""+r[i]);
						result.add(p);
					}
					
					//solveTaskForm.reset(mapping, request);
					
					PropertyUtils.setSimpleProperty(solveTaskForm, finals.Final.RESULT, result);
					
				}catch(Exception e){
					errors.add(finals.Final.ERROR_SOLVE, new ActionMessage(finals.Final.ERROR_SOLVE));
				}
			}
		}
		
		if(!errors.isEmpty()){
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		return mapping.findForward(finals.Final.FORWARD_SUCCESS);
	}
}
