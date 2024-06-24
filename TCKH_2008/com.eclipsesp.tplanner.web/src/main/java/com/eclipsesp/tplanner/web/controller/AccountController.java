/*
 * 4 Лип 2008 13:58:45
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 823 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 11:42:08 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.bean.*;

@Controller
@Permission(tournaments = SingleMask.CAN_NOTHING, users = SingleMask.CAN_READ_ALL)
public class AccountController {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	ITournamentManager tournamentManager;

	@Autowired
	IAccountManager accountManager;

	@Autowired
	IStatusManager statusManager;

	@RequestMapping(value = "/account*")
	protected String getPageId() {
		return MessageKeys.ACCOUNT;
	}
	/**
	 * Method set's fields from Account page, validating them. 
	 * 
	 * @param id - unique identifier user. 
	 * @param account - return object from database.
	 * 
	 * @return object account.
	 */
	@ModelAttribute("accountForm")
	public Account listTournaments(HttpServletRequest request,
	    @RequestParam(value = "id", required = false)  Integer id) {
		Account account = this.accountManager.getById(id.longValue());
		return account;
	}
	/**
	 * Method set's fields in Account page. 
	 * 
	 * @param id - unique identifier user. 
	 * @param tournaments - return collection tournament from database,when user owner.
	 * 
	 * @return collection of tournament view beans to display.
	 */

	@ModelAttribute("tournaments")
	public Collection<TournamentView> getTournaments(
			@RequestParam(value = "id", required = false)Long ownerId) {
		return this.getTournaments(this.tournamentManager.getByOwnerId(ownerId));
	}
	/**
	 * Method set's fields in Account page. 
	 * 
	 * @param id - unique identifier user. 
	 * @param tournamentsTakePart - return collection tournament from database,
	 * when user take part of tournament.
	 * 
	 * @return collection of tournament view beans to display.
	 */
	@ModelAttribute("tournamentsTakePart")
	public Collection<TournamentView> getTournamentsTakePart(
			@RequestParam(value = "id", required = false)Long ownerId) {
			
			Collection<Tournament> tournament =  new ArrayList<Tournament>();
			Collection <User2Tournament> user2Tournament = this.tournamentManager.getUser2TournamentAccount(ownerId);
		try {
			for (User2Tournament temp : user2Tournament) {
				if (temp.getStatusId()==5||temp.getStatusId()==6){
					tournament.add(this.tournamentManager.getById(temp.getTournamentId()));
				}
			}
		} catch (Exception e) {
			this.logger.error(e);
		}
	return this.getTournaments(tournament);
	}
	/**
	 * Method set's fields in Account page. 
	 * 
	 * @param id - unique identifier user. 
	 * @param tournamentsPending - return collection tournament from database,
	 * when user status tournament pending.
	 * 
	 * @return collection of tournament view beans to display.
	 */
	@ModelAttribute("tournamentsPending")
	public Collection<TournamentView> getTournamentsPending(
			@RequestParam(value = "id", required = false)Long ownerId) {
			
			Collection<Tournament> tournament =  new ArrayList<Tournament>();
			Collection <User2Tournament> user2Tournament = this.tournamentManager.getUser2TournamentAccount(ownerId);
		try {
			for (User2Tournament temp : user2Tournament) {
				if (temp.getStatusId()==4){
					tournament.add(this.tournamentManager.getById(temp.getTournamentId()));
				}
			}
		} catch (Exception e) {
			this.logger.error(e);
		}
	return this.getTournaments(tournament);
	}
	/**
	 * Method set's fields in bean Account. 
	 * 
	 * @param tournaments - generate collection tournament for view in page.
	 * 
	 * @return collection of tournament view beans to display.
	 */
	public Collection<TournamentView> getTournaments(Collection<Tournament> tournaments){
		
			Collection<TournamentView> result = new ArrayList<TournamentView>();
		try{
			for (Tournament tour : tournaments) {
				TournamentView tournamentView = new TournamentView();
				tournamentView.setId(tour.getId());
				tournamentView.setGameKind(tour.getGameKind());
				tournamentView.setTournamentName(tour.getTournamentName());
				tournamentView.setVisibility(tour.getVisibility());
				tournamentView.setStartDate(tour.getStartDate());
				tournamentView.setFinishDate(tour.getFinishDate());
				tournamentView.setDescription(tour.getDescription());
	
				tournamentView.setOwner(this.accountManager.getById(
				        tour.getAccountId()).getNick());
	
				tournamentView.setStatus(this.statusManager.getById(
				        tour.getStatusId()).getStatusName());
				this.logger.info("Class AccountController method getTournaments.Parametr 'tournamentView': < "+
						"Id="+tournamentView.getId()+" :"+
						"GameKind="+tournamentView.getGameKind()+" :"+
						"TournamentName="+tournamentView.getTournamentName()+" :"+
						"Visibility="+tournamentView.getVisibility()+" :"+
						"StartDate="+tournamentView.getStartDate()+" :"+
						"FinishDate="+tournamentView.getFinishDate()+" :"+
						"Description="+tournamentView.getDescription()+" :");
				result.add(tournamentView);
		}
		}catch(Exception e){
			this.logger.error(e);
		}
		return result;
	}
}
