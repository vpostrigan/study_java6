/*
 * 29 Лип 2008 12:15:51
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 640 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-08 10:55:00 +0300 (Пт, 08 авг 2008) $
 */
package com.eclipsesp.tplanner.web.bean;



import java.util.*;

/**
 * Bean that describes view for tournament view page. 
 * 
 * @author rshportko
 */
public class TournamentView {
		
	private Long id;
	private String gameKind;
	private String tournamentName;	
	private String status;
	private String owner;
	private Date startDate;
	private Date finishDate;
	private String visibility;
	private String description;	
	

	public TournamentView(Long id, String gameKind, String tournamentName,
            String status, String owner, Date startDate, Date finishDate,
            String visibility, String description) {	    
		this.id = id;
	    this.gameKind = gameKind;
	    this.tournamentName = tournamentName;
	    this.status = status;
	    this.owner = owner;
	    this.startDate = startDate;
	    this.finishDate = finishDate;
	    this.visibility = visibility;
	    this.description = description;
    }
	
	public TournamentView() {
	   //Do nothing
    }
	

	public String getGameKind() {
    	return gameKind;
    }
	public void setGameKind(String gameKind) {
    	this.gameKind = gameKind;
    }
	public String getTournamentName() {
    	return tournamentName;
    }
	public void setTournamentName(String tournamentName) {
    	this.tournamentName = tournamentName;
    }
	public String getStatus() {
    	return status;
    }
	public void setStatus(String status) {
    	this.status = status;
    }
	public String getOwner() {
    	return owner;
    }
	public void setOwner(String owner) {
    	this.owner = owner;
    }
	public Date getStartDate() {
    	return startDate;
    }	
	public void setStartDate(Date startDate) {
    	this.startDate = startDate;
    }
	public Date getFinishDate() {
    	return finishDate;
    }
	public void setFinishDate(Date finishDate) {
    	this.finishDate = finishDate;
    }
	public String getVisibility() {
    	return visibility;
    }
	public void setVisibility(String visibility) {
    	this.visibility = visibility;
    }
	
	public String getDescription() {
    	return description;
    }

	public void setDescription(String descripiton) {
    	this.description = descripiton;
    }

	public Long getId() {
    	return id;
    }

	public void setId(Long id) {
    	this.id = id;
    }
	
}
