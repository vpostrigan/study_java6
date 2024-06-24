/*
 * 29 Лип 2008 13:57:57
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 903 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-14 16:32:47 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.bean.*;
import com.eclipsesp.tplanner.web.form.*;

/**
 * Tournament view page controller.
 * 
 * @author rshportko
 */
@Controller
@Permission(tournaments = SingleMask.CAN_READ_ALL, users = SingleMask.CAN_NOTHING)
public class TournamentsController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	AccountManager accountManager;

	@Autowired
	StatusManager statusManager;

	@Autowired
	IRoleManager roleManager;

	int maskForAdmin =
	        Security.compileSecurityMask(SingleMask.CAN_NOTHING,
	                SingleMask.CAN_WRITE_ALL);

	int maskForOwner =
	        Security.compileSecurityMask(SingleMask.CAN_NOTHING,
	                SingleMask.CAN_WRITE_SELF);
	
	@RequestMapping(value = "/tournaments*", method = RequestMethod.GET, params = "!ownerId")
	public String getTournamentPage(HttpServletRequest request) {
		String prevPage = "tournaments";
		request.getSession().setAttribute("myTournamentsPage", prevPage);
		return this.getPageId();
	}

	protected String getPageId() {
		return MessageKeys.TOURNAMENTS;
	}

	@ModelAttribute("pageid")
	public PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(this.getPageId());
		return p;
	}

	/** Add button handler */
	@RequestMapping(method = RequestMethod.POST, params = "action=Add")
	public String add(HttpServletRequest request) {
		return "redirect:addTournament.html";
	}

	/**
	 * Delete button handler. Chek's user rights and after that allow or deny
	 * deleting tournament.
	 * 
	 * 
	 * @param tournamentView
	 *            bean that represent tournament for display
	 * 
	 * @return redirect to tournaments view page
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Delete")
	public String delete(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		AccountWithPermission account =
	        (AccountWithPermission) request.getSession().getAttribute(
	                "account");
		if (tournamentView.getId() != null) {			
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "pending")) {				
				try {
					
					if (account.getEmail() != null) {
						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								this.tournamentManager.delete(tournamentView.getId());
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();

							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								this.tournamentManager.delete(tournamentView.getId());
							} else {
								return "redirect:error.html";
							}
						}
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return "redirect:tournaments.html";
			} else {
				errors.reject("error.incorrectStatus");
				return this.getPageId();
			}
		} else {
			errors.reject("error.unselectedToDelete");			
			return this.getPageId();
				
		}
	}

	/**
	 * @param tournamentView
	 *            tournament view bean.
	 * 
	 * @return redirect to edit page with id of tournament to edit.
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Edit")
	public String edit(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "pending")) {
				try {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {
						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								return "redirect:editTournament?editTournamentId="
								        + tournamentView.getId();
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();

							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								return "redirect:editTournament?editTournamentId="
								        + tournamentView.getId();
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();
							}
						}
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return this.getPageId();
			} else {
				errors.reject("error.incorrectStatus");
				return this.getPageId();
			}

		} else {
			errors.reject("error.unselectedToEdit");
			return this.getPageId();
		}
	}

	/**
	 * @param tournamentView
	 *            tournament view bean.
	 * 
	 * @return redirect to send promo-code page with id of tournament to invite.
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Invite")
	public String invite(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "pending")) {
				try {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {

						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								return "redirect:promosend?id="
								        + tournamentView.getId();
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();

							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								return "redirect:promosend?id="
								        + tournamentView.getId();
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();
							}
						}
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return this.getPageId();
			} else {
				errors.reject("error.incorrectStatus");
				return this.getPageId();
			}

		} else {
			errors.reject("error.unselected");
			return this.getPageId();
		}
	}

	/**
	 * Start button handler. Chek's user rights and after that allow or deny
	 * starting tournament.
	 * 
	 * 
	 * @param tournamentView
	 *            bean that represent tournament for display
	 * 
	 * @return redirect to tournaments view page
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Start")
	public String start(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "pending")) {
				try {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {

						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								this.tournamentManager.start(tournamentView.getId());
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();

							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								this.tournamentManager.start(tournamentView.getId());
							} else {
								return "redirect:error.html";
							}
						}
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return "redirect:tournaments.html";
			} else {
				errors.reject("error.incorrectStatus");
				return this.getPageId();
			}
		} else {
			errors.reject("error.unselected");
			return this.getPageId();
		}
	}

	/**
	 * Finish button handler. Chek's user rights and after that allow or deny
	 * finishing tournament.
	 * 
	 * 
	 * @param tournamentView
	 *            bean that represent tournament for display
	 * 
	 * @return redirect to tournaments view page
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Finish")
	public String finish(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "started")) {
				try {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {

						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								this.tournamentManager.finish(tournamentView.getId());
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();

							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								this.tournamentManager.finish(tournamentView.getId());
							} else {
								return "redirect:error.html";
							}
						}
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return "redirect:tournaments.html";
			} else {
				errors.reject("error.notStarted");
				return this.getPageId();
			}
		} else {
			errors.reject("error.unselected");
			return this.getPageId();
		}
	}

	@ModelAttribute("account")
	public AccountWithPermission getAccountId(HttpServletRequest request) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		return account;
	}

	/**
	 * @param tournamentView
	 *            tournament view bean.
	 * 
	 * @return redirect to edit page with id of tournament to edit.
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Details")
	public String details(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			try {

				String statusName =
				        this.statusManager.getById(
				                this.tournamentManager.getById(
				                        tournamentView.getId()).getStatusId()).getStatusName();

				String visibility =
				        this.tournamentManager.getById(tournamentView.getId()).getVisibility();

				if (statusName.equalsIgnoreCase("pending")) {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {
						int data = account.getPermission();
						if (this.tournamentManager.getById(
						        tournamentView.getId()).getAccountId() != account.getId()) {
							if (Security.checkPermissionMask(data,
							        this.maskForAdmin)) {
								return "redirect:tournamentUsers.jsp?tournamentId="
								        + tournamentView.getId();
							} else {
								if (visibility.equalsIgnoreCase("Public")) {
									return "redirect:tournamentUsersLook.jsp?tournamentId="
									        + tournamentView.getId();
								} else {
									errors.reject("error.incorrectOwner");
									return this.getPageId();
								}
							}
						} else {
							if (Security.checkPermissionMask(data,
							        this.maskForOwner)) {
								return "redirect:tournamentUsers.jsp?tournamentId="
								        + tournamentView.getId();
							} else {
								errors.reject("error.incorrectOwner");
								return this.getPageId();
							}
						}
					}
				} else {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {
						return "redirect:tournamentUsersLook.jsp?tournamentId="
						        + tournamentView.getId();
					}
				}
			} catch (Exception e) {
				this.logger.error(e);
			}
			return this.getPageId();
		} else {
			errors.reject("error.unselected");
			return this.getPageId();
		}
	}

	/**
	 * Delete button handler. Chek's user rights and after that allow or deny
	 * deleting tournament.
	 * 
	 * 
	 * @param tournamentView
	 *            bean that represent tournament for display
	 * 
	 * @return redirect to tournaments view page
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Join")
	public String join(@ModelAttribute("tournamentView")
	TournamentView tournamentView, Errors errors, HttpServletRequest request) {
		if (tournamentView.getId() != null) {
			if (this.statusManager.getById(
			        this.tournamentManager.getById(tournamentView.getId()).getStatusId()).getStatusName().equalsIgnoreCase(
			        "pending")) {
				try {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");

					if (this.tournamentManager.getById(tournamentView.getId()).getAccountId() != account.getId()) {
						this.tournamentManager.createUser2Tournament(
						        account.getId(),
						        tournamentView.getId().intValue(), 5);
						return this.getPageId();
					} else {
						errors.reject("error.joinOwnTournament");
						return this.getPageId();
					}
				} catch (Exception e) {
					this.logger.error(e);
				}
				return "redirect:tournaments.html";
			} else {
				errors.reject("error.incorrectStatus");
				return this.getPageId();
			}
		} else {
			errors.reject("error.unselected");
			return this.getPageId();
		}
	}

	/**
	 * Setup's model attribute "tournaments". Compare's received id with current
	 * user id to avoid incorrect output.
	 */
	@ModelAttribute("tournaments")
	public Collection<TournamentView> getTournaments(HttpServletRequest request) {
		return this.getTournaments(new Long(-1), request);
	}

	/**
	 * @param request -
	 *            HttpServletRequest
	 * 
	 * @return true - if current page have "ownerId" parameter. false -
	 *         otherwise.
	 */
	public static boolean checkMyTournamentsPage(HttpServletRequest request) {
		String previousPage =
		        (String) request.getSession().getAttribute("myTournamentsPage");
		if (previousPage.equalsIgnoreCase("myTournaments")) {
			return true;
		} else {
			return false;
		}
	}

	/** @return collection of my own tournaments */
	@RequestMapping(value = "/tournaments*", method = RequestMethod.GET)
	@ModelAttribute("tournaments")
	public Collection<TournamentView> myOwnTournaments(
	    @RequestParam(value = "ownerId", required = false)
	    Long ownerId, HttpServletRequest request) {

		String prevPage = "myTournaments";
		request.getSession().setAttribute("myTournamentsPage", prevPage);

		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		if (ownerId == account.getId()||
		Security.checkPermissionMask(account.getPermission(),
		Security.compileSecurityMask(SingleMask.CAN_WRITE_ALL, SingleMask.CAN_WRITE_ALL))) {
			return this.getTournaments(ownerId, request);
		} else {
			return null;
		}
	}

	/**
	 * @return collection of tournament view beans to display.
	 * 
	 * Display private tournaments only for admins or for their owners.
	 */
	public Collection<TournamentView> getTournaments(Long ownerId,
	    HttpServletRequest request) {
		Collection<TournamentView> result = new ArrayList<TournamentView>();
		try {
			Collection<Tournament> tournaments;
			if (ownerId == -1) {
				tournaments = this.tournamentManager.getAll();
			} else {
				tournaments = this.tournamentManager.getByOwnerId(ownerId);
			}

			for (Tournament tour : tournaments) {
				TournamentView tournamentView = new TournamentView();
				tournamentView.setId(tour.getId());
				tournamentView.setGameKind(tour.getGameKind());
				tournamentView.setTournamentName(tour.getTournamentName());				
				tournamentView.setStartDate(tour.getStartDate());
				tournamentView.setFinishDate(tour.getFinishDate());
				tournamentView.setDescription(tour.getDescription());

				tournamentView.setOwner(this.accountManager.getById(
				        tour.getAccountId()).getNick());

				tournamentView.setStatus(this.statusManager.getById(
				        tour.getStatusId()).getStatusName());

				if (tour.getVisibility().equalsIgnoreCase("public")) {
					result.add(tournamentView);
				} else {
					AccountWithPermission account =
					        (AccountWithPermission) request.getSession().getAttribute(
					                "account");
					if (account.getEmail() != null) {
						int data = account.getPermission();
						if (Security.checkPermissionMask(data,
						        this.maskForAdmin)
						        || account.getId() == tour.getAccountId()
						        || this.tournamentManager.checkUser2Tournament(
						                tour.getId(), account.getId())) {

							result.add(tournamentView);
						}
					}
				}
			}
		} catch (Exception e) {
			this.logger.error(e);
		}

		return result;
	}

}
