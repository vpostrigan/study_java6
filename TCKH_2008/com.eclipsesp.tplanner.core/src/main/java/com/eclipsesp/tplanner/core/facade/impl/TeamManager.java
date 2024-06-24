/*
 * 25.07.2008 15:13:31
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 286 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:47:20 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**
 * <p>
 * An implementation of {@link ITeamManager} facade. In particular, it deals
 * with JDBC DAO support classes
 * </p>
 * 
 * @author vpostrigan
 */
public class TeamManager
        implements ITeamManager {

	@Autowired
	protected TeamJdbcDaoSupport daoSupport;

	public Collection<Team> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public Team getTeam(int id) throws DataAccessException {
		return this.daoSupport.getTeam(id);
	}

	public Collection<Team> getTeam(String teamName) throws DataAccessException {
		return this.daoSupport.getTeam(teamName);
	}

	public void createTeam(Team t) throws DataAccessException {
		this.daoSupport.createTeam(t);
	}

	public void updateTeamName(int id, String newTeamName)
	    throws DataAccessException {

		this.daoSupport.updateTeamName(id, newTeamName);
	}

	public void deleteTeam(int id) throws DataAccessException {
		this.daoSupport.deleteTeam(id);
	}

	public TeamJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(TeamJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
