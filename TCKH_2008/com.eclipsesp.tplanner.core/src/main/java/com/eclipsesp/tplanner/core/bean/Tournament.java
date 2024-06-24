/*
 * 17 Лип 2008 18:12:30
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 438 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-04 11:56:58 +0300 (Пн, 04 авг 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;

import java.util.*;


/**
 * This class represent table "Tournament" from database.
 * 
 * Class defines all fields from table "Tournament", extends {@link Bean} class
 * and implement {@link Serializable} interface.
 * 
 * @author rshportko
 */
public class Tournament
        extends Bean {

	
	
	private static final long serialVersionUID = -3812954050396734135L;

	/** Tournament status identifier */
	private long statusId;

	/** Tournament start date */
	private Date startDate;

	/** Tournament finish date */
	private Date finishDate;

	/** Tournament game kind */
	private String gameKind;

	/** Tournament owner id */
	private long accountId;

	/** Tournament name */
	private String tournamentName;

	/** Tournament visibility */
	private String visibility;
	
	/**Tournament description*/
	private String description;

	/** Public default constructor */
	public Tournament() {
		// Do nothing here
	}
	
	/** Initializes all fields */
	public Tournament(long id, long statusId, Date startDate, Date finishDate,
	        String gameKind, long accountId, String tournamentName,
	        String visibility, String description) {
		this.id = id;
		this.statusId = statusId;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.gameKind = gameKind;
		this.accountId = accountId;
		this.tournamentName = tournamentName;
		this.visibility = visibility;
		this.description = description;
	}

	
	
	/** Returns tournament's status identifier */
	public long getStatusId() {
		return this.statusId;
	}

	/** Sets tournament's status identifier */
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	/** Returns tournament's start date */
	public Date getStartDate() {
		return this.startDate;
	}

	/** Sets tournament's start date */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** Returns tournament's finish date */
	public Date getFinishDate() {
		return this.finishDate;
	}

	/** Sets tournament's finish date */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	/** Returns tournament's game kind */
	public String getGameKind() {
		return this.gameKind;
	}

	/** Sets tournament's game kind */
	public void setGameKind(String gameKind) {
		this.gameKind = gameKind;
	}

	/** Returns tournament's owner id */
	public long getAccountId() {
		return this.accountId;
	}

	/** Sets tournament's owner id */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/** Returns tournament's name */
	public String getTournamentName() {
		return this.tournamentName;
	}

	/** Sets tournament's name */
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	/** Returns tournament's visibility */
	public String getVisibility() {
		return this.visibility;
	}

	/** Sets tournament's visibility */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	/** Returns tournament's description */
	public String getDescription() {
		return this.description;
	}

	/** Sets tournament's description */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
