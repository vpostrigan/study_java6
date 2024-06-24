/*
 * 23.07.2008 16:09:43
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 163 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-24 18:42:01 +0300 (Чт, 24 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * <p>
 * JDBC DAO support for managing systems groups
 * </p>
 * 
 * @author vpostrigan
 */
public class GroupJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	private SimpleJdbcTemplate template;

	private final String GET_ALL = "select `id`, `group_name` from `group`";

	private final String GET_GROUP_BY_ID =
	        "select `id`, `group_name` from `group` where `id`=?";

	private final String GET_GROUP_BY_NAME =
	        "select `id`, `group_name` from `group` where `group_name`=?";

	private final String CREATE_GROUP =
	        "insert into `group`(`group_name`) values(?)";

	private final String UPDATE_GROUP_NAME =
	        "update `group` set `group_name`=? where id=?";

	private final String DELETE_GROUP = "delete from `group` where `id`=?";

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		this.template = this.getSimpleJdbcTemplate();
	}

	/**
	 * @return list of all groups in the table group
	 * @throws DataAccessException
	 */
	public Collection<Group> getAll() throws DataAccessException {
		return this.template.query(this.GET_ALL,
		        ParameterizedBeanPropertyRowMapper.newInstance(Group.class));
	}

	/**
	 * @param id -
	 *            id of the group in the table
	 * @return One group, selected by inserted id
	 * @throws DataAccessException
	 */
	public Group getGroup(int id) throws DataAccessException {
		return this.template.queryForObject(this.GET_GROUP_BY_ID,
		        ParameterizedBeanPropertyRowMapper.newInstance(Group.class), id);
	}

	/**
	 * @param groupName -
	 *            name of the group in the table
	 * @return One group, selected by inserted group name
	 * @throws DataAccessException
	 */
	public Collection<Group> getGroup(String groupName)
	    throws DataAccessException {
		return this.template.query(this.GET_GROUP_BY_NAME,
		        ParameterizedBeanPropertyRowMapper.newInstance(Group.class),
		        groupName);
	}

	/**
	 * @param groupName -
	 *            entity of class group
	 * @throws DataAccessException
	 */
	public void createGroup(String groupName) throws DataAccessException {
		this.template.update(this.CREATE_GROUP, groupName);
	}

	/**
	 * @param id -
	 *            id of the group in the table
	 * @param newGroupName -
	 *            new name of the group
	 * @throws DataAccessException
	 */
	public void updateGroupName(int id, String newGroupName)
	    throws DataAccessException {

		this.template.update(this.UPDATE_GROUP_NAME, newGroupName, id);
	}

	/**
	 * @param id -
	 *            id of the group in the table
	 * @throws DataAccessException
	 */
	public void deleteGroup(int id) throws DataAccessException {
		this.template.update(this.DELETE_GROUP, id);
	}
}
