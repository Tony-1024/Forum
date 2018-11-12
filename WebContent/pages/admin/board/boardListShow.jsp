<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>View Board List</title>
  </head>
  <body>
    <center>
      <br>
      <html:form action="needLogin/admin/getBoardList.do">
      <input type="hidden" name="method" value="getBoardList">
	  <input type="hidden" name="type" value="show">
      <table border="0" width="580" cellspacing="0" cellpadding="0">
          <tr>
              <td align="right">
			          Select category:
			          <html:select property="classId">
			              <html:option value="-1">--Select Category--</html:option>
					      <html:optionsCollection name="backClassList" value="classId" label="className"/>
					  </html:select>
					  <html:submit value="Show"/>		          
			  </td>
	      </tr>
	  </table>
      </html:form>

      <table border="1" width="780" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="rows">
          <tr height="29"><td background="images/admin/backLT.jpg" colspan="5" style="text-indent:5"><b><font color="white">■ Board List</font></b></td></tr>	      
	      <tr height="30" bgcolor="#F0F0F0">
	          <td width="15%" align="center">Board ID</td>
	          <td width="40%" align="center">Board Name</td>
	          <td width="25%" align="center">Board Manager</td>	          
	          <td width="20%" align="center" colspan="2">Operation</td>
	      </tr>
          <logic:notPresent name="backBoardList">
              <tr height="80" align="center" bgcolor="#FBFBFB"><td colspan="5"><li>No Board to show!</li></td></tr> 
          </logic:notPresent>
          <logic:present name="backBoardList">
              <logic:notEmpty name="backBoardList">
              <tr>
                  <td colspan="5">
                  <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#F0F0F0" bordercolorlight="#F0F0F0" bordercolordark="white" rules="rows">              
                  <logic:iterate id="backBoardSingle" name="backBoardList">
                      <tr height="35" bgcolor="#FBFBFB">
                            <td width="15%" align="center">${backBoardSingle.boardId}</td>
                            <td width="40%" style="text-indent:5"><a href="needLogin/admin/modifyBoard.do?method=modifyBoard&boardId=${backBoardSingle.boardId}"><bean:write name="backBoardSingle" property="boardName" filter="false"/></a></td>
                            <td width="25%" align="center"><a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${backBoardSingle.boardMaster}"><bean:write name="backBoardSingle" property="boardMaster"/></a></td>
                            <td align="center"><a href="needLogin/admin/modifyBoard.do?method=modifyBoard&boardId=${backBoardSingle.boardId}">Update</a></td>
                            <td align="center"><a href="needLogin/admin/deleteBoard.do?method=deleteBoard&boardId=${backBoardSingle.boardId}" onclick="javaScript:return confirm('Are you sure to delete?')">Delete</a></td>
                      </tr>
                  </logic:iterate>
                  </table>
                  </td>
              </tr>
              </logic:notEmpty>
              <logic:empty name="backBoardList">
                  <tr height="80" align="center"><td colspan="5"><li>No Board to show!</li></td></tr> 
              </logic:empty>
          </logic:present>
	      <tr height="19" style="background-image:url('images/admin/backLE.jpg')"><td colspan="5"></td></tr>	                
      </table>
    </center>
  </body>
</html>