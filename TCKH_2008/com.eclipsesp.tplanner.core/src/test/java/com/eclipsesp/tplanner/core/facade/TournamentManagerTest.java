/*
 * 24 Лип 2008 15:51:20
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 455 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-04 13:59:48 +0300 (Пн, 04 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

/**
 * Test tournament manager functionality
 * 
 */

public class TournamentManagerTest
        extends BaseDatabaseTests {

	private static final String TOURNAMENT_NAME = "test";

	/** Object being used */
	TournamentManager tournamentManager;

	@Before
	public void setUp() throws Exception {
		TournamentJdbcDaoSupport daoSupport =
		        this.setUp(TournamentJdbcDaoSupport.class);
		this.tournamentManager = new TournamentManager();
		this.tournamentManager.setDaoSupport(daoSupport);
	}

	@Test
	public void testGetById() {
		try {
			Tournament tournament = this.tournamentManager.getById(new Long(1));
			Assert.assertEquals(tournament.getId(), 1);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetAll() {
		try {

			Collection<Tournament> tournaments =
			        this.tournamentManager.getAll();
			Assert.assertFalse(tournaments.isEmpty());
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetByName() {
		try {

			Collection<Tournament> tournaments =
			        this.tournamentManager.getByName(TournamentManagerTest.TOURNAMENT_NAME);

			Set<String> names = new HashSet<String>();
			for (Tournament tour : tournaments) {
				names.add(tour.getTournamentName());
			}
			Assert.assertEquals(new HashSet<String>(
			        Arrays.asList(TournamentManagerTest.TOURNAMENT_NAME)),
			        names);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetByStatusId() {
		try {

			Collection<Tournament> tournaments =
			        this.tournamentManager.getByStatusId(new Long(1));

			Set<Long> statusInentifiers = new HashSet<Long>();
			for (Tournament tour : tournaments) {
				statusInentifiers.add(tour.getStatusId());
			}
			Assert.assertEquals(new HashSet<Long>(Arrays.asList(new Long(1))),
			        statusInentifiers);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetByVisibility() {
		try {

			Collection<Tournament> tournaments =
			        this.tournamentManager.getByVisibility("public");

			Set<String> visibilities = new HashSet<String>();
			for (Tournament tour : tournaments) {
				visibilities.add(tour.getVisibility());
			}
			Assert.assertEquals(new HashSet<String>(Arrays.asList("public")),
			        visibilities);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() {
		try {
			Date finishDate = new Date(2008, 7, 25);
			Tournament tournament = this.tournamentManager.getById(new Long(1));
			tournament.setFinishDate(finishDate);
			this.tournamentManager.update(tournament);
			Assert.assertEquals(
			        this.tournamentManager.getById(new Long(1)).getFinishDate().getDate(),
			        25);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	/**Method was changed but it works correctly*/
	@Test
	public void testDelete() {
		try {
			this.tournamentManager.delete(new Long(1));			
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}
}
