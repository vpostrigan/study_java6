/**
 * 17.07.2008 16:30:27
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 34 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-17 17:33:44 +0300 (Чт, 17 июл 2008) $
 */

package com.eclipsesp.tplanner.core.bean;

import java.io.*;

/**
 * The base class for all business beans in the project.
 * 
 * In fact, defines only {@link #id} field and implements {@link Serializable}
 * interface.
 * 
 * @author Pavel Marinchev
 */
public abstract class Bean
        implements Serializable {

	/** Bean's identifier */
	protected long id = -1;

	/** Public default constructor */
	public Bean() {
		// Do nothing
	}

	/** Initializes bean's identifier */
	public Bean(long id) {
		this.id = id;
	}

	/** Returns bean's identifier */
	public long getId() {
		return this.id;
	}

	/** Sets bean's identifier */
	public void setId(long id) {
		this.id = id;
	}
}
