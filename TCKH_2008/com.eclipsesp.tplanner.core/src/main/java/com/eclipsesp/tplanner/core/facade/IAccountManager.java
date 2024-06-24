/*
 * 17.07.2008 16:30:27
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 917 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-14 19:04:48 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Defines general contract to manage persistency system users/roles/teams.
 * 
 * @author Pavel Marinchev
 * @author akrumin
 * @author vpostrigan
 */
public interface IAccountManager {

	Collection<Account> getAll() throws DataAccessException;

	Collection<Account> getAllNotFromUser2Tournament(int tournamentId)
	    throws DataAccessException;

	Collection<Account> getAllFromUser2Tournament(int tournamentId)
	    throws DataAccessException;

	void setPassword(long id, String password);

	AccountWithPermission getById(long id);

	AccountWithPermission getByEmail(String email);

	void newAccount(Account account);

	Account getByNick(String nick);

	Collection<AccountWithPermission> getByCity(String city);

	Collection<AccountWithPermission> getByRoleName(String roleName);

	Collection<Account> getByNameSurname(String firstname, String lastname);

	Collection<Account> getByNickFirstLetter(String firstLetter,
	    String subLetter);

	Collection<Account> searchByNickSurnameNameDescription(String nick,
	    String surname, String name, String city, String address);

	int setAccount(Account account, int id);

	int getMetaData() throws DataAccessException;

	void deleteAccount(Account account) throws DataAccessException;
}
