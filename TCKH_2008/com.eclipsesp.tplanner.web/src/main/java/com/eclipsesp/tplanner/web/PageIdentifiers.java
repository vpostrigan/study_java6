/**
 * 22.07.2008 16:58:29
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 412 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-01 16:45:37 +0300 (Пт, 01 авг 2008) $
 */
package com.eclipsesp.tplanner.web;

/**
 * This bean is intended to be used within JSP files in order to get access to
 * static constants defined in {@link IPageIdentifiers} interface.
 * 
 * @author Pavel Marinchev
 */
public class PageIdentifiers
        implements IPageIdentifiers {

	public String getHOME() {
		return IPageIdentifiers.HOME;
	}

	public String getTPLANNER_TITLE() {
		return IPageIdentifiers.TPLANNER_TITLE;
	}

	public String getLOGIN() {
		return IPageIdentifiers.LOGIN;
	}

	public String getREGISTRATION() {
		return IPageIdentifiers.REGISTRATION;
	}
	
	public String getTOURNAMENTS(){
		return IPageIdentifiers.TOURNAMENTS;
	}
	
	public String getADD_TOURNAMENT(){
		return IPageIdentifiers.ADD_TOURNAMENT;
	}
	
	public String getEDIT_TOURNAMENT(){
		return IPageIdentifiers.EDIT_TOURNAMENT;
	}
}
