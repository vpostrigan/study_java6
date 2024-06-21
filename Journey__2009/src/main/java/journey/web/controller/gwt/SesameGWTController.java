package journey.web.controller.gwt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Sergey Bykov
 */

public class SesameGWTController
        extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//modelMap.put("svnRevision", this.revisionProvider.getRevision());
		return new ModelAndView("SesameGWT", modelMap);
	}

}
