/*
 * 29 Лип 2008 16:38:37
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 307 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 20:39:24 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

public class Status
        extends Bean {

	/**
     * 
     */
    private static final long serialVersionUID = 1351281215442649068L;
    
	private String statusName;	
	private String statusType;
	
	public String getStatusName() {
    	return statusName;
    }
	public void setStatusName(String statusName) {
    	this.statusName = statusName;
    }
	public String getStatusType() {
    	return statusType;
    }
	public void setStatusType(String statusType) {
    	this.statusType = statusType;
    }
	public Status(Long id, String statusName, String statusType) {
	    this.id = id;
	    this.statusName = statusName;
	    this.statusType = statusType;
    }
	public Status() {
	    //Do nothing
    }
}
