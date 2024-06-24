/*
 * 24 Лип 2008 10:26:50
 *
 * (C) Eclipse SP LLC. All rights reserved
 *  
 * $Revision: 874 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-13 18:16:20 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Defines general contract to manage tournaments.
 */

public interface ITournamentManager {

	public Collection<Tournament> getAll() throws DataAccessException;

	public Tournament getById(Long id) throws DataAccessException;

	public Collection<Tournament> getByStatusId(Long id)
	    throws DataAccessException;

	public Collection<Tournament> getByGameKind(String gameKind)
	    throws DataAccessException;

	public Collection<Tournament> getByOwnerId(Long id)
	    throws DataAccessException;

	public Collection<Tournament> getByOwnerId(Long id, String orderBy)
	    throws DataAccessException;

	public Collection<Tournament> getByVisibility(String visibility)
	    throws DataAccessException;

	public Collection<Tournament> getByName(String name)
	    throws DataAccessException;

	public void update(Tournament tournament) throws DataAccessException;

	public void create(Tournament tournament) throws DataAccessException;

	public void delete(Long id) throws DataAccessException;

	public Collection<User2Tournament> getUser2Tournament(Long accountId,
	    Long statusId) throws DataAccessException;

	public Collection<User2Tournament> getUser2TournamentAll()
	    throws DataAccessException;

	public Collection<User2Tournament> getUser2TournamentAccount(long accountId)
	    throws DataAccessException;

	public void createUser2Tournament(Long accountId, int tournamentId,
	    int statusId) throws DataAccessException;

	public void deleteUser2Tournament(Long accountId, int tournamentId)
	    throws DataAccessException;

	public Collection<Invite> getByAccountId(Long accountId);

	public void updateUser2Tournament(Long accountId, int tournamentId,
	    int value);

	public Collection<Tournament> getAll(String sortField)
	    throws DataAccessException;

	public User2Tournament getUser2TournamentFull(Long accountId,
	    Long tournamentId, Long statusId);
}
