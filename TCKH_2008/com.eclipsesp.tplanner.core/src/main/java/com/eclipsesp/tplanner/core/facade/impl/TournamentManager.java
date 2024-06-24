/*
 * 24 Лип 2008 10:34:00
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 874 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-13 18:16:20 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**
 * An implementation of {@link ITournamentManager} facade. In particular, it
 * deals with JDBC DAO support classes
 */

public class TournamentManager
        implements ITournamentManager {

	@Autowired
	protected TournamentJdbcDaoSupport daoSupport;

	public void create(Tournament tournament) throws DataAccessException {
		this.daoSupport.create(tournament);
	}

	public void delete(Long id) throws DataAccessException {
		this.daoSupport.delete(id);
	}

	public Collection<Tournament> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public Collection<Tournament> getByGameKind(String gameKind)
	    throws DataAccessException {
		return this.daoSupport.getByGameKind(gameKind);
	}

	public Tournament getById(Long id) throws DataAccessException {
		return this.daoSupport.getById(id);
	}

	public Collection<Tournament> getByName(String name)
	    throws DataAccessException {
		return this.daoSupport.getByName(name);
	}

	public Collection<Tournament> getByOwnerId(Long id)
	    throws DataAccessException {
		return this.daoSupport.getByOwnerId(id);
	}

	public Collection<Tournament> getByStatusId(Long id)
	    throws DataAccessException {
		return this.daoSupport.getByStatusId(id);
	}

	public Collection<Tournament> getByVisibility(String visibility)
	    throws DataAccessException {
		return this.daoSupport.getByVisibility(visibility);
	}

	public void update(Tournament tournament) throws DataAccessException {
		this.daoSupport.update(tournament);
	}

	public Collection<User2Tournament> getUser2Tournament(Long accountId,
	    Long statusId) throws DataAccessException {
		return this.daoSupport.getUser2Tournament(accountId, statusId);
	}

	public Collection<User2Tournament> getUser2TournamentAll()
	    throws DataAccessException {
		return this.daoSupport.getUser2TournamentAll();
	}

	public Collection<User2Tournament> getUser2TournamentAccount(long accountId)
	    throws DataAccessException {
		return this.daoSupport.getUser2TournamentAccount(accountId);
	}

	public void createUser2Tournament(Long accountId, int tournamentId,
	    int statusId) throws DataAccessException {
		this.daoSupport.createUser2Tournament(accountId, tournamentId, statusId);
	}

	public void deleteUser2Tournament(Long accountId, int tournamentId)
	    throws DataAccessException {
		this.daoSupport.deleteUser2Tournament(accountId, tournamentId);
	}

	public TournamentJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(TournamentJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	public void start(Long id) {
		Tournament tournament = getById(id);
		tournament.setStatusId(new Long(2));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		tournament.setStartDate(calendar.getTime());
		update(tournament);
	}

	public void finish(Long id) {
		Tournament tournament = getById(id);
		tournament.setStatusId(new Long(3));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		tournament.setFinishDate(calendar.getTime());
		update(tournament);
	}

	public boolean checkUser2Tournament(Long tournament_id, Long account_id)
	    throws DataAccessException {
		return this.daoSupport.checkUser2Tournament(tournament_id, account_id);
	}

	public Collection<Invite> getByAccountId(Long accountId) {
		return this.daoSupport.getByAccountId(accountId);
	}

	public void updateUser2Tournament(Long accountId, int tournamentId,
	    int value) {
		this.daoSupport.UpdateUser2Tournament(accountId, tournamentId, value);
	}

	public Collection<Tournament> getAll(String sortField)
	    throws DataAccessException {
		return this.daoSupport.getAll(sortField);
	}

	public Collection<Tournament> getByOwnerId(Long ownerId, String orderBy) {
		return this.daoSupport.getByOwnerId(ownerId, orderBy);
	}

	public User2Tournament getUser2TournamentFull(Long accountId,
	    Long tournamentId, Long statusId) {
		return this.daoSupport.getUser2TournamentFull(accountId, tournamentId,
		        statusId);
	}
}
