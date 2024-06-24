/*
 * 23 ��� 2008 18:13:11
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 221 $
 * $Author: pmarinchev_tckh $
 * $Date: 2008-07-25 17:20:36 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.utils;

import java.lang.reflect.*;
import java.util.*;

import org.apache.commons.logging.*;

/**
 * Some utilities for work with data
 * 
 * @author akrumin
 */
public class DataUtilities {

	protected final static Log logger = LogFactory.getLog(DataUtilities.class);

	/**
	 * 
	 * @param clazz -
	 *            class to convert
	 * @param o -
	 *            occurrence of class
	 * @return map that contains all fields values
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertClassToMap(Class clazz, Object o) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Method methods[] = clazz.getMethods();
			for (Method element : methods) {
				if (element.getName().matches("get.+")) {
					String s =
					        element.getName().substring(3,
					                element.getName().length());
					data.put(s.toLowerCase(),
					        element.invoke(o, (Object[]) null));
				}
			}
			data.remove("class");
		} catch (Exception e) {
			DataUtilities.logger.error(e);
		}
		return data;
	}
}
