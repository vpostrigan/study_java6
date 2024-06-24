/*
 * 25 Лип 2008 15:09:11
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 222 $
 * $Author: rshportko_tckh $
 * $Date: 2008-07-25 17:26:15 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import java.util.*;

import org.springframework.dao.*;
import org.springframework.jdbc.core.simple.*;

import com.eclipsesp.tplanner.core.bean.*;

public class PromoJdbcDaoSupport
        extends AbstractSimpleJdbcDaoSupport {

	SimpleJdbcTemplate template;

	@Override
	protected void initDao() throws Exception {
		this.template = this.getSimpleJdbcTemplate();
	}

	public Collection<Promo> getByTournamentId(Long tournamentId)
	    throws DataAccessException {
		return this.template.query(
		        "select * from `promo` where `tournament_id` = ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Promo.class),
		        tournamentId);
	}

	public Collection<Promo> getByPromoCode(String promoCode)
	    throws DataAccessException {
		return this.template.query("select * from `promo` where `promo` = ?",
		        ParameterizedBeanPropertyRowMapper.newInstance(Promo.class),
		        promoCode);
	}

	public void create(Promo promo) throws DataAccessException {
		template.update(
		        "INSERT INTO `promo` (tournament_id, promo) VALUES (?, ?)",
		        promo.getTournamentId(), promo.getPromo());
	}

	public void delete(Promo promo) throws DataAccessException {
		template.update(
		        "DELETE FROM `promo` WHERE `tournament_id`=? AND `promo`=?",
		        promo.getTournamentId(), promo.getPromo());
	}
}
