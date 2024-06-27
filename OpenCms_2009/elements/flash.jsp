<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%
	int pictureId=0;
	CmsJspActionElement cms = new CmsJspActionElement(pageContext, request, response);	
	String uri=cms.getRequestContext().getUri();
	if("/start.html".equals(uri)) pictureId=1;
	if("/success.html".equals(uri)) pictureId=2;
	if("/saleskit.html".equals(uri)) pictureId=3;
	if("/contacts.html".equals(uri)) pictureId=4;
	pictureId++;
%>
<%@page import="org.opencms.jsp.CmsJspActionElement"%>

<div class="flash" > 
<script language="javascript">AC_FL_RunContent = 0;</script>
<script src="<cms:link>../scripts/AC_RunActiveContent.js</cms:link>" language="javascript"></script>

<!--url's used in the movie-->
<!--text used in the movie-->
<!--
<p align="center"><font face="Tahoma" size="14" color="#ffffff" letterSpacing="0.000000" kerning="1">Start</font></p>
<p align="center"><font face="Tahoma" size="14" color="#ffffff" letterSpacing="0.000000" kerning="1">Start</font></p>
<p align="center"><font face="Tahoma" size="14" color="#ffffff" letterSpacing="0.000000" kerning="1">Start</font></p>
<p align="center"><font face="Tahoma" size="14" color="#ffffff" letterSpacing="0.000000" kerning="1">Start</font></p>
-->
<!-- saved from url=(0013)about:internet -->
<script language="javascript">
	if (AC_FL_RunContent == 0) {
		alert("This page requires AC_RunActiveContent.js.");
	} else {
		AC_FL_RunContent(
			'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
			'width', '674',
			'height', '280',
			'src', '<cms:link>../images/Menu</cms:link>',
			'quality', 'high',
			'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
			'align', 'none',
			'play', 'true',
			'loop', 'true',
			'scale', 'showall',
			'wmode', 'opaque',
			'devicefont', 'false',
			'id', 'Menu',
			'bgcolor', '#ffffff',
			'name', 'Menu',
			'menu', 'true',
			'allowFullScreen', 'false',
			'allowScriptAccess','sameDomain',
			'movie', '<cms:link>../images/Menu</cms:link>',
			'salign', 'lt',
			'FlashVars', 'defaultPictureId=<%= pictureId %>&start=<cms:link>/start.html</cms:link>&success=<cms:link>/success.html</cms:link>&saleskit=<cms:link>/saleskit.html</cms:link>&contacts=<cms:link>/contacts.html</cms:link>'
			); //end AC code
	}
</script>
<noscript>
	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="674" height="280" id="Menu" align="left">
	<param name="allowScriptAccess" value="sameDomain" />
	<param name="allowFullScreen" value="false" />
	<param name="FlashVars" value="defaultPictureId=<%= pictureId %>&start=<cms:link>/start.html</cms:link>&success=<cms:link>/success.html</cms:link>&saleskit=<cms:link>/saleskit.html</cms:link>&contacts=<cms:link>/contacts.html</cms:link>" />
	<param name="movie" value="<cms:link>../images/Menu.swf</cms:link>" />
	<param name="quality" value="high" />
	<param name="scale" value="showall" />
	<param name="salign" value="lt" /><param name="bgcolor" value="#ffffff" />	
	<embed src="<cms:link>../images/Menu.swf</cms:link>" quality="high" scale="showall" salign="lt" bgcolor="#ffffff" width="674" height="280" name="Menu" align="left" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"  FlashVars="defaultPictureId=<%= pictureId %>&start=<cms:link>/start.html</cms:link>&success=<cms:link>/success.html</cms:link>&saleskit=<cms:link>/saleskit.html</cms:link>&contacts=<cms:link>/contacts.html</cms:link>"/>
	</object>
</noscript>
</div>