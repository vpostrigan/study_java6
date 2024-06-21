package journey;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.DispatcherServlet;

public class JourneyDispatcherServlet extends DispatcherServlet {
	
	private static final long serialVersionUID = 5825588642993247311L;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	protected void initFrameworkServlet() throws ServletException,
	    BeansException {
		super.initFrameworkServlet();
		
		logger.info("JONE: JourneyDispatcherServlet started.");		
		
		// Initializing DEBUG state
		ServletContext sc = this.getServletContext();
		if (null != sc) {
			//String debugParam =
			//        sc.getInitParameter(TplannerDispatcherServlet.DEBUG_PARAM);
			//if (null != debugParam) {
				// TODO apply DEBUG state if any
			//}
		}
	}
}
