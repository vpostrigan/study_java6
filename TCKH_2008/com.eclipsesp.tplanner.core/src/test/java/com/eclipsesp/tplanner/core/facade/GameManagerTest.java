/*
 * 24 Лип 2008 21:06:34
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 262 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-29 10:45:10 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.junit.*;

import com.eclipsesp.tplanner.core.*;
import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.impl.*;

public class GameManagerTest
        extends BaseDatabaseTests {

	private GameManager gameManager;

	@Before
	public void setUp() throws Exception {
		GameTeamJdbcDaoSupport gameTeamJdbcDaoSupport =
		        this.setUp(GameTeamJdbcDaoSupport.class);
		GameUserJdbcDaoSupport gameUserJdbcDaoSupport =
		        this.setUp(GameUserJdbcDaoSupport.class);
		this.gameManager = new GameManager();
		this.gameManager.setTeamDaoSupport(gameTeamJdbcDaoSupport);
		this.gameManager.setUserDaoSupport(gameUserJdbcDaoSupport);
	}

	@Test
	public void insertToUpdateGameTeam() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.JULY);
		calendar.set(Calendar.DAY_OF_MONTH, 24);
		GameTeam gameTeam = new GameTeam(1, 1, 2, calendar.getTime(), 3, 6, 1, 2);
		this.gameManager.insertToUpdateGameTeam(gameTeam);

	}

	@Test
	public void insertToUpdateGameUser() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.JULY);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		GameUser gameUser = new GameUser(2, 2, 2, calendar.getTime(), 3, 6, 1, 2);
		this.gameManager.insertToUpdateGameUser(gameUser);
	}
	
	@Test
	public void getTeam(){
		ArrayList<GameTeam> list = (ArrayList<GameTeam>) 
			this.gameManager.getTeam("winner","2");
		for (GameTeam gameTea : list) { 
		    this.logger.info(gameTea.getId()+" : "+gameTea.getGroupId()+" : "+
		    		gameTea.getWinner()+" : "+gameTea.getDate()+" : "+
		    		gameTea.getScore1()+" : "+gameTea.getScore2()+" : "+
		    		gameTea.getParticipant1()+" : "+gameTea.getParticipant2());
		}
		Assert.assertNotNull(list);
	}
	@Test
	public void getUser(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		GameUser gameUser = new GameUser(2, 2, 2, calendar.getTime(), 3, 6, 1, 2);
		this.gameManager.insertToUpdateGameUser(gameUser);
		this.gameManager.getUser("group_id","2");
		
		Assert.assertNotNull(this.gameManager.getUser("group_id","2"));

	}
	@Test
	public void deleteTeam(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		this.gameManager.deleteGameTeam(new GameTeam(2,1,1,calendar.getTime(),1,1,1,1));	
	}
	@Test
	public void deleteUser(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		this.gameManager.deleteGameUser(new GameUser(1,1,1,calendar.getTime(),1,1,1,1));	
		
	}
	@Test
	public void getAllTeam(){
		Assert.assertNotNull(this.gameManager.getAllTeam());
	}
	@Test
	public void getAllUser(){
		Assert.assertNotNull(this.gameManager.getAllUser());	
	}
	@Test
	public void getTeamPair(){
		Assert.assertNotNull(this.gameManager.getTeamPair("1","2"));
		Assert.assertTrue(this.gameManager.getTeamPair("A","3").isEmpty());
	}
	@Test
	public void getUserPair(){
		Assert.assertNotNull(this.gameManager.getUserPair("1","2"));	
		Assert.assertTrue(this.gameManager.getUserPair("6","R").isEmpty());
	}
	
	
}
