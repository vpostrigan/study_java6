/*
 * 29 Лип 2008 16:42:47
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 307 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 20:39:24 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

public class StatusJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {
	
	SimpleJdbcTemplate template;

	@Override
	protected void initDao() throws Exception {
		this.template = this.getSimpleJdbcTemplate();
	}
	
	public Status getById(Long id)
		throws DataAccessException {
			return this.template.queryForObject(
			        "select * from `status` where `id` = ?",
			        ParameterizedBeanPropertyRowMapper.newInstance(Status.class),id);
	}
	
	public void create(Status status) throws DataAccessException {
		template.update(
		        "INSERT INTO `status` (id, status_name, type) VALUES (?, ?, ?)",
		        status.getId(), status.getStatusName(), status.getStatusType());
	}

	public void delete(Long id) throws DataAccessException {
		template.update(
		        "DELETE FROM `status` WHERE `id`=?", id);
	}

}
