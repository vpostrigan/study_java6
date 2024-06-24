/*
 * 25 Лип 2008 15:57:31
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 222 $
 * $Author: rshportko_tckh $
 * $Date: 2008-07-25 17:26:15 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**An implementation of {@link IPromotManager} facade. In particular, it deals
 * with JDBC DAO support classes*/

public class PromoManager
        implements IPromoManager {
	
	@Autowired
	protected PromoJdbcDaoSupport daoSupport;	

	public void create(Promo promo) throws DataAccessException {
		this.daoSupport.create(promo);
	}

	public void delete(Promo promo) throws DataAccessException {
		this.daoSupport.delete(promo);
	}

	public Collection<Promo> getByPromoCode(String promoCode)
	    throws DataAccessException {
		return this.daoSupport.getByPromoCode(promoCode);
	}

	public Collection<Promo> getByTournamentId(Long tournamentId)
	    throws DataAccessException {
		return this.daoSupport.getByTournamentId(tournamentId);
	}
	
	public PromoJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(PromoJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
