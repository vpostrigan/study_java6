package chap24;

import java.text.NumberFormat;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.*;
import javax.servlet.http.*;

public class PerLocaleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        //Get the client's Locale
        Locale locale = request.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.WelcomeBundle", locale);
        String welcome = bundle.getString("Welcome");

        NumberFormat nft = NumberFormat.getPercentInstance(locale);
        String formatted = nft.format(0.51);

        //Display the locale
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html><head><title>" + welcome + "</title></head><body>");

        out.println("<h2>" + bundle.getString("Hello") + " " +
                bundle.getString("and") + " " +
                welcome + "</h2>");

        out.println("Locale: ");
        out.println(locale.getLanguage() + "_" + locale.getCountry());

        out.println("<br /><br />");
        out.println("NumberFormat.getPercentInstance(): " + formatted);

        out.println("</body></html>");
    } //end doGet

}
