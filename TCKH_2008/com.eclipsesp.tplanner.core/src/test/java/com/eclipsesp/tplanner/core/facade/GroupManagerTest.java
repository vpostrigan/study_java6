/*
 * 24.07.2008 15:41:21
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
 * Test group manager functionality
 * 
 * @author vpostrigan
 */
public class GroupManagerTest
        extends BaseDatabaseTests {

	/** Object being tested */
	private GroupManager group;

	/** Group id from table group ('1', 'Pirates') */
	private final int TEST_GROUP_ID = 1;

	/** Group name from table group ('1', 'Pirates') */
	private final String TEST_GROUP_NAME = "Pirates";

	/** New group name for test */
	private final String NEW_TEST_GROUP_NAME = "!q@a#z$w%^&s*(x";

	@Before
	public void setUp() throws Exception {
		GroupJdbcDaoSupport daoSupport = this.setUp(GroupJdbcDaoSupport.class);
		this.group = new GroupManager();
		this.group.setDaoSupport(daoSupport);
	}

	@Test
	public void getAll() {
		Collection<Group> groups = this.group.getAll();

		Assert.assertNotNull(groups);
	}

	@Test
	public void getGroupById() {
		Group group = this.group.getGroup(this.TEST_GROUP_ID);

		Assert.assertNotNull(group);
		Assert.assertEquals(this.TEST_GROUP_ID, group.getId());
	}

	@Test
	public void getGroupByName() {
		this.getGroups(this.TEST_GROUP_NAME);
	}

	@Test
	public void createGroup() {
		this.group.createGroup(this.NEW_TEST_GROUP_NAME);
		this.getGroups(this.NEW_TEST_GROUP_NAME);
	}

	@Test
	public void updateGroupName() {
		this.group.updateGroupName(this.TEST_GROUP_ID, this.NEW_TEST_GROUP_NAME);
		this.getGroups(this.NEW_TEST_GROUP_NAME);
	}

	@Test
	public void deleteGroup() {
		this.group.deleteGroup(this.TEST_GROUP_ID);

		Collection<Group> groups = this.group.getAll();
		Assert.assertTrue(groups.isEmpty());
	}

	/** Get list of groups by specified groupName */
	public void getGroups(String groupName) {
		Collection<Group> group = this.group.getGroup(groupName);

		Assert.assertNotNull(group);
		Assert.assertTrue(group.size() >= 1 ? true : false);
	}
}
