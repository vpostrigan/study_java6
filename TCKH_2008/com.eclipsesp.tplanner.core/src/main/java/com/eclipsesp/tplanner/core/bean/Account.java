/*
 * 17 ��� 2008 17:05:59
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 876 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 19:56:40 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;

/**
 * This class represent table "Account" from database.
 * 
 * Class defines all fields from table "Account" and implement
 * {@link Serializable} interface.
 * 
 * @author rshportko
 */
public class Account
        extends Bean {

	private static final long serialVersionUID = 1L;

	/** User's e-mail address */
	protected String email;

	/** User's fictitious name */
	protected String nick;

	/** User's password */
	protected String password;

	/** User's lastName */
	protected String lastName;

	/** User's city */
	protected String city;

	/** User's zone improvement plan code */
	protected String zip;

	/** User's address */
	protected String address;

	/** User's first name */
	protected String firstName;

	/** User's role */
	protected String role_Name;

	/** Description */
	protected String description;

	/** Public default constructor */
	public Account() {

	}

	/** Initializes all fields */
	public Account(long id, String email, String nick, String password,
	        String surname, String city, String zip, String address,
	        String userName, String roleName, String description) {
		super(id);
		this.email = email;
		this.nick = nick;
		this.password = password;
		this.lastName = surname;
		this.city = city;
		this.zip = zip;
		this.address = address;
		this.firstName = userName;
		this.role_Name = roleName;
		this.description = description;
	}
	public Account(Account account){
		this(account.getId(),account.getEmail(),account.getNick(),account.getPassword(),
				account.getLastname(),account.getCity(),account.getZip(),account.getAddress(),
				account.getFirstName(),account.getRole_Name(),account.getDescription());   
	}
	
	/** Returns user's e-mail */
	public String getEmail() {
		return this.email;
	}

	/** Sets user's e-mail */
	public void setEmail(String mail) {
		this.email = mail;
	}

	/** Returns user's nick */
	public String getNick() {
		return this.nick;
	}

	/** Sets user's nick */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/** Returns user's password */
	public String getPassword() {
		return this.password;
	}

	/** Sets user's password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Returns user's lastName */
	public String getLastname() {
		return this.lastName;
	}

	/** Sets user's lastName */
	public void setLastname(String surname) {
		this.lastName = surname;
	}

	/** Returns user's city */
	public String getCity() {
		return this.city;
	}

	/** Sets user's city */
	public void setCity(String city) {
		this.city = city;
	}

	/** Returns user's ZIP code */
	public String getZip() {
		return this.zip;
	}

	/** Sets user's ZIP code */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/** Returns user's address */
	public String getAddress() {
		return this.address;
	}

	/** Sets user's address */
	public void setAddress(String address) {
		this.address = address;
	}

	/** Returns user's first name */
	public String getFirstName() {
		return this.firstName;
	}

	/** Sets user's first name */
	public void setFirstName(String userName) {
		this.firstName = userName;
	}

	/** Returns user's role */
	public String getRole_Name() {
		return this.role_Name;
	}

	/** Sets user's role */
	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
