/*
 * 30 ��� 2008 11:15:51
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.form;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Command class for new account registration
 * 
 * @author akrumin
 */
public class RegistrationCC
        extends AccountWithPermission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2640948007990142115L;

	protected String passwordconf;

	public RegistrationCC() {
		this.id = 0;
		this.role_Name = "users";
	}

	public String getPasswordconf() {
		return this.passwordconf;
	}

	public void setPasswordconf(String passwordconf) {
		this.passwordconf = passwordconf;
	}

}
