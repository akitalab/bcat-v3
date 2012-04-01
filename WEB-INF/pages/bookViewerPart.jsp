<%@ page pageEncoding="Shift_JIS" %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>

<!-- BEGIN bookViewerPart -->
<FONT size=+1><bean:write scope='request' name='book' property='title' /></FONT>
<TABLE width='100%'>
 <TR>
  <TD align=right><b>著者 : </b></TD>
  <TD colspan=3><bean:write scope='request' name='book' property='author' /></TD>
 </TR>
 <TR>
  <TD align=right><b>訳者 : </b></TD>
  <TD colspan=3><bean:write scope='request' name='book' property='translator' /></TD>
 </TR>
 <TR>
  <TD align=right><b>出版社 : </b></TD>
  <TD><bean:write scope='request' name='book' property='publisher' /></TD>
 </TR>
 <TR>
  <TD align=right><b>出版日 : </b></TD>
  <TD><bean:write scope='request' name='book' property='publicationDate' /></TD>
 </TR>
 <TR>
  <TD nowrap align=right><b>キーワード : </b></TD>
  <TD colspan=3><bean:write scope='request' name='book' property='keyword' /></TD>
 </TR>
 <TR>
  <TD align=right><b>ISBN : </b></TD>
  <TD><bean:write scope='request' name='book' property='code' /></a></TD>
  <TD align=right nowrap><b>図書ID:</b></TD>
  <TD><bean:write scope='request' name='book' property='bookId' /></TD>
 </TR>
 <TR>
  <TD align=right><b>登録日 : </b></TD>
  <TD><bean:write scope='request' name='book' property='dataCreatedDate' />(<bean:write scope='request' name='book' property='dataCreator' />)</TD>
  <TD align=right><b>状態 : </b></TD>
  <TD>
   <logic:empty scope='request' name='book' property='status'>
    <FONT color=#000088>○</FONT>保管中
   </logic:empty>
   <logic:notEmpty scope='request' name='book' property='status'>
    <logic:equal scope='request' name='book' property='status' value='out'>
     <FONT color=#888800>！</FONT>貸出中
    </logic:equal>
    <logic:notEqual scope='request' name='book' property='status' value='out'>
     <FONT color=#880000>？</FONT><bean:write scope='request' name='book' property='status' />
    </logic:notEqual>
   </logic:notEmpty>
  </TD>
 </TR>
 <TR>
  <TD align=right valign=top><b>備考 :</b></TD>
  <TD colspan=3><PRE><bean:write scope='request' name='book' property='memo' /></PRE></TD>
 </TR>
 <TR>
  <TD align=left colspan=2 nowrap>
   <logic:notPresent name='withoutButtons' scope='request'>
    <logic:equal scope='request' name='book' property='status' value='out'>
     <logic:present name='withReturnBookButton' scope='request'>
      <html:form method='get' action='/return'>
       <html:submit value='この本を返却する' />
       <html:hidden property='step' value='prepare' />
       <input type='hidden' name='bookId' value="<bean:write scope='request' name='book' property='bookId' />" />
      </html:form>
     </logic:present>
    </logic:equal>
    <logic:empty scope='request' name='book' property='status'>
     <html:form method='get' action='/takeout'>
      <html:hidden property='step' value='prepare' />
      <input type='hidden' name='bookId' value="<bean:write scope='request' name='book' property='bookId' />" />
      <html:submit value='この本を借りる' />
     </html:form>
    </logic:empty>
   </TD>
   <TD align=right colspan=2 nowrap>
    <TABLE>
     <TR>
      <TD>
       <html:form method='get' action='/edit'>
        <INPUT type='hidden' name='bookId' value="<bean:write scope='request' name='book' property='bookId' filter='false' />" >
        <html:hidden property='step' value='input' />
        <html:submit value='編集' />
       </html:form>
      </TD>
      <TD>
       <html:form method='get' action='/delete'>
        <INPUT type='hidden' name='bookId' value="<bean:write scope='request' name='book' property='bookId' filter='false' />" >
        <html:hidden property='step' value='prepare' />
        <html:submit value='削除' />
       </html:form>
      </TD>
     </TR>
    </TABLE>
   </logic:notPresent>
  </TD>
 </TR>
</TABLE>
<!-- END bookViewerPart -->