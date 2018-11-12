<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>View board detailed info</title>
  </head>
  <body>
    <center>
      <br>
      <html:form action="needLogin/admin/modifyBoard.do">
          <input type="hidden" name="method" value="modifyBoard">
          <input type="hidden" name="validate" value="yes">
          <table border="0" width="90%" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" rules="none">
	          <tr height="60"><td colspan="2" style="text-indent:5">■ Update board</td></tr>
    	      <tr height="50">
	                <td align="right" width="30%">Board ID:&nbsp;&nbsp;</td>
	                <td style="text-indent:5"><html:hidden property="boardId" write="true"/></td>
	          </tr>
              <tr>
                    <td align="right" width="30%">Category:&nbsp;&nbsp;</td>
                    <td>
			            <html:select property="boardClassID">
				            <html:optionsCollection name="backClassList" value="classId" label="className"/>
        			    </html:select>
			        </td>
	          </tr>	          
	          <tr height="50">
	                <td align="right">Board Name:&nbsp;&nbsp;</td>
    	            <td><html:text property="boardName" size="50"/></td>
    	      </tr>
    	      <tr>
	          		<td></td>
	          		<td><html:errors property="boardName"/></td>
	          </tr>
	          <tr height="50">
	                <td align="right">Board Manager:&nbsp;&nbsp;</td>
	                <td><html:text property="boardMaster" size="40"/></td>
	          </tr>
	          <tr>
	          		<td></td>
	          		<td><html:errors property="boardMaster"/></td>
	          </tr>
    	      <tr height="50">
	                <td align="right">Bulletin:&nbsp;&nbsp;</td>
	                <td><html:text property="boardPcard"  size="50"/></td>
	          </tr>
	          <tr>
	          		<td></td>
	          		<td><html:errors property="boardPcard"/></td>
	          </tr>
    	      <tr height="50">
	                <td>&nbsp;</td>
	                <td>
	                    <html:submit value="Submit"/>
	                    <html:reset value="Reset"/>
    	            </td>
	          </tr>	      	      
          </table>
      </html:form>
    </center>
  </body>
</html>