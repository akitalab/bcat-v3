<%@ page pageEncoding="Shift_JIS" %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>

<!-- BEGIN header -->
<TABLE align=center width='100%' cellspacing=0 cellpadding=3 border=0>
 <TR>
  <TD bgcolor="#339933" width=90% align=left valign=center nowrap>
   <FONT color="#ffffff"><B>【bcat】図書管理システム</B></FONT>
  </TD>
  <TD bgcolor="#339933" align=right valign=center nowrap>
    <FONT color="#ffffff" size=-1><B>
      <logic:present scope="session" name="user">
	   ユーザ：<bean:write scope="session" name="user" />&nbsp;
      </logic:present>
    </B></FONT>
  </TD>
 </TR>
 <TR>
  <TD bgcolor="#dddddd" align=right valign=center colspan=2>
   <html:link forward="top">メニュー</html:link> |
   <html:link forward="search">検索</html:link> |
   <html:link forward="list">全図書一覧</html:link> |
   <html:link page="/listTakingout.do">貸出図書一覧</html:link> |
   <html:link forward="add">新規図書登録</html:link> ||
   <logic:present scope="session" name="user">
    <html:link forward="logout">ログアウト</html:link>
   </logic:present>
   <logic:notPresent scope="session" name="user">
    <html:link forward="login">ログイン</html:link>
   </logic:notPresent>
  </TD>
 </TR>
</TABLE>
<!HR size=1 noshade>
<!-- END header -->