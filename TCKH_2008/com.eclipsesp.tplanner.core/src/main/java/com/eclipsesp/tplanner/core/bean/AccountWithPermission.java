/*
 * 6 ��� 2008 10:29:39
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.core.bean;

import com.sun.accessibility.internal.resources.*;

public class AccountWithPermission
        extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4453676211105720906L;

	protected int permission;

	public AccountWithPermission() {

	}

	public AccountWithPermission(AccountWithPermission account){
		this.address = account.getAddress();
		this.city = account.getCity();
		this.description = account.getDescription();
		this.email = account.getEmail();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastname();
		this.nick = account.getNick();
		this.password = account.getPassword();
		this.role_Name = account.getRole_Name();
		this.zip = account.getZip();
		this.id = account.getId();
	}
	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

}
