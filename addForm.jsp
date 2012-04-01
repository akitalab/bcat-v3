<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>新規図書登録</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <B>新規図書登録</B>
    <FONT color="red"><UL><html:errors/></UL></FONT>
    <html:form action="/add" focus="code">
      <jsp:include page="/WEB-INF/pages/bookEditorPart.jsp" flush="true" />
      <html:hidden property='step' value='prepare' />
      <html:submit value='登録' />
    </html:form>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>