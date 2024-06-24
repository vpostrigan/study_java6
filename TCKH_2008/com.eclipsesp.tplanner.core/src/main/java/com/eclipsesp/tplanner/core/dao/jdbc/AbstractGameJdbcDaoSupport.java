/*
 * 24 Лип 2008 11:03:18
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 249 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-28 16:13:34 +0300 (Пн, 28 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.utils.*;

public abstract class AbstractGameJdbcDaoSupport<T extends Game>
        extends AbstractSimpleJdbcDaoSupport
        implements ClazzFactory<T> {

	protected T gameObject;

	@Override
	protected void initDao() throws Exception {
		this.gameObject = this.make();
	}

	/**
	 * @return List of all game in the table GameTeam and GameUser
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public Collection<T> getAll() throws DataAccessException {
		return (Collection<T>) this.getSimpleJdbcTemplate().query(
		        "select * from " + this.gameObject.getTableName(),
		        ParameterizedBeanPropertyRowMapper.newInstance(this.gameObject.getClass()));
	};

	public void insertToUpdateGame(T game) throws DataAccessException {
		this.getSimpleJdbcTemplate().update(
		        String.format("update `%s` set `group_Id`=?,`winner`=?," +
				"`date`=?,`score_1`=?,`score_2`=?,`%s`=?,`%s`=? where `id`=?",
				this.gameObject.getTableName(),
				this.gameObject.getTableName().subSequence(4, 8)+"_1",
				this.gameObject.getTableName().subSequence(4, 8)+"_2"), 
				game.getGroupId(),game.getWinner(),
				game.getDate(),game.getScore1(),game.getScore2(),
				game.getParticipant1(),game.getParticipant2(),game.getId());
	};

	public void deleteGame(T game) throws DataAccessException {
		this.getSimpleJdbcTemplate().update(
		        String.format("delete from `%s` where `id`=?",
		                this.gameObject.getTableName()), game.getId());
	}
	
	@SuppressWarnings("unchecked")
    public Collection<T> getOneField(String nameField, String valueToSearch) 
    throws DataAccessException{
		return (Collection<T>) this.getSimpleJdbcTemplate().query(
				String.format("select * from `%s` where `%s` =?", 
						this.gameObject.getTableName(),nameField),
				ParameterizedBeanPropertyRowMapper.newInstance(this.gameObject.getClass()),
				valueToSearch);
		
	};
	
	@SuppressWarnings("unchecked")
    public Collection<T> getPair(String participant1, String participant2) 
    throws DataAccessException{
		return (Collection<T>) this.getSimpleJdbcTemplate().query(
				String.format("select * from %s where %s_1 =? and %s_2 =?",
						this.gameObject.getTableName(),
						this.gameObject.getTableName().subSequence(4, 8),
						this.gameObject.getTableName().subSequence(4, 8)),
				ParameterizedBeanPropertyRowMapper.newInstance(this.gameObject.getClass()),
				participant1,participant2);
 
		
	};
	

}
