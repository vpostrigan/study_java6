/*
 * 25.07.2008 11:06:47
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 251 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-28 17:34:41 +0300 (Пн, 28 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

/**
 * Test stage manager functionality
 * 
 * @author vpostrigan
 */
public class StageManagerTest
        extends BaseDatabaseTests {

	/** Object being tested */
	private StageManager stage;

	/** Stage id from table group ('1', 'Quarterfinal') */
	private final int TEST_STAGE_ID = 1;

	/** Stage name from table group ('1', 'Quarterfinal') */
	private final String TEST_STAGE_NAME = "Quarterfinal";

	/** New stage name for test */
	private final String NEW_TEST_STAGE_NAME = "!q@a#z$w%^&s*(x";

	@Before
	public void setUp() throws Exception {
		StageJdbcDaoSupport daoSupport = this.setUp(StageJdbcDaoSupport.class);
		this.stage = new StageManager();
		this.stage.setDaoSupport(daoSupport);
	}

	@Test
	public void getAll() {
		Collection<Stage> stage = this.stage.getAll();
		Assert.assertNotNull(stage);
	}

	@Test
	public void getStageById() {
		Stage stage = this.stage.getStage(this.TEST_STAGE_ID);

		Assert.assertNotNull(stage);
		Assert.assertEquals(this.TEST_STAGE_ID, stage.getId());
	}

	@Test
	public void getStageByName() {
		this.getStages(this.TEST_STAGE_NAME);
	}

	@Test
	public void createStage() {
		Stage s = new Stage(0L, 1L, this.NEW_TEST_STAGE_NAME);

		this.stage.createStage(s);
		this.getStages(this.NEW_TEST_STAGE_NAME);
	}

	@Test
	public void updateStageName() {
		this.stage.updateStageName(this.TEST_STAGE_ID, this.NEW_TEST_STAGE_NAME);
		this.getStages(this.NEW_TEST_STAGE_NAME);
	}

	@Test
	public void deleteStage() {
		this.stage.deleteStage(this.TEST_STAGE_ID);

		Collection<Stage> stage = this.stage.getAll();
		Assert.assertTrue(stage.isEmpty());
	}

	/** Get list of stages by specified stageName */
	public void getStages(String stageName) {
		Collection<Stage> stage = this.stage.getStage(stageName);

		Assert.assertNotNull(stage);
		Assert.assertTrue(stage.size() >= 1 ? true : false);
	}
}
