/*
 * 17 ��� 2008 19:05:39
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 100 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-18 18:35:41 +0300 (Пт, 18 июл 2008) $
 */
package com.eclipsesp.tplanner.core.security.utils;

/**
 * Enumeration of possible permissions.
 * 
 * @author akrumin
 */
public enum SinglePermission implements IMask {
	CAN_NOTHING(0),
	CAN_READ_SELF(1),
	CAN_WRITE_SELF(3),
	CAN_READ_ALL(5),
	CAN_READ_ALL_WRITE_SELF(7),
	CAN_WRITE_ALL(15);
	SinglePermission(int id)
	{
		this.id = id;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}