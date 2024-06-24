/*
 * 29 Лип 2008 14:49:14
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.form;

/**
 * Command class for login form.
 * 
 * @author akrumin
 */
public class LoginCC {
	private String email;

	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
