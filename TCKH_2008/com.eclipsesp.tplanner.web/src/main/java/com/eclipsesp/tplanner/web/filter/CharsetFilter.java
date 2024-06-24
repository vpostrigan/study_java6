/**
 * 22.07.2008 16:58:29
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 170 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-24 21:12:18 +0300 (Чт, 24 июл 2008) $
 */
package com.eclipsesp.tplanner.web.filter;

import java.io.*;

import javax.servlet.*;

/**
 * This filter sets encoding for HTTP requests.
 * 
 * @author Pavel Marinchev
 */
public class CharsetFilter
        implements Filter {

	private String encoding;

	public void destroy() {
		// Do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain next) throws IOException, ServletException {
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		next.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
		if (null == this.encoding) {
			this.encoding = "utf-8";
		}
	}

}
