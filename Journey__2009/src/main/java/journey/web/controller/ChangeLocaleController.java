package journey.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Change local
 * 
 * @author vpostrigan
 */
@Controller
public class ChangeLocaleController {

	@RequestMapping(value = "/index.changeLanguage", method = RequestMethod.GET)
	public String changeLocal(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "language", required = false) String language) {
		
		HttpSession session = request.getSession();
		Locale locale = request.getLocale();

		locale = new Locale(language);

		// reset the Struts locale
		//session.setAttribute(Globals.LOCALE_KEY, locale);

		// reset the JSTL locale
		Config.set(session, Config.FMT_LOCALE, locale);
		
		//RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
		
		return "redirect:index";
	}
}
