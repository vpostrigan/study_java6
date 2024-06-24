/*
 * 25 Лип 2008 16:02:13
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 267 $
 * $Author: pmarinchev_tckh $
 * $Date: 2008-07-29 11:37:38 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.*;

public class PromoManagerTest
        extends BaseDatabaseTests {

	/** Object being used */
	PromoManager promoManager;

	@Before
	public void setUp() throws Exception {
		PromoJdbcDaoSupport daoSupport = this.setUp(PromoJdbcDaoSupport.class);
		this.promoManager = new PromoManager();
		this.promoManager.setDaoSupport(daoSupport);
	}

	@Test
	public void testCreate() {
		try {
			Promo promo =
			        new Promo(
			                new Long(1),
			                Security.encodePromoCode("rshportko@eclipse-sp.com"));
			this.promoManager.create(promo);
			Collection<Promo> promos =
			        this.promoManager.getByPromoCode(Security.encodePromoCode("rshportko@eclipse-sp.com"));
			Assert.assertFalse(promos.isEmpty());
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetByTournamentId() {
		try {
			Collection<Promo> promos =
			        this.promoManager.getByTournamentId(new Long(1));
			Assert.assertFalse(promos.isEmpty());
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testGetByPromoCode() {
		try {
			Collection<Promo> promos =
			        this.promoManager.getByPromoCode(Security.encodePromoCode("pmarinchev@eclipse-sp.com"));
			Assert.assertFalse(promos.isEmpty());
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

	@Test
	public void testDelete() {
		try {
			Promo promo =
			        new Promo(
			                new Long(1),
			                Security.encodePromoCode("pmarinchev@eclipse-sp.com"));
			this.promoManager.delete(promo);
			Assert.assertTrue(this.promoManager.getByTournamentId(new Long(1)).isEmpty());
		} catch (Exception e) {
			this.logger.error(e);
			Assert.fail();
		}
	}

}
