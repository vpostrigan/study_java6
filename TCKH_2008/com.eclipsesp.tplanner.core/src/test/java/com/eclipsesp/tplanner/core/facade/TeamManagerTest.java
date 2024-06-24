/*
 * 25.07.2008 15:54:44
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 285 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:47:16 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

/**
 * Test team manager functionality
 * 
 * @author vpostrigan
 */
public class TeamManagerTest
        extends BaseDatabaseTests {

	/** Object being tested */
	private TeamManager team;

	/** Team id from table team ('1', 'J-Team') */
	private final int TEST_TEAM_ID = 1;

	/** Team name from table team ('1', 'J-Team') */
	private final String TEST_TEAM_NAME = "J-Team";

	/** New team name for test */
	private final String NEW_TEST_TEAM_NAME = "!q@a#z$w%^&s*(x";

	@Before
	public void setUp() throws Exception {
		TeamJdbcDaoSupport daoSupport = this.setUp(TeamJdbcDaoSupport.class);
		this.team = new TeamManager();
		this.team.setDaoSupport(daoSupport);
	}

	@Test
	public void getAll() {
		Collection<Team> team = this.team.getAll();
		Assert.assertNotNull(team);
	}

	@Test
	public void getTeamById() {
		Team team = this.team.getTeam(this.TEST_TEAM_ID);

		Assert.assertNotNull(team);
		Assert.assertEquals(this.TEST_TEAM_ID, team.getId());
	}

	@Test
	public void getTeamByName() {
		this.getTeams(this.TEST_TEAM_NAME);
	}

	@Test
	public void createTeam() {
		Team s = new Team(0L, 1L, this.NEW_TEST_TEAM_NAME);

		this.team.createTeam(s);
		this.getTeams(this.NEW_TEST_TEAM_NAME);
	}

	@Test
	public void updateTeamName() {
		this.team.updateTeamName(this.TEST_TEAM_ID, this.NEW_TEST_TEAM_NAME);
		this.getTeams(this.NEW_TEST_TEAM_NAME);
	}

	@Test
	public void deleteTeam() {
		this.team.deleteTeam(this.TEST_TEAM_ID);

		Collection<Team> team = this.team.getAll();
		Assert.assertTrue(team.isEmpty());
	}

	/** Get list of teams by specified teamName */
	public void getTeams(String teamName) {
		Collection<Team> team = this.team.getTeam(teamName);

		Assert.assertNotNull(team);
		Assert.assertTrue(team.size() >= 1 ? true : false);
	}
}
