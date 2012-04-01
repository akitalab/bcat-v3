<%@ page pageEncoding="Shift_JIS" %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>

<!-- BEGIN bookEditorPart -->
<TABLE width='100%'>
 <logic:present name="editAll" scope="request">
  <TR>
   <TD align=right><B>図書ID : </B></TD>
   <TD>
    <html:text property="bookId" />
    <html:hidden property="beforeBookId" write="false" />
   </TD>
  </TR>
 </logic:present>
 <logic:notPresent name="editAll" scope="request">
  <html:hidden property="bookId" write="false" />
  <html:hidden property="beforeBookId" write="false" />
 </logic:notPresent>
 <TR>
  <TD align=right valign=top><B>ISBN : </B></TD>
  <TD><html:text property="code" />例 : ISBN1-2345-6789-X</TD>
 </TR>
 <TR>
  <TD align=right valign=top><B>タイトル*: </B></TD>
  <TD><html:text property="title" size="50" /></TD>
 </TR>
 <TR><TD></TD><TD><FONT color=red><html:errors property="title"/></FONT></TD></TR>
 <TR>
  <TD align=right valign=top><B>著者*: </B></TD>
  <TD><html:text property="author" size="50" /></TD>
 </TR>
 <TR><TD></TD><TD><FONT color=red><html:errors property="author"/></FONT></TD></TR>
 <TR>
  <TD align=right valign=top><B>訳者 : </B></TD>
  <TD><html:text property="translator" size="50" /></TD>
 </TR>
 <TR>
  <TD align=right valign=top><B>出版社*: </B></TD>
  <TD><html:text property="publisher" size="50" /></TD>
 </TR>
 <TR><TD></TD><TD><FONT color=red><html:errors property="publisher"/></FONT></TD></TR>
 <TR>
  <TD align=right valign=top><B>出版日*: </B></TD>
  <TD><html:text property="publicationDate" /> 例: 2003-04-05</TD>
 </TR>
 <TR><TD></TD><TD><FONT color=red><html:errors property="publicationDate"/></FONT></TD></TR>
 <logic:present name="editAll" scope="request">
  <TR>
   <TD align=right><B>登録者 : </B></TD>
   <TD>
    <html:text property="dataCreator" />
   </TD>
  </TR>
  <TR>
   <TD align=right><B>登録日 : </B></TD>
   <TD>
    <html:text property="dataCreatedDate" /> 例: 2001-12-22 12:34:56
   </TD>
  </TR>
  <TR>
   <TD align=right><B>状態 : </B></TD>
   <TD><html:text property="status" />空:保管中 out:貸出中 lost:紛失</TD>
  </TR>
 </logic:present>
 <logic:notPresent name="editAll" scope="request">
  <html:hidden property="dataCreator" write="false" />
  <html:hidden property="dataCreatedDate" write="false" />
  <html:hidden property="status" write="false" />
 </logic:notPresent>
 <TR>
  <TD nowrap align=right><B>キーワード : </B></TD>
  <TD><html:text property="keyword" size="50" /></TD>
 </TR>
 <TR>
  <TD nowrap align=right><B>備考 : </B></TD>
  <TD><html:textarea cols="50" rows="4" property="memo" /></TD>
 </TR>
</TABLE>
<P><FONT size="-1">*印の項目は必ず入力してください。</FONT></P>
<!-- END bookEditorPart -->
