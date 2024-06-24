/*
 * 29 Лип 2008 16:59:15
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.core.facade;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

public class StatusManagerTest
        extends BaseDatabaseTests {

	/** Object being used */
	StatusManager statusManager;

	@Before
	public void setUp() throws Exception {
		StatusJdbcDaoSupport daoSupport =
		        this.setUp(StatusJdbcDaoSupport.class);
		this.statusManager = new StatusManager();
		this.statusManager.setDaoSupport(daoSupport);
	}

	@Test
	public void testCreate() {
		try {
			Status status = new Status(new Long(15), "finished", "user");
			this.statusManager.create(status);
			Status statuses = this.statusManager.getById(new Long(15));
			Assert.assertNotNull(statuses);
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetById() {
		try {
			Status status = this.statusManager.getById(new Long(1));
			Assert.assertEquals(status.getStatusName(), "pending");
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testDelete() {
		try {
			this.statusManager.delete(new Long(1));
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

}
