/*
 * 30.07.2008 10:37:42
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.tag;

import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/** This tag generate logotype of tournament and add Title text */
public class Logo
        extends TagSupport {
	private String image = null;

	private String width = null;

	private String height = null;

	String imgDir = "../images/";

	@Override
	public int doStartTag() throws JspException {

		try {
			this.pageContext.getOut().print(
			        new StringBuilder("<img src=\"").append(this.imgDir).append(
			                this.image).append("\" width=\"").append(this.width).append(
			                "\" height=\"").append(this.height).append(
			                "\" align=\"left\">").toString());
		} catch (IOException e) {
			throw new JspTagException(
			        "Error: IOException while writing to the user");
		}

		return Tag.SKIP_BODY;

	}

	public void setImage(String newValue) {
		this.image = newValue;
	}

	public void setWidth(String newValue) {
		this.width = newValue;
	}

	public void setHeight(String newValue) {
		this.height = newValue;
	}

}
