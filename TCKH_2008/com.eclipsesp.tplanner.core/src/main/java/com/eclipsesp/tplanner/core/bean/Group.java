/*
 * 17.07.2008 18:12:33
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
 * Business bean for tournament group
 * </p>
 * implements {@link java.io.Serializable} interface
 * 
 * @see java.io.Serializable
 * @author vpostrigan
 */
public class Group
        extends Bean
        implements Serializable {

	private static final long serialVersionUID = -7115604380414191047L;

	/**
	 * <p>
	 * Group name of tournament
	 * </p>
	 */
	private String groupName;

	public Group() {
		// Do nothing
	}

	public Group(long id, String groupName) {
		super(id);
		this.groupName = groupName;
	}

	/** Returns bean's groupName */
	public String getGroupName() {
		return this.groupName;
	}

	/** Sets bean's groupName */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
