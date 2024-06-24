/*
 * 17.07.2008 16:30:27
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 917 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-14 19:04:48 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**
 * An implementation of {@link IAccountManager} facade. In particular, it deals
 * with JDBC DAO support classes
 * 
 * @author Pavel Marinchev
 * @author akrumin
 * @author vpostrigan
 */
public class AccountManager
        implements IAccountManager {

	@Autowired
	protected AccountJdbcDaoSupport daoSupport;

	/**
	 * 
	 * @return return all rows from account table as Collection of accounts
	 */
	public Collection<Account> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public void setPassword(long id, String password) {
		this.daoSupport.setPassword(id, password);
	}

	/**
	 * Method to add new account into database
	 * 
	 * @param account -
	 *            object of Account class
	 * @return
	 */
	public void newAccount(Account account) {
		this.daoSupport.addNewAccount(account);
	}

	/**
	 * @param id -
	 *            id of row you want to search
	 * @return return Account class responds specified id
	 */

	public AccountWithPermission getById(long id) {
		Collection<AccountWithPermission> c =
		        this.daoSupport.getByParam("id", id);
		Iterator<AccountWithPermission> i = c.iterator();
		if (i.hasNext()) {
			return i.next();
		} else {
			return null;
		}
	}

	/**
	 * @param email -
	 *            email of row you want to search
	 * @return return Account class responds specified email
	 */
	public AccountWithPermission getByEmail(String email) {
		Collection<AccountWithPermission> c =
		        this.daoSupport.getByParam("email", email);
		Iterator<AccountWithPermission> i = c.iterator();
		if (i.hasNext()) {
			return i.next();
		} else {
			return null;
		}
	}

	/**
	 * @param email -
	 *            email value of row you want to search
	 * @return return Account class responds specified email
	 */
	public Account getByNick(String nick) {
		Collection<AccountWithPermission> c =
		        this.daoSupport.getByParam("nick", nick);
		Iterator<AccountWithPermission> i = c.iterator();
		if (i.hasNext()) {
			return i.next();
		} else {
			return null;
		}
	}

	/**
	 * @param city -
	 *            city value of row you want to search
	 * @return return collection of Account objects responds specified city
	 */
	public Collection<AccountWithPermission> getByCity(String city) {
		return this.daoSupport.getByParam("city", city);
	}

	/**
	 * @param role_name -
	 *            role_name of row you want to search
	 * @return return collection of Account objects responds specified email
	 */
	public Collection<AccountWithPermission> getByRoleName(String roleName) {
		return this.daoSupport.getByParam("role_name", roleName);
	}

	/**
	 * @param role_name -
	 *            role_name of row you want to search
	 * @return return collection of Account objects responds specified email
	 */
	public Collection<Account> getByNameSurname(String firstname,
	    String lastname) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstname", firstname);
		map.put("lastname", lastname);
		return this.daoSupport.getByParams(map);
	}

	public Collection<Account> getByNickFirstLetter(String firstLetter,
	    String subLetter) {
		return this.daoSupport.getByNickFirstLetter(firstLetter, subLetter);
	}

	public Collection<Account> searchByNickSurnameNameDescription(String nick,
	    String surname, String name, String city, String address) {
		return this.daoSupport.searchByNickSurnameNameDescription(nick,
		        surname, name, city, address);
	}

	public Collection<Account> getAllNotFromUser2Tournament(int tournamentId)
	    throws DataAccessException {
		return this.daoSupport.getAllNotFromUser2Tournament(tournamentId);
	}

	public Collection<Account> getAllFromUser2Tournament(int tournamentId)
	    throws DataAccessException {
		return this.daoSupport.getAllFromUser2Tournament(tournamentId);
	}

	public int setAccount(Account account, int id) {
		return this.daoSupport.setAccount(account, id);
	}

	public void deleteAccount(Account account) throws DataAccessException {
		this.daoSupport.deleteAccount(account);
	}

	public int getMetaData() throws DataAccessException {
		return this.daoSupport.getMetaData();
	}

	public AccountJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(AccountJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
