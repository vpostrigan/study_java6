/*
 * 11.08.2008 15:49:35
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 36 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-17 17:35:40 +0300 (Чт, 17 июл 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;

/**
 * This class represent table "User2Tournament" from database.
 * 
 * Class defines all fields from table "User2Tournament",implement {@link Serializable} 
 * interface.
 * 
 * @author dproshkin
 */
public class User2Tournament implements Serializable {

    private static final long serialVersionUID = -1282853804817371069L;

    /** Tournament identifier */
	private long tournament_Id;
	
	/** Tournament status identifier */
	private long status_Id;

	/** Tournament account id */
	private long account_Id;

	public long getTournamentId() {
    	return tournament_Id;
    }

	public void setTournamentId(long tournamentId) {
    	this.tournament_Id = tournamentId;
    }

	public long getStatusId() {
    	return status_Id;
    }

	public void setStatusId(long statusId) {
    	this.status_Id = statusId;
    }

	public long getAccountId() {
    	return account_Id;
    }

	public void setAccountId(long accountId) {
    	this.account_Id = accountId;
    }
}
