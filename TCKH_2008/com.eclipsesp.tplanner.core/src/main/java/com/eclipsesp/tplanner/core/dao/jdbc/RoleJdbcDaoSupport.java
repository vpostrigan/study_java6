/*
 * 25.07.2008 10:59:19
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 425 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-01 19:08:38 +0300 (Пт, 01 авг 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

public class RoleJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		// this.setPassword = new SetPassword(getDataSource());
	}

	public Collection<Role> getAll() {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		return template.query("select * from role",
		        ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
	}

	public void createPermissionDefinition(String roleName, int permission)
	    throws DataAccessException {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		template.update("insert into `role`(`role_name`,"
		        + "`permission_def`) values(?, ?)", roleName, permission);
	}

	public Role getByRoleName(String roleName) throws DataAccessException {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		return template.queryForObject("select *"
		        + "  from `role` where `role_name`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Role.class),
		        roleName);
	}

	public Role getByPermissionDefinition(int permission)
	    throws DataAccessException {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		return template.queryForObject("select `role_name`, `permission_def`"
		        + "  from `role` where `permission_def`=?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Role.class),
		        permission);
	}

	public void changeRole(String roleName, String newRoleName, int permission)
	    throws DataAccessException {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		template.update("update `role` set `role_name`=?, `permission_def`=? "
		        + " where `role_name`=?", newRoleName, permission, roleName);
	}

	public void deleteRole(String roleName) throws DataAccessException {
		SimpleJdbcTemplate template = getSimpleJdbcTemplate();
		template.update("delete from `role` where `role_name`=?", roleName);
	}

}
