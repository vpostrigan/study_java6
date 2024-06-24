/*
 * 24.07.2008 10:34:37
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 202 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-25 14:52:33 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * <p>
 * JDBC DAO support for managing systems stages
 * </p>
 * 
 * @author vpostrigan
 */
public class StageJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	private SimpleJdbcTemplate template;

	private final String GET_ALL =
	        "select `id`, `stage_name`, `tournament_id`, `creation_date` from `stage`";

	private final String GET_STAGE_BY_ID =
	        "select `id`, `stage_name`, `tournament_id`, `creation_date` from `stage` where `id`=?";

	private final String GET_STAGE_BY_NAME =
	        "select `id`, `stage_name`, `tournament_id`, `creation_date` from `stage` where `stage_name`=?";

	private final String CREATE_STAGE =
	        "insert into `stage`(`stage_name`, `tournament_id`, `creation_date`) values(?, ?, ?)";

	private final String UPDATE_STAGE_NAME =
	        "update `stage` set `stage_name`=? where `id`=?";

	private final String DELETE_STAGE = "delete from `stage` where `id`=?";

	@Override
	protected void initDao() throws Exception {
		// initialize DAO queries if any
		this.template = this.getSimpleJdbcTemplate();
	}

	/**
	 * @return list of all stages in the table stage
	 * @throws DataAccessException
	 */
	public Collection<Stage> getAll() throws DataAccessException {
		return this.template.query(this.GET_ALL,
		        ParameterizedBeanPropertyRowMapper.newInstance(Stage.class));
	}

	/**
	 * @param id -
	 *            id of the stage in the table
	 * @return One stage, selected by inserted id
	 * @throws DataAccessException
	 */
	public Stage getStage(int id) throws DataAccessException {
		return this.template.queryForObject(this.GET_STAGE_BY_ID,
		        ParameterizedBeanPropertyRowMapper.newInstance(Stage.class), id);
	}

	/**
	 * @param stageName -
	 *            name of the stage in the table
	 * @return One stage, selected by inserted stage name
	 * @throws DataAccessException
	 */
	public Collection<Stage> getStage(String stageName)
	    throws DataAccessException {
		return this.template.query(this.GET_STAGE_BY_NAME,
		        ParameterizedBeanPropertyRowMapper.newInstance(Stage.class),
		        stageName);
	}

	/**
	 * @param stageName -
	 *            entity of class stage
	 * @throws DataAccessException
	 */
	public void createStage(Stage s) throws DataAccessException {

		this.template.update(this.CREATE_STAGE, s.getStageName(),
		        s.getTournamentId(), s.getCreationDate());
	}

	/**
	 * @param id -
	 *            id of the stage in the table
	 * @param newStageName -
	 *            new name of the stage
	 * @throws DataAccessException
	 */
	public void updateStageName(int id, String newStageName)
	    throws DataAccessException {

		this.template.update(this.UPDATE_STAGE_NAME, newStageName, id);
	}

	/**
	 * @param id -
	 *            id of the stage in the table
	 * @throws DataAccessException
	 */
	public void deleteStage(int id) throws DataAccessException {
		this.template.update(this.DELETE_STAGE, id);
	}
}
