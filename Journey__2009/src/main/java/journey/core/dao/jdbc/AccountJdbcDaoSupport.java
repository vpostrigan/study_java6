package journey.core.dao.jdbc;

import java.util.Collection;

import javax.sql.DataSource;

import journey.core.bean.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AccountJdbcDaoSupport extends SimpleJdbcDaoSupport {
	
	private SimpleJdbcTemplate template;
	
	@Autowired
	public void init(DataSource ds) {
		this.setDataSource(ds);
	}

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		this.template = this.getSimpleJdbcTemplate();
	}
	
	/**
	 * @return list of all Accounts in the table account
	 * @throws DataAccessException
	 */
	public Collection<Account> getAll() throws DataAccessException {
		return this.template.query("select * from account order by `email`",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class));
	}
	
	public Account getByEmailAndPass(String email, String password) throws DataAccessException {
		return this.template.queryForObject("select * from account where `email`=? and `password`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class), email, password);
	}
	
	public void createAccount(Account a) throws DataAccessException {
		this.template.update("insert into `account`(`email`,`firstname`,`lastname`,`password`," +
				"`gender`,`birthday`,`city`,`state`,`country`,`zip`,`street1`,`street2`," +
				"`status`,`registered`,`bio`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				a.getEmail(), a.getFirstname(), a.getLastname(), a.getPassword(), a.getGender(),
				a.getBirthday(), a.getCity(), a.getState(), a.getCountry(), a.getZip(), a.getStreet1(), 
				a.getStreet2(), a.getStatus(), a.getRegistered(), a.getBio());
	}
}
