/*
 * 1 ��� 2008 13:42:10
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Filter checking session existence and if account attribute does not exist in
 * session, it would be added.
 * 
 * @author akrumin
 */
public class SessionFilter
        implements Filter {
	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
	    FilterChain next) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getSession().getAttribute("account") == null) {
			AccountWithPermission account = new AccountWithPermission();
			account.setNick("Guest");
			request.getSession().setAttribute("account", account);
		}
		if (request.getSession().getAttribute("permission") == null) {
			int permission = 0;
			request.getSession().setAttribute("permission", permission);
		}
		if (request.getSession().getAttribute("invitescount") == null) {
			request.getSession().setAttribute("invitescount", 0);
		}
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addCookie(new Cookie("email", "asdasdasd"));
		next.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
