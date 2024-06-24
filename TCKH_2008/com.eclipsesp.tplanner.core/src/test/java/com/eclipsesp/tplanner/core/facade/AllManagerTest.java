/*
 * 25.07.2008 17:38:00
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 312 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-30 10:40:28 +0300 (Ср, 30 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import org.junit.runner.*;
import org.junit.runners.*;

/**
 * Test suites
 * 
 * @author vpostrigan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AccountManagerTest.class, GameManagerTest.class,
        StageManagerTest.class, StatusManagerTest.class,
        GroupManagerTest.class, PromoManagerTest.class, RoleManagerTest.class,
        TeamManagerTest.class, TournamentManagerTest.class })
public class AllManagerTest {
	// do nothing
}
