package journey.core.facade.impl;

import java.util.Collection;

import journey.core.bean.Account;
import journey.core.dao.jdbc.AccountJdbcDaoSupport;
import journey.core.facade.IAccountManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class AccountManager implements IAccountManager{
	
	@Autowired
	protected AccountJdbcDaoSupport daoSupport;
	
	public AccountJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(AccountJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	
	public Collection<Account> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}
	
	public Account getByEmailAndPass(String email, String password) throws DataAccessException {
		return this.daoSupport.getByEmailAndPass(email, password);
	}
	
	public void createAccount(Account a) throws DataAccessException {
		this.daoSupport.createAccount(a);
	}
}
