/*
 * 17.07.2008 17:46:01
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 212 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-25 16:43:23 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;

/**
 * <p>
 * Business bean for tournament team
 * </p>
 * implements {@link Serializable} interface
 * 
 * @author vpostrigan
 */
public class Team
        extends Bean {

	private static final long serialVersionUID = -6576266495110074313L;

	/**
	 * <p>
	 * Stage name of tournament
	 * </p>
	 */
	private String teamName;

	/**
	 * <p>
	 * Stage creation date
	 * </p>
	 */
	private long accountId;

	public Team() {
		// Do nothing
	}

	public Team(long id, long accountId, String teamName) {
		super(id);
		this.teamName = teamName;
		this.accountId = accountId;
	}

	/** Returns bean's teamName */
	public String getTeamName() {
		return this.teamName;
	}

	/** Sets bean's teamName */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
