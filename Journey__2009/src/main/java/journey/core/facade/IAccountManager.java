package journey.core.facade;

import java.util.Collection;

import journey.core.bean.Account;

import org.springframework.dao.DataAccessException;

/**
 * 
 * @author Postrigan V.A.
 */
public interface IAccountManager {
	Collection<Account> getAll() throws DataAccessException;
	
	Account getByEmailAndPass(String email, String password) throws DataAccessException;
	
	void createAccount(Account a) throws DataAccessException;
}
