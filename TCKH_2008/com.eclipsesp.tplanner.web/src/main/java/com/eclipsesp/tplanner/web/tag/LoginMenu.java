/*
 * 5 ��� 2008 14:05:28
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

import com.eclipsesp.tplanner.core.bean.*;

public class LoginMenu
        extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2430434556764353393L;

	@Override
	public int doStartTag() throws JspException {
		try {
			Account account =
			        (Account) this.pageContext.getSession().getAttribute(
			                "account");
			String action = "login.html";
			String aText = "Sign In";
			if (account.getEmail() != null) {
				action = "logout.html";
				aText = "Sign Out";
			}
			this.pageContext.getOut().print(
			        new StringBuilder("<span><a href=\"").append(action).append(
			                "\">").append(aText).append(
			                "</a></span> | <span><a href=\"registration.html\">SignUp</a></span>"));
		} catch (IOException e) {
			throw new JspTagException(
			        "Error: IOException while writing to the user");
		}

		return Tag.SKIP_BODY;
	}

	public void setWidth(String newValue) {
		// this.width = newValue;
	}

	public void setHeight(String newValue) {
		// this.height = newValue;
	}

}
