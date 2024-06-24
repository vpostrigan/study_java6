/*
 * 06.08.2008 12:53:39
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 572 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-06 18:01:16 +0300 (Ср, 06 авг 2008) $
 */
package com.eclipsesp.tplanner.web;

/**
 * @author vpostrigan
 */
public class DatabasePatcher {

	private String driverClassName;

	private String url;

	private String username;

	private String password;

	public DatabasePatcher() {

	}

	public DatabasePatcher(String driverClassName, String url, String username,
	        String password) {
		super();
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
