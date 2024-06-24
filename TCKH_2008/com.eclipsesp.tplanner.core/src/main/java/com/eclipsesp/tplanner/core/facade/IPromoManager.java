/*
 * 25 Лип 2008 15:55:04
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 222 $
 * $Author: rshportko_tckh $
 * $Date: 2008-07-25 17:26:15 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**Defines general contract to manage "promo" table*/

public interface IPromoManager {
	
	public Collection<Promo> getByTournamentId(Long tournamentId)
    throws DataAccessException;

public Collection<Promo> getByPromoCode(String promoCode)
    throws DataAccessException;

public void create(Promo promo) throws DataAccessException;

public void delete(Promo promo) throws DataAccessException;

}
