package journey.web.filter;

import java.io.*;

import javax.servlet.*;

/**
 * This filter sets encoding for HTTP requests.
 */
public class CharsetFilter implements Filter {

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