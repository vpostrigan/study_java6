/*
 * 31.07.2008 12:39:16
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.bean;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * User bean (restricted bean account)
 * 
 * @author vpostrigan
 */
public class UsersView
        extends Account {

	private static final long serialVersionUID = -4547491072331718705L;

	private long id;

	private String nick;

	private String firstName;

	private String lastName;

	private String description;

	public UsersView(long id, String nick, String firstName, String lastName,
	        String description) {
		this.id = id;
		this.nick = nick;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getNick() {
		return this.nick;
	}

	@Override
	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}
