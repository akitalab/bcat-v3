<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<HTML>
<HEAD>
  <TITLE>}‘•Ô‹pŠm”F</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <H3>‚±‚Ì}‘‚Ì•Ô‹pŽè‘±‚«‚ð‚µ‚Ü‚·B</H3>
    <bean:define toScope='request' id='withoutButtons' value='true' />
    <jsp:include page="/WEB-INF/pages/bookViewerPart.jsp" flush="true" />
    <html:form action='/return'>
	  <html:submit value='•Ô‹p‚·‚é' />
      <html:hidden property='bookId' />
	  <html:hidden property='step' value='confirm' />
    </html:form>
    <P><html:link forward='top'>–ß‚é</html:link></P>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>