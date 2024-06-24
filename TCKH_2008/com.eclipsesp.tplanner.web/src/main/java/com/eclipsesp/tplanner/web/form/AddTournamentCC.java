/*
 * 31 Лип 2008 10:17:23
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 643 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-08 11:21:20 +0300 (Пт, 08 авг 2008) $
 */
package com.eclipsesp.tplanner.web.form;

import java.text.*;
import java.util.*;

import org.apache.commons.logging.*;

import com.eclipsesp.tplanner.core.bean.*;


/**
 * Bean class for add tournaments form
 * */

public class AddTournamentCC
        extends Tournament {

	/**
     * 
     */
    private static final long serialVersionUID = 1484361394933367872L;
       
    private final Log logger = LogFactory.getLog(getClass());
    
    /**Entered start date*/
    private String viewStartDate;
    /**Entered finish date*/
    private String viewFinishDate;
    
    public AddTournamentCC() {
    	this.id = 0;
    	this.setStatusId(1);
    	this.setAccountId(1);
    }
        
    /**Convert Date to String*/
    public String formatDate(Date date) {
    	if(date!=null){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
    	return formatter.format(date);      
    	}
    	else{
    		return null;
    	}
    }
    
    /**Convert String to Date*/
    public Date formatDate(String date) {
    	if(date!=""){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");			
    	try {
	        return formatter.parse(date);
        } catch (ParseException e) {
	        this.logger.error(e);
        }
    	}
    	else{
        return null;
    	}
    	return null;
    }

	public String getViewStartDate() {
    	return viewStartDate;
    }

	public void setViewStartDate(String viewStartDate) {
    	this.viewStartDate = viewStartDate;
    	this.setStartDate(this.formatDate(viewStartDate));
    }


	public String getViewFinishDate() {
    	return viewFinishDate;
    }


	public void setViewFinishDate(String viewFinishDate) {
		this.setFinishDate(this.formatDate(viewFinishDate));
    	this.viewFinishDate = viewFinishDate;
    }
}
