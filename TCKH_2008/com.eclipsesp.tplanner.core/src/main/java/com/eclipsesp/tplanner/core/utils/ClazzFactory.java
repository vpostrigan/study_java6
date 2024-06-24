/*
 * 24.07.2008 18:01:36
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 170 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-24 21:12:18 +0300 (Чт, 24 июл 2008) $
 */
package com.eclipsesp.tplanner.core.utils;

/**
 * Defines basic contract for classes that produce other classes instances
 * 
 * @author Pavel Marinchev
 */
public interface ClazzFactory<T> {
	T make();
}
