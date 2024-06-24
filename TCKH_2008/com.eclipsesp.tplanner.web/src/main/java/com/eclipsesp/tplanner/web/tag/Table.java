/*
 * 11.08.2008 10:51:21
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 36 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-17 17:35:40 +0300 (Чт, 17 июл 2008) $
 */
package com.eclipsesp.tplanner.web.tag;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.logging.*;

import com.eclipsesp.tplanner.web.bean.*;

/**
 * Tag is generation of table on a page
 * 
 * @author dproshkin
 */
public class Table  extends TagSupport {

    private static final long serialVersionUID = -8126337246893309287L;
    
    private final Log logger = LogFactory.getLog(getClass());
    
    /** Collection is passed for displaying */
    Collection<TournamentView> tournamentView;
    /** For connecting of necessity of css. To paint out lines */
    String tdclass;
    
    public int doStartTag() throws JspException {
    	try{
        	int count=0;
        	for(TournamentView tournament :this.getTournamentView()){
    		if (count%2==0){
    			tdclass = "oddRow_";
    		}else{
    			tdclass = "evenRow_";
    		}
    		this.pageContext.getOut().println("<tr>");
    		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,tournament.getGameKind()));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,tournament.getOwner()));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,tournament.getTournamentName()));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,this.formatDate(tournament.getStartDate())));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,this.formatDate(tournament.getFinishDate())));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,tournament.getStatus()));
     		this.pageContext.getOut().print(String.format("<td class=%s>%s</td>",tdclass,tournament.getDescription()));     		
     		this.pageContext.getOut().println("</tr>");
     		count++;
    	}
        	this.pageContext.getOut().println("</table>");
        	this.pageContext.getOut().println("<br/>");
    	}catch(IOException ioe){
    		this.logger.info("Message tag table: "+ioe.getMessage()+" StackTrace tag table: "+ioe.getStackTrace());
    	}
		
		return Tag.SKIP_BODY;
	}

	
    public Collection<TournamentView> getTournamentView() {
    	return tournamentView;
    }

	public void setTournamentView(Collection<TournamentView> tournamentView) {
    	this.tournamentView = tournamentView;
    }
    /**Convert Date to String*/
    public String formatDate(Date date) {
    	if(date!=null){
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");	
    	return formatter.format(date);      
    	}
    	else{
    		return null;
    	}
    }
}
