/*
 * 24 Лип 2008 18:44:02
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 856 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 16:40:38 +0300 (Ср, 13 авг 2008) $
 */

package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Determines the set of methods, to manage  the games of commands and users
 * 
 * @author dproshkin
 */
public interface IGameManager {

	Collection<GameTeam> getAllTeam() throws DataAccessException;

	Collection<GameUser> getAllUser() throws DataAccessException;
	
	Collection<GameTeam> getTeam(String nameField, String valueToSearch) throws DataAccessException;
	
	Collection<GameUser> getUser(String nameField, String valueToSearch) throws DataAccessException;
	
	Collection<GameTeam> getTeamPair(String teamParticipant1, String teamParticipant2) throws DataAccessException;
	
	Collection<GameUser> getUserPair(String userParticipant1, String userParticipant2) throws DataAccessException;

	void insertToUpdateGameTeam(GameTeam gameTeam) throws DataAccessException;
	
	void insertToUpdateGameUser(GameUser gameUser) throws DataAccessException;

	void deleteGameTeam(GameTeam gameTeam) throws DataAccessException;
	
	void deleteGameUser(GameUser gameUser) throws DataAccessException;
}
