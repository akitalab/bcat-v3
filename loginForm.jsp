<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>ログイン</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <B>ログイン</B>
    <logic:present scope="request" name="loginForm">
     <logic:notEmpty scope="request" name="loginForm" property="nextAction">
      <P>処理を続けるために、まずシステムにログインしてください。</P>
     </logic:notEmpty>
    </logic:present>
    <FONT color="red"><UL><html:errors/></UL></FONT>
    <TABLE align=center width="80%">
      <html:form action="/login" focus="user">
        <html:hidden property="nextAction" />
        <TR>
          <TD align=right>ID: </TD>
          <TD><html:text property="user" /></TD>
        </TR>
        <TR>
          <TD align=right>パスワード: </TD>
          <TD><html:password property="password" /></TD>
        </TR>
        <TR>
          <TD></TD><TD><html:submit value="ログイン" /></TD>
        </TR>
      </html:form>
    </TABLE>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>
