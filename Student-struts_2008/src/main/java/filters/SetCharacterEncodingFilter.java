package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>Класс для установки кодировки</p>
 */
public class SetCharacterEncodingFilter implements Filter {
    
    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    private static final String ENCODING = "encoding";
    
    public void destroy( ) {
        this.encoding = null;
        this.filterConfig = null;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // Conditionally select and set the character encoding to be used
        if (request.getCharacterEncoding( ) == null) {
            String encoding = selectEncoding(request);
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }

        // Pass control on to the next filter
        chain.doFilter(request, response);
    }
    
    /**
     * @param filterConfig The filter configuration object
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter(ENCODING);
    }

    /**    
     * @param request The servlet request we are processing
     */
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}