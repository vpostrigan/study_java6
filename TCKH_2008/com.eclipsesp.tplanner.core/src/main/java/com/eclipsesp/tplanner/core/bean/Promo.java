/*
 * 17.07.2008 18:24:53
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
 * Business bean for promo-code
 * </p>
 * implements {@link Serializable} interface
 * 
 * @author vpostrigan
 */
public class Promo
        implements Serializable {

	private static final long serialVersionUID = 8509463571680838169L;

	/**
	 * <p>
	 * Initializes bean's statusId
	 * </p>
	 */
	private long tournamentId;

	/**
	 * <p>
	 * Promo-code of tournament
	 * </p>
	 */
	private String promo;

	public Promo() {
		// Do nothing
	}

	public Promo(long tournamentId, String promo) {
		this.tournamentId = tournamentId;
		this.promo = promo;
	}

	/** Returns tournament id */
	public long getTournamentId() {
		return this.tournamentId;
	}

	/** Sets bean's tournamentId */
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}

	/** Returns bean's promo */
	public String getPromo() {
		return this.promo;
	}

	/** Sets bean's promo */
	public void setPromo(String promo) {
		this.promo = promo;
	}
}
