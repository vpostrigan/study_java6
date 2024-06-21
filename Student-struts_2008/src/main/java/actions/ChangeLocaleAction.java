package actions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChangeLocaleAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		Locale locale = getLocale(request);
		
		String language = (String) PropertyUtils.getSimpleProperty(form, "language");
		
		locale = new Locale(language, "");
		
		
		// reset the Struts locale
		session.setAttribute(Globals.LOCALE_KEY, locale);
		
		// reset the JSTL locale
		Config.set(session, Config.FMT_LOCALE, locale);
		
		return (mapping.findForward(finals.Final.FORWARD_SUCCESS));
	}
}
