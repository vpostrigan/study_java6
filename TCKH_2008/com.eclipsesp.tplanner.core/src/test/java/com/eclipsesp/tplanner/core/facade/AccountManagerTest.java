/*
 * 21.07.2008 18:21:10
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 549 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-06 11:28:33 +0300 (Ср, 06 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.utils.*;

/**
 * Test account manager functionality
 * 
 * @author Pavel Marinchev
 * @author akrumin
 */
public class AccountManagerTest
        extends BaseDatabaseTests {
	/** Object being tested */
	private AccountManager manager;

	@Before
	public void setUp() throws Exception {
		AccountJdbcDaoSupport daoSupport =
		        this.setUp(AccountJdbcDaoSupport.class);
		this.manager = new AccountManager();
		this.manager.setDaoSupport(daoSupport);
	}

	@Test
	public void addNewAccount() {
		Account account =

		        new Account(0, "asd@asd2.com", "nick", "ad", null, null, null,
		                null, "username", "users", null);
		try {
			this.manager.newAccount(account);
		} catch (Exception e) {
			this.logger.error(e);
			// Assert.fail();
		}
		Collection<Account> accounts = this.manager.getAll();
		Set<String> emails = new HashSet<String>();
		for (Account a : accounts) {
			emails.add(a.getEmail());
			this.logger.info(a.getEmail() + a.getId() + a.getZip()
			        + a.getRole_Name() + "\n");
		}
		Assert.assertTrue(account.getEmail().compareTo(
		        this.manager.getByEmail("asd@asd2.com").getEmail()) == 0);
	}

	@Test
	public void setAccount() {
		Account account =
		        new Account(1, "asd@asd2.com", "nick", "asdasdasd", "surname",
		                "city", "132123", "address", "username",
		                "administrators", null);
		try {
			this.manager.setAccount(account, 1);
		} catch (Exception e) {
			this.logger.error(e);
			// Assert.fail();
		}
		Collection<Account> accounts = this.manager.getAll();
		Set<String> emails = new HashSet<String>();
		for (Account a : accounts) {
			emails.add(a.getEmail());
			this.logger.info(a.getEmail() + a.getId() + a.getZip()
			        + a.getRole_Name() + "\n");
		}
		Assert.assertTrue(account.getEmail().compareTo(
		        this.manager.getByEmail("asd@asd2.com").getEmail()) == 0);
	}

	@Test
	public void getAll() {
		Collection<Account> accounts = this.manager.getAll();
		Set<String> emails = new HashSet<String>();
		for (Account a : accounts) {
			emails.add(a.getEmail());
			this.logger.info(a.getEmail() + a.getId() + a.getZip()
			        + a.getRole_Name());
		}
	}

	@Test
	public void getByNameSurname() {
		Iterator<Account> i =
		        this.manager.getByNameSurname("Pavel", "Marinchev").iterator();
		Assert.assertEquals(i.next().getEmail(), "pmarinchev@eclipse-sp.com");
	}

	@Test
	public void getById() {
		Account i = this.manager.getById(1);
		Map<String, Object> m =
		        DataUtilities.convertClassToMap(Account.class, i);
		for (String s : m.keySet()) {
			this.logger.info(s + " = " + m.get(s) + "\n");
		}
		Assert.assertEquals(i.getEmail(), "pmarinchev@eclipse-sp.com");

	}

}
