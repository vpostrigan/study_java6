/*
 * 31.07.2008 17:26:34
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.bean;

public class LettersView {

	private String letter;

	public LettersView(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return this.letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
}
