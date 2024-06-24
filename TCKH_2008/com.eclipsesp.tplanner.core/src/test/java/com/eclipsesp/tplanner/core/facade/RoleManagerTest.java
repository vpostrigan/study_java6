/*
 * 25.07.2008 15:16:58
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 425 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-01 19:08:38 +0300 (Пт, 01 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

public class RoleManagerTest
        extends BaseDatabaseTests {

	private RoleManager manager;

	private final String USER_NAME = "users";

	private final String ROLE_NAME = "pluger";

	private final int PERMISSION = 255;

	private final int TEST_OF_GETBYPERMISSION = this.PERMISSION;

	private final String ROLE_NAME_FOR_CHANGE = "lamer";

	@Before
	public void setUp() throws Exception {
		RoleJdbcDaoSupport daoSupport = this.setUp(RoleJdbcDaoSupport.class);
		this.manager = new RoleManager();
		this.manager.setDaoSupport(daoSupport);
	}

	@Test
	public void getAll() {
		Collection<Role> role = this.manager.getAll();
		Assert.assertNotNull(role);
	}

	@Test
	public void getByRoleName() {
		Role role = this.manager.getByRoleName("administrators");
		this.logger.info(role.getPermission_Def()
		        + "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadsadasdsd");
		Assert.assertNotNull(role);
	}

	@Test
	public void createPermissionDefinition() {
		try {
			this.manager.createPermissionDefinition(this.ROLE_NAME,
			        this.PERMISSION);
			this.manager.getByPermissionDefinition(this.TEST_OF_GETBYPERMISSION);
			Assert.fail();
		} catch (Exception e) {
			this.logger.error(e);
		}
	}

	@Test
	public void getByPermissionDefinition() {
		Role role = this.manager.getByPermissionDefinition(this.PERMISSION);

		Assert.assertNotNull(role);

	}

	// @Test
	public void changeRole() {

		this.manager.changeRole(this.USER_NAME, this.ROLE_NAME_FOR_CHANGE, 22);
		Role role = this.manager.getByPermissionDefinition(22);
		Assert.assertNotNull(role);

	}

	@Test(expected = IncorrectResultSizeDataAccessException.class)
	public void deleteRole() throws Exception {

		this.manager.deleteRole(this.USER_NAME);
		Role role = this.manager.getByRoleName(this.USER_NAME);
		Assert.assertNull(role);
	}

}
