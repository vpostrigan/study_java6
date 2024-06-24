/*
 * 30 Лип 2008 17:01:46
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 880 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 19:58:58 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.web.tag;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.logging.*;

import java.lang.Math;
import java.util.*;

/**
 * 
 * Forms on the page of reference. 
 * As numbers of pages.
 * 
 * @author dproshkin
 */
public class Pager
        extends TagSupport {

	private static final long serialVersionUID = 2802335731826230073L;

	private final Log logger = LogFactory.getLog(getClass());
	
	/** Accepts the size of a deduced collection. For generation of pages. */
	private String size;

	/** The quantity of pages is stored */
	private Integer pagesSupply;

	/** It is stored pages on which it is possible to pass */
	private NavigableSet<Integer> navigableSet;

	/** For definition of the minimal value of quantity of pages */
	private Integer number;

	/** Quantity of numbers of pages */
	private final Integer viewPage = 5;

	/** Are used for localization. */
	private String langPages;

	private String langNext;

	private String langPrevious;

	/** Stores a full way to page. */
	private String path;

	/** For definition of the minimal value of quantity of pages */
	private Integer selectPage;

	private Integer tempValue ;
	
	private String generateStringOutput;
	
	private HttpServletRequest httpServletRequest;

	public int doStartTag() throws JspException {

		try {
			/** We get the complete path of page */
			this.pageContext.getOut().println("<br>");
			httpServletRequest =
			        (HttpServletRequest) this.pageContext.getRequest();
			path ="<a href=\""
			                + httpServletRequest.getRequestURL().toString().replaceAll(
			                        "WEB-INF/", "");
			try {
				this.logger.info("====="+httpServletRequest.getQueryString()+"=====");
				this.logger.info("====="+httpServletRequest.getQueryString().indexOf("value=")+"=====");
/*				selectPage =
				        Integer.valueOf(httpServletRequest.getQueryString().substring(
				                httpServletRequest.getQueryString().indexOf("value=")+6));
*/				selectPage = Integer.valueOf(httpServletRequest.getParameter("value"));
				this.logger.info("====="+httpServletRequest.getParameter("value")+"======");
				this.logger.info("====="+selectPage+"=====");
			} catch (NullPointerException ex) {
				selectPage = 1;
			}

			this.pageContext.getOut().print("<br>");
			navigableSet = new TreeSet<Integer>();

			// Has defined the greatest possible quantity of pages
			this.pagesSupply =
			        (int) (Math.round(Double.valueOf(this.getSize()) / 15 + 0.45 ));
			if (this.pagesSupply.equals(0) ){
				this.pagesSupply = 1;
			}
			this.number = (int) (Math.round(this.pagesSupply) / this.viewPage);

			// Check of quantity of pages is less viewPage
			if (number <= 1) {
				this.number = this.pagesSupply;
			} else {
				this.number = this.viewPage;
			}
			
		if ((this.pagesSupply - this.selectPage <= this.number)) {
				if ((this.pagesSupply - this.selectPage) != 0) {
						if(this.pagesSupply - this.selectPage <= this.viewPage){
							this.tempValue = this.pagesSupply;
						} 
						else {
							this.tempValue = this.selectPage+2;
						}
				} else {
					this.tempValue = 1;
				}
			} else {
				this.tempValue = this.selectPage+3;
			}
			this.pageContext.getOut().print(this.getLangPages() + ":&nbsp");
		
			if (selectPage+1 <= this.pagesSupply) {
				this.pageContext.getOut().print(this.path + 
						String.format("?value=%s\">%s</a>&nbsp",selectPage + 1,
				        String.format(this.getLangNext()+ "&nbsp")));
			}
			
			for (int i = 1;i <= this.number; i++) {
				this.navigableSet.add(i);
			}
			if(selectPage-2>1){
				for (int i1 = (this.selectPage-2);i1 < this.tempValue; i1++) {
					this.navigableSet.add(i1);
			}
				}
			if (this.pagesSupply>2){
				for (int i11 = (this.pagesSupply-(this.viewPage-1));i11 <= this.pagesSupply; i11++) {
				}
			}
			this.logger.info("==="+this.navigableSet+"====");

			for (Integer list : this.navigableSet) {
				if (this.selectPage!=list.intValue()){	
					generateStringOutput = "&nbsp " + path + 
						String.format("?value=%s\">%s</a>&nbsp ",list,list);
					} 
				else 
					{
						generateStringOutput = String.format("<snap> %s </snap>", list.intValue());
					}
			
				if (list.intValue()!= this.pagesSupply){
					if(this.navigableSet.higher(list.intValue())-list.intValue()>4){
						generateStringOutput = generateStringOutput+ "...";
					}
				}
				
				this.pageContext.getOut().println(generateStringOutput);
			}

			if (selectPage > 1) {
				this.pageContext.getOut().print("&nbsp" + this.path + 
						String.format("?value=%s\">%s</a> ",selectPage - 1,
				        String.format(this.getLangPrevious())));
			}
		} catch (IOException e) {
			throw new JspTagException(
			        "Error: IOException while writing to the user");
		}
		this.navigableSet.clear();
		return Tag.SKIP_BODY;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLangPages() {
		return this.langPages;
	}

	public void setLangPages(String langPages) {
		this.langPages = langPages;
	}

	public String getLangNext() {
		return langNext;
	}

	public void setLangNext(String langNext) {
		this.langNext = langNext;
	}

	public String getLangPrevious() {
		return this.langPrevious;
	}

	public void setLangPrevious(String langPrevious) {
		this.langPrevious = langPrevious;
	}

}
