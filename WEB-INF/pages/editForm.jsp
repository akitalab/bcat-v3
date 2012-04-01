<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>図書情報編集</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <B>図書情報編集</B>
    <FONT color="red"><UL><html:errors/></UL></FONT>
    <html:form action="/edit">
      <bean:define toScope='request' id='editAll' value='true' />
      <jsp:include page="/WEB-INF/pages/bookEditorPart.jsp" flush="true" />
      <html:hidden property='step' value='prepare' />
      管理者パスワード：<html:password property='password' /><BR>
      <html:submit value='変更' />
    </html:form>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>