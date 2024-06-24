/*
 * 17.07.2008 17:04:03
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 231 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-25 18:36:00 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;
import java.util.*;

/**
 * <p>
 * Business bean for stage of tournament
 * </p>
 * implements {@link Serializable} interface
 * 
 * @author vpostrigan
 */
public class Stage
        extends Bean {

	private static final long serialVersionUID = 4710601973436581122L;

	/**
	 * <p>
	 * Initializes bean's statusId
	 * </p>
	 */
	private long tournamentId;

	/**
	 * <p>
	 * Stage name of tournament stage
	 * </p>
	 */
	private String stageName;

	/**
	 * <p>
	 * Stage creation date
	 * </p>
	 */
	private Date creationDate;

	public Stage() {
		// Do nothing
	}

	/** Date created automatically */
	public Stage(long id, long tournamentId, String stageName) {
		super(id);
		this.tournamentId = tournamentId;
		this.stageName = stageName;
		this.creationDate = new Date();
	}

	/** Returns tournament id */
	public long getTournamentId() {
		return this.tournamentId;
	}

	/** Sets bean's tournamentId */
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}

	/** Returns bean's stageName */
	public String getStageName() {
		return this.stageName;
	}

	/** Sets bean's stageName */
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	/** Returns bean's creationDate */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/** Sets bean's creationDate */
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
}
