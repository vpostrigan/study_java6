/*
 * 25.07.2008 15:16:37
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 283 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:39:39 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * @author vpostrigan
 */
public class TeamJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	private SimpleJdbcTemplate template;

	private final String GET_ALL =
	        "select `id`, `team_name`, `account_id` from `team`";

	private final String GET_TEAM_BY_ID =
	        "select `id`, `team_name`, `account_id` from `team` where `id`=?";

	private final String GET_TEAM_BY_NAME =
	        "select `id`, `team_name`, `account_id` from `team` where `team_name`=?";

	private final String CREATE_TEAM =
	        "insert into `team`(`team_name`, `account_id`) values(?, ?)";

	private final String UPDATE_TEAM_NAME =
	        "update `team` set `team_name`=? where `id`=?";

	private final String DELETE_TEAM = "delete from `team` where `id`=?";

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		this.template = this.getSimpleJdbcTemplate();
	}

	/**
	 * @return list of all teams in the table team
	 * @throws DataAccessException
	 */
	public Collection<Team> getAll() throws DataAccessException {
		return this.template.query(this.GET_ALL,
		        ParameterizedBeanPropertyRowMapper.newInstance(Team.class));
	}

	/**
	 * @param id -
	 *            id of the team in the table
	 * @return One team, selected by inserted id
	 * @throws DataAccessException
	 */
	public Team getTeam(int id) throws DataAccessException {
		return this.template.queryForObject(this.GET_TEAM_BY_ID,
		        ParameterizedBeanPropertyRowMapper.newInstance(Team.class), id);
	}

	/**
	 * @param teamName -
	 *            name of the team in the table
	 * @return One team, selected by inserted team name
	 * @throws DataAccessException
	 */
	public Collection<Team> getTeam(String teamName) throws DataAccessException {

		return this.template.query(this.GET_TEAM_BY_NAME,
		        ParameterizedBeanPropertyRowMapper.newInstance(Team.class),
		        teamName);
	}

	/**
	 * @param teamName -
	 *            entity of class team
	 * @throws DataAccessException
	 */
	public void createTeam(Team t) throws DataAccessException {
		this.template.update(this.CREATE_TEAM, t.getTeamName(),
		        t.getAccountId());
	}

	/**
	 * @param id -
	 *            id of the team in the table
	 * @param newTeasName -
	 *            new name of the team
	 * @throws DataAccessException
	 */
	public void updateTeamName(int id, String newStageName)
	    throws DataAccessException {

		this.template.update(this.UPDATE_TEAM_NAME, newStageName, id);
	}

	/**
	 * @param id -
	 *            id of the team in the table
	 * @throws DataAccessException
	 */
	public void deleteTeam(int id) throws DataAccessException {
		this.template.update(this.DELETE_TEAM, id);
	}
}
