/*
 * 4 Сер 2008 12:08:50
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 888 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-14 14:20:26 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.web.validators;

import java.util.*;

import org.springframework.validation.*;

import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.utils.*;
import com.eclipsesp.tplanner.web.form.*;

/**Validate data from addTournament controller*/

public class AddTournamentValidator {

	public void validate(AddTournamentCC addForm, Errors errors) {
		validateTournamentName(addForm, errors);
		validateGameKind(addForm, errors);		
		validateStartDate(addForm, errors);	
		validateFinishDate(addForm, errors);			
		validateSQLInjection(addForm, errors);
		validateDescription(addForm, errors);
		if(!errors.hasErrors()){
			validateDateDifference(addForm, errors);
		}
	}
	
	private void validateDescription(AddTournamentCC addForm, Errors errors) {
		if (addForm.getDescription().length()>255||!this.checkTokensLength(addForm.getTournamentName())) {
			errors.reject("error.incorrectTournamentName");
		}
    }

	private void validateDateDifference(AddTournamentCC addForm, Errors errors) {		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(addForm.getStartDate());
		Long startDate = calendar.getTimeInMillis();
		calendar.setTime(addForm.getFinishDate());
		Long finishDate = calendar.getTimeInMillis();				
		if(finishDate<startDate){
			errors.reject("error.wrongDateDifference");
		}				
	}
	
	private void validateFinishDate(AddTournamentCC addForm, Errors errors) {
		if (!Security.checkByPattern(addForm.getViewFinishDate(), Security.DATE_PATTERN)) {
			errors.reject("error.incorrectFinishDate");
		}
    }

	private void validateStartDate(AddTournamentCC addForm, Errors errors) {
		if (!Security.checkByPattern(addForm.getViewStartDate(), Security.DATE_PATTERN)) {
			errors.reject("error.incorrectStartDate");
		}
    }	

	private void validateGameKind(AddTournamentCC addForm, Errors errors) {
		if(addForm.getGameKind().length() < 3 || addForm.getGameKind().length()>30
				||!this.checkTokensLength(addForm.getTournamentName())) {
			errors.reject("error.incorrectGameKind");
		}
    }

	public void validateTournamentName(AddTournamentCC addForm, Errors errors) {
		if (addForm.getTournamentName().length() < 3 || addForm.getTournamentName().length()>100
				||!this.checkTokensLength(addForm.getTournamentName())) {
			errors.reject("error.incorrectTournamentName");
		}
	}	
	
	public void validateSQLInjection(AddTournamentCC addForm, Errors errors) {
		if (!Security.checkOnSQLInjection(DataUtilities.convertClassToMap(
		        AddTournamentCC.class, addForm))) {
			errors.reject("error.sqlInjection");
		}
	}
	public boolean checkTokensLength(String input){
		StringTokenizer stringTokenizer = new StringTokenizer(input," ");
		while(stringTokenizer.hasMoreElements()){
			if(stringTokenizer.nextElement().toString().length()>12){
				return false;
			}			
		}
		return true;
		
	}
}
