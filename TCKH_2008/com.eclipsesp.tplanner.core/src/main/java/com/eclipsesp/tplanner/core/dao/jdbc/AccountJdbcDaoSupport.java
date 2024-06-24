/*
 * 17.07.2008 16:30:27
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 917 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-14 19:04:48 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.utils.*;

/**
 * JDBC DAO support for managing systems accounts
 * 
 * @author Pavel Marinchev
 * @author akrumin
 * @author vpostrigan
 * @author dproshkin
 */
public class AccountJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {
	public final String SINGLE_QUERY =
	        "select *,`role`.`permission_def` as permission from `account` left join `role` on `role`.`role_name`=`account`.`role_name` where `%s` = ?";

	public final String MULTI_QUERY = "select * from `account` where %s";

	public final String ACCOUNT_ADD =
	        "insert into `account` (%s) values (?,?,?,?,?,?,?,?,?,?,?)";

	public final String ACCOUNT_UPDATE =
	        "UPDATE `account`  SET %s where `id` = ";

	private final String GET_ACCOUNT_BY_NICK_FIRST_LETTER =
	        "select * from `account` where `nick` REGEXP ? or `nick` REGEXP ? order by `nick`";

	private final String SEARCH_ACCOUNTS =
	        "select * from `account` where (`nick` like ? or `firstname` like ?) or (`lastname` like ? or  (`city` like ? or `address` like ?)) order by `nick`";

	private final String GET_META_DATA = "select * from `metadata`";

	private final String ACCOUNT_DELETE =
	        "DELETE FROM `account` WHERE `%s` = ?";

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		// this.setPassword = new SetPassword(getDataSource());
	}

	/**
	 * 
	 * @return return all rows from account table as Collection of accounts
	 */
	public Collection<Account> getAll() {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.query("select * from account order by `nick`",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class));
	}

	public Collection<Account> getAllNotFromUser2Tournament(int tournamentId)
	    throws DataAccessException {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.query(
		        "select * from `account` where `id` in (select `account_id` from `user2tournament` where `tournament_id` = ?) order by `nick`",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        tournamentId);
	}

	public Collection<Account> getAllFromUser2Tournament(int tournamentId)
	    throws DataAccessException {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.query(
		        "select * from `account` where `id` not in (select `account_id` from `user2tournament` where `tournament_id` = ?) order by `nick`",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        tournamentId);
	}

	/**
	 * Method to add new account into database
	 * 
	 * @param account -
	 *            object of Account class
	 * @return
	 */
	public boolean addNewAccount(Account account) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		StringBuffer sb = new StringBuffer();
		List<Object> s = new ArrayList<Object>();
		Map<String, Object> data =
		        DataUtilities.convertClassToMap(Account.class, account);
		for (String key : data.keySet()) {
			sb.append('`');
			sb.append(key);
			sb.append('`');
			sb.append(',');
			s.add(data.get(key));
		}
		sb.deleteCharAt(sb.length() - 1);
		String query = String.format(this.ACCOUNT_ADD, sb.toString());
		template.update(query, s.toArray());
		return true;
	}

	/**
	 * Method to add new account into database
	 * 
	 * @param account -
	 *            object of Account class
	 * @return
	 */
	public int setAccount(Account account, int id) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		StringBuffer sb = new StringBuffer();
		List<Object> s = new ArrayList<Object>();
		Map<String, Object> data =
		        DataUtilities.convertClassToMap(Account.class, account);
		for (String key : data.keySet()) {
			sb.append('`');
			sb.append(key);
			sb.append('`');
			sb.append(" = ?, ");
			s.add(data.get(key));
		}
		sb.deleteCharAt(sb.length() - 2);
		String query = String.format(this.ACCOUNT_UPDATE + id, sb.toString());
		this.logger.info(query.toString());
		return template.update(query, s.toArray());
	}

	public int setPassword(long id, String password) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.update("update `account` set `password` = password(?) "
		        + "where `id` = ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        id);
	}

	public void deleteAccount(Account account) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		this.logger.info("======class AccountJdbcDaoSupport,method deleteAccount,string to delete:"
		        + String.format(this.ACCOUNT_DELETE, "id")
		        + "parametr when delete:" + account.getId());
		template.update(String.format(this.ACCOUNT_DELETE, "id"),
		        account.getId());
	}

	/**
	 * finding rows, in account table, with supplied argument.
	 * 
	 * @param paramName -
	 *            name of field to search by
	 * @param param -
	 *            value of field
	 * @return collection of Account class objects
	 */
	public Collection<AccountWithPermission> getByParam(Object paramName,
	    Object param) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		String pattern = String.format(this.SINGLE_QUERY, paramName);
		Collection<AccountWithPermission> c = null;
		c =
		        template.query(
		                pattern,
		                ParameterizedBeanPropertyRowMapper.newInstance(AccountWithPermission.class),
		                param);
		return c;
	}

	/**
	 * finding rows, in account table, with supplied argument.
	 * 
	 * @param paramName -
	 *            name of field to search by
	 * @param param -
	 *            value of field
	 * @return collection of Account class objects
	 */
	public Collection<Account> getByParams(Map<String, Object> params) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		StringBuffer sb = new StringBuffer();
		List<Object> s = new ArrayList<Object>();
		for (String key : params.keySet()) {
			sb.append('`');
			sb.append(key);
			sb.append('`');
			sb.append(" = ? and ");
			s.add(params.get(key));
		}
		sb.delete(sb.length() - 4, sb.length());
		this.logger.info(sb.toString());
		String pattern = String.format(this.MULTI_QUERY, sb.toString());
		return template.query(pattern,
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        s.toArray());
	}

	public Collection<Account> getByNickFirstLetter(String firstLetter,
	    String subLetter) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.query(this.GET_ACCOUNT_BY_NICK_FIRST_LETTER,
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        firstLetter, subLetter);
	}

	public Collection<Account> searchByNickSurnameNameDescription(String nick,
	    String surname, String name, String city, String address) {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.query(this.SEARCH_ACCOUNTS,
		        ParameterizedBeanPropertyRowMapper.newInstance(Account.class),
		        nick, surname, name, city, address);
	}

	public int getMetaData() throws DataAccessException {
		SimpleJdbcTemplate template = this.getSimpleJdbcTemplate();
		return template.queryForInt(this.GET_META_DATA);
	}
}
