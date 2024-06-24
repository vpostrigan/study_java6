/*
 * 24 Лип 2008 18:43:43
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 856 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 16:40:38 +0300 (Ср, 13 авг 2008) $
 */

package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**
 * An implementation of {@link IGameManager} facade. In particular, it
 * deals with JDBC DAO support classes.Implementation of methods for 
 * two entyti(@link Gameteam, @link Gameuser)
 */
public class GameManager
        implements IGameManager {
	
	@Autowired
	protected GameTeamJdbcDaoSupport teamDaoSupport;
	@Autowired
	protected GameUserJdbcDaoSupport userDaoSupport;

	public GameTeamJdbcDaoSupport getTeamDaoSupport() {
		return this.teamDaoSupport;
	}

	public void setTeamDaoSupport(GameTeamJdbcDaoSupport teamDaoSupport) {
		this.teamDaoSupport = teamDaoSupport;
	}

	public GameUserJdbcDaoSupport getUserDaoSupport() {
		return this.userDaoSupport;
	}

	public void setUserDaoSupport(GameUserJdbcDaoSupport userDaoSupport) {
		this.userDaoSupport = userDaoSupport;
	}

	@Override
	public void deleteGameTeam(GameTeam gameTeam)  throws DataAccessException {
		this.teamDaoSupport.deleteGame(gameTeam);
	}
	
	@Override
    public void deleteGameUser(GameUser gameUser) throws DataAccessException {
		this.userDaoSupport.deleteGame(gameUser);
    }

	@Override
	public Collection<GameTeam> getAllTeam() throws DataAccessException {
		return this.teamDaoSupport.getAll();
	}

	@Override
	public Collection<GameUser> getAllUser() throws DataAccessException {
		return this.userDaoSupport.getAll();
	}

	@Override
    public Collection<GameTeam> getTeam(String nameField, String valueToSearch)
        throws DataAccessException {
	    return this.teamDaoSupport.getOneField(nameField, valueToSearch);
    }

	@Override
    public Collection<GameUser> getUser(String nameField, String valueToSearch)
        throws DataAccessException {
	    return this.userDaoSupport.getOneField(nameField, valueToSearch);
    }
	
	@Override
    public Collection<GameTeam> getTeamPair(String teamParticipant1, String teamParticipant2) 
    	throws DataAccessException {
	    return this.teamDaoSupport.getPair(teamParticipant1, teamParticipant2);
    }

	@Override
    public Collection<GameUser> getUserPair(String userParticipant1, String userParticipant2) 
        throws DataAccessException {
	    return this.userDaoSupport.getPair(userParticipant1, userParticipant2);
    }

	@Override
    public void insertToUpdateGameTeam(GameTeam gameTeam)
        throws DataAccessException {
		this.teamDaoSupport.insertToUpdateGame(gameTeam);
    }

	@Override
    public void insertToUpdateGameUser(GameUser gameUser)
        throws DataAccessException {
		this.userDaoSupport.insertToUpdateGame(gameUser);
    }

}
