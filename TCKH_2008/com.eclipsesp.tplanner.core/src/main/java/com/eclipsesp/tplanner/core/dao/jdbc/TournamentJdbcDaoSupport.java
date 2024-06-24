/*
 * 23 Лип 2008 15:58:29
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 874 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-13 18:16:20 +0300 (Ср, 13 авг 2008) $
 */

/**JDBC DAO support for tournaments managing */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * @author rshprotko
 * @author vpostrigan
 */
public class TournamentJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	private static final String UPDATE_TOURNAMENT =
	        "UPDATE `tournament` SET `status_id` = ?,"
	                + " `start_date` = ?,`finish_date` = ?, "
	                + "`game_kind` = ?, `tournament_name` = ?,`visibility` = ?, `description` = ? WHERE `id` = ?";

	private static final String CREATE_TOURNAMENT =
	        "INSERT INTO `tournament` (id, status_id,"
	                + " start_date, account_id, finish_date,"
	                + " game_kind, tournament_name, visibility, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	SimpleJdbcTemplate template;

	@Override
	protected void initDao() throws Exception {
		this.template = getSimpleJdbcTemplate();
	}

	public Collection<Tournament> getAll() throws DataAccessException {
		return this.template.query(
		        "select * from `tournament`",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class));
	}

	public Collection<Tournament> getAll(String sortField)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` ORDER BY ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        sortField);
	}

	public Tournament getById(Long id) throws DataAccessException {
		return this.template.queryForObject(
		        "select * from `tournament` where `id`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        id);
	}

	public Collection<Tournament> getByStatusId(Long id)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `status_id`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        id);
	}

	public Collection<Tournament> getByGameKind(String gameKind)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `game_kind`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        gameKind);
	}

	public Collection<Tournament> getByOwnerId(Long id)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `account_id`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        id);
	}

	public Collection<Tournament> getByOwnerId(Long id, String orderBy)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `account_id`=? ORDER BY ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        id, orderBy);
	}

	public Collection<Tournament> getByVisibility(String visibility)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `visibility`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        visibility);
	}

	public Collection<Tournament> getByName(String name)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `tournament` where `tournament_name`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Tournament.class),
		        name);
	}

	public void update(Tournament tournament) throws DataAccessException {
		this.template.update(TournamentJdbcDaoSupport.UPDATE_TOURNAMENT,
		        tournament.getStatusId(), tournament.getStartDate(),
		        tournament.getFinishDate(), tournament.getGameKind(),
		        tournament.getTournamentName(), tournament.getVisibility(),
		        tournament.getDescription(), tournament.getId());
	}

	public void create(Tournament tournament) throws DataAccessException {
		this.template.update(TournamentJdbcDaoSupport.CREATE_TOURNAMENT, 0,
		        tournament.getStatusId(), tournament.getStartDate(),
		        tournament.getAccountId(), tournament.getFinishDate(),
		        tournament.getGameKind(), tournament.getTournamentName(),
		        tournament.getVisibility(), tournament.getDescription());
	}

	public void delete(Long id) throws DataAccessException {
		this.template.update("DELETE FROM `tournament` WHERE `id`=?", id);
	}

	public Collection<User2Tournament> getUser2Tournament(Long accountId,
	    Long statusId) throws DataAccessException {
		return this.template.query(
		        "SELECT * FROM `user2tournament` where account_id=? and status_id=? ",
		        ParameterizedBeanPropertyRowMapper.newInstance(User2Tournament.class),
		        accountId, statusId);
	}

	public Collection<User2Tournament> getUser2TournamentAll()
	    throws DataAccessException {
		return this.template.query(
		        "SELECT * FROM `user2tournament`",
		        ParameterizedBeanPropertyRowMapper.newInstance(User2Tournament.class));
	}

	public Collection<User2Tournament> getUser2TournamentAccount(Long accountId)
	    throws DataAccessException {
		return this.template.query(
		        "SELECT * FROM `user2tournament` where account_id=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(User2Tournament.class),
		        accountId);
	}

	public void createUser2Tournament(Long accountId, int tournamentId,
	    int statusId) throws DataAccessException {
		this.template.update(
		        "INSERT INTO `user2tournament` (account_id, tournament_id,"
		                + " status_id) VALUES (?, ?, ?)", accountId,
		        tournamentId, statusId);
	}

	public void deleteUser2Tournament(Long accountId, int tournamentId)
	    throws DataAccessException {
		this.template.update(
		        "delete from `user2tournament` where `account_id`=? and `tournament_id`=?",
		        accountId, tournamentId);
	}

	public void UpdateUser2Tournament(Long accountId, int tournamentId,
	    int value) throws DataAccessException {
		this.template.update(
		        "update `user2tournament` set `status_id`=? where `account_id`=? and `tournament_id`=?",
		        value, accountId, tournamentId);
	}

	public boolean checkUser2Tournament(Long tournament_id, Long account_id)
	    throws DataAccessException {
		try {
			Long result =
			        this.template.queryForLong(
			                "SELECT `account_id` FROM `user2tournament`"
			                        + " WHERE `tournament_id`=? AND `account_id`=?",
			                tournament_id, account_id);
			if (result != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public Collection<Invite> getByAccountId(Long accountId)
	    throws DataAccessException {
		return this.template.query(
		        "SELECT t.tournament_name as tournamentName, u.status_id as status, t.id as tournamentid,s.status_name as statusName from tournament t, user2tournament u,status s where t.id = u.tournament_id and s.id=u.status_id and u.account_id = ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Invite.class),
		        accountId);
	}

	public User2Tournament getUser2TournamentFull(Long accountId,
	    Long tournamentId, Long statusId) throws DataAccessException {
		try {
			return this.template.queryForObject(
			        "SELECT * FROM `user2tournament` where account_id=? and tournament_id = ? and status_id = ?",
			        ParameterizedBeanPropertyRowMapper.newInstance(User2Tournament.class),
			        accountId, tournamentId, statusId);
		} catch (Exception e) {
			return null;
		}
	}

}
