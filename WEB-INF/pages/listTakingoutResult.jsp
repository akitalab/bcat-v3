<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>‘Ýo’†}‘ˆê——</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
  	<H3>‘Ýo’†}‘ˆê——</H3>
    <bean:define toScope='request' id='withReturnBookButton' value='true' />
    <jsp:include page="/WEB-INF/pages/searchResultPart.jsp" flush="true" />
	<P><html:link forward="top">–ß‚é</html:link></P>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>