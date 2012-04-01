<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>}‘ŒŸõŒ‹‰Ê</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <P><FONT color="red"><html:errors/></FONT></P>
    <jsp:include page="/WEB-INF/pages/searchResultPart.jsp" flush="true" />
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>