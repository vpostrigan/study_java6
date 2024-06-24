/*
 * 7 Сер 2008 11:28:39
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.bean;

/**
 * Bean initialize at the startup and contains data for promt creating
 * 
 * @author akrumin
 */
public class InvationMessageBean {
	protected String mainText;

	protected String regAddress;

	public String getMainText() {
		return this.mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public String getRegAddress() {
		return this.regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

}
