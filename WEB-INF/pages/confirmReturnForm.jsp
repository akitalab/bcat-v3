<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<HTML>
<HEAD>
  <TITLE>図書返却確認</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <H3>この図書の返却手続きをします。</H3>
    <bean:define toScope='request' id='withoutButtons' value='true' />
    <jsp:include page="/WEB-INF/pages/bookViewerPart.jsp" flush="true" />
    <html:form action='/return'>
	  <html:submit value='返却する' />
      <html:hidden property='bookId' />
	  <html:hidden property='step' value='confirm' />
    </html:form>
    <P><html:link forward='top'>戻る</html:link></P>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>