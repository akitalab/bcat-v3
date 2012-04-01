<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<HTML>
<HEAD>
  <TITLE>図書検索</TITLE>
</HEAD>
<BODY>
  <jsp:include page="/WEB-INF/pages/header.jsp" flush="true" />
  <BLOCKQUOTE>
    <FONT color="red"><UL><html:errors/></UL></FONT>
    <TABLE align=center width=100% cellspacing=0 cellpadding=0 border=0>
      <TR><TD>
       <html:form action="/search" focus="word">
        <TABLE>
         <TR><TD nowrap align=right>検索範囲:</TD>
         <TD><html:select property="where">
          <html:option value="">全て</html:option>
          <html:option value="title">タイトル</html:option>
          <html:option value="author">著者</html:option>
          <html:option value="translator">訳者</html:option>
          <html:option value="keyword">キーワード</html:option>
          <html:option value="memo">備考</html:option>
          <html:option value="code">ISBN</html:option>
          <html:option value="bookid">図書ID</html:option>
         </html:select><BR></TD></TR>
         <TR><TD align=right>検索語:</TD>
         <TD>
          <html:text property="word" /><BR>
         </TD></TR>
         <TR><TD></TD><TD>
          <html:submit value="検索" />
         </TD></TR>
        </TABLE>
       </html:form>
      </TD><TD valign=top>
        <TABLE align=right cellspacing=3 border=0><TR><TD bgcolor="#cccccc">
          &nbsp;&nbsp;<b>検索方法について</b>
          <TABLE width=100% cellspacing=3 border=0><TR><TD bgcolor="#ffffff">
            <SMALL>
              ・アルファベットの大文字小文字は区別されません。<BR>
              ・ワイルドカード「%」（半角記号）が使用できます。<BR>
              ・半角カタカナ（JIS X 0201で規定される片仮名等）は使えません。<BR>
            </SMALL>
          </TD></TR></TABLE>
        </TD></TR></TABLE>
      </TD></TR>
    </TABLE>
  </BLOCKQUOTE>
  <jsp:include page="/WEB-INF/pages/footer.jsp" flush="true" />
</BODY>
</HTML>
