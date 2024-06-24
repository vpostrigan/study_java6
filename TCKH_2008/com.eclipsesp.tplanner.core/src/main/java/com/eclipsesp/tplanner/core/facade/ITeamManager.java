/*
 * 25.07.2008 15:09:24
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 284 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:47:06 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * <p>
 * Defines general contract to manage teams.
 * </p>
 * 
 * @author vpostrigan
 */
public interface ITeamManager {
	Collection<Team> getAll() throws DataAccessException;

	Team getTeam(int id) throws DataAccessException;

	Collection<Team> getTeam(String teamName) throws DataAccessException;

	void createTeam(Team t) throws DataAccessException;

	void updateTeamName(int id, String newTeamName) throws DataAccessException;

	void deleteTeam(int id) throws DataAccessException;
}
