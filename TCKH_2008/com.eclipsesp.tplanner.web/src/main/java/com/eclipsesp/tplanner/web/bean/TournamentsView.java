/*
 * 14.08.2008 16:07:14
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.bean;

/**
 * For checkboxes
 * 
 * @author vpostrigan
 */
public class TournamentsView {
	private Long id[];

	public TournamentsView() {
		super();
	}

	public TournamentsView(Long[] id) {
		super();
		this.id = id;
	}

	public Long[] getId() {
		return this.id;
	}

	public void setId(Long[] id) {
		this.id = id;
	}
}
