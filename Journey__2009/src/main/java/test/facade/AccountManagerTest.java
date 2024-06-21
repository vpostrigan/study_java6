package test.facade;

import java.util.Collection;
import java.util.Date;

import journey.core.bean.Account;
import journey.core.dao.jdbc.AccountJdbcDaoSupport;
import journey.core.facade.impl.AccountManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.BaseDatabaseTests;

public class AccountManagerTest extends BaseDatabaseTests {
	
	/** Object being tested */
	private AccountManager manager;
	
	@Before
	public void setUp() throws Exception {
		AccountJdbcDaoSupport daoSupport = this.setUp(AccountJdbcDaoSupport.class);
		this.manager = new AccountManager();
		this.manager.setDaoSupport(daoSupport);
	}
	
	@Test
	public void getAll() {
		Collection<Account> accounts = this.manager.getAll();

		Assert.assertNotNull(accounts);
	}

	@Test
	public void addNewAccount() {
		Account account = new Account(0, "asd@asd2.com", "nick", "ad", "123456789", "M", new Date(),
		              "city", "state", "country", "zip", "street1", "street2", "status", 
		              new Date(), "bio");
		try {
			this.manager.createAccount(account);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
		Collection<Account> accounts = this.manager.getAll();
		for (Account a : accounts) {
			this.logger.info(a.getEmail() + a.getId() + a.getZip() + "\n");
		}
		Assert.assertTrue( account.equals(this.manager.getByEmailAndPass("asd@asd2.com", "12345678")) );
	}
}
