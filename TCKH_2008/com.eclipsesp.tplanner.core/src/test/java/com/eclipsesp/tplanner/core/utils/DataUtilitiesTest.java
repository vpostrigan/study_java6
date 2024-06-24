/*
 * 23 Лип 2008 18:19:22
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 274 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-29 13:32:04 +0300 (Р’С‚, 29 РёСЋР» 2008) $
 */
package com.eclipsesp.tplanner.core.utils;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.bean.*;

public class DataUtilitiesTest {
	@Test
	public void convertClassToMapTest() {
		Account acc =
		        new Account(1, "asd", "asd", "sdf", "dfg", "fgh", "121212",
		                "asdasdasd", "asdasd", "qweqwe", null);
		Map<String, Object> map =
		        DataUtilities.convertClassToMap(Account.class, acc);
		for (int a = 0; a < map.keySet().size(); a++) {
			System.out.print(map.get(map.keySet().toArray()[a].toString())
			        + "  " + map.keySet().toArray()[a].toString() + "\n");
		}
	}
}
