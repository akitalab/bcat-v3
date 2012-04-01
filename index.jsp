<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>図書管理システムメニュー</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <B>図書管理システムメニュー</B>
    <UL>
      <LI><html:link forward='search'>検索</html:link>
      <LI><html:link forward='list'>全図書一覧</html:link>
      <LI><html:link forward='return'>貸出図書一覧</html:link>
      <LI><html:link forward='add'>新規図書登録</html:link>
    </UL>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>