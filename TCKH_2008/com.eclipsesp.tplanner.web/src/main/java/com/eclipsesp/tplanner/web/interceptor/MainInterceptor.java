/*
 * 1 Сер 2008 10:22:52
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.interceptor;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.web.servlet.handler.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.security.*;

/**
 * Intercepts all calls to the controllers, and checking permission of user to
 * access them
 * 
 * @author akrumin
 */
public class MainInterceptor
        extends HandlerInterceptorAdapter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {
		Class clazz = handler.getClass();
		Security.Permission a =
		        (Security.Permission) clazz.getAnnotation(Security.Permission.class);
		if (a == null) {
			return true;
		}
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		if (account.getEmail() != null) {

			int mask = Security.compileSecurityMask(a.users(), a.tournaments());
			int data = account.getPermission();
			if (Security.checkPermissionMask(data, mask)) {

				return true;
			} else {
				response.sendRedirect("error.html");
				return true;
			}
		} else {
			if (!request.getPathInfo().contains("/login.")
			        && !request.getPathInfo().contains("/registration.")) {
				response.sendRedirect("login.html");
			}
			return true;
		}
	}
}
