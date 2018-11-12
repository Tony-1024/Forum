<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>Front end-view user's info</title>
  </head>
  <body>
    <center>
        <table border="1" width="70%" bgcolor="#F9F9F9" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" rules="none" style="margin-top:8">
	        <tr height="60">  <td colspan="2" style="text-indent:5">■ View User's Information</td></tr>
 	        <%-- <tr height="50">
 	        	<td align="right">User Profile:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
 	        	<td><img src="images/face/user/${bbsUserSingle.userFace}" style="border:1 solid;border-color:#E0E0E0"></td>
 	        </tr> --%>
 	        <tr height="50">
	            <td align="right">User Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	        <td><bean:write name="bbsUserSingle" property="userName"/></td>
   	        </tr>
   	        <tr height="50">
	            <td align="right">User Gender:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td><bean:write name="bbsUserSingle" property="userSex"/></td>
	        </tr>
   	        <tr height="50">
	            <td align="right">User Role:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td>
	                <logic:equal value="0" name="bbsUserSingle" property="userAble">User</logic:equal>
	                <logic:equal value="1" name="bbsUserSingle" property="userAble">Board Admin</logic:equal>
	                <logic:equal value="2" name="bbsUserSingle" property="userAble">System Admin</logic:equal>
	            </td>
	        </tr>
   	        <tr height="50">
	            <td align="right">Tele no.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td><bean:write name="bbsUserSingle" property="userPhone"/></td>
	        </tr>
	        <%-- <tr height="50">
	            <td align="right">Facebook:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td><bean:write name="bbsUserSingle" property="userFB"/></td>
	        </tr> --%>
   	        <tr height="50">
	            <td align="right">E-mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td><bean:write name="bbsUserSingle" property="userEmail"/></td>
	        </tr>
   	        <tr height="50">
	            <td align="right">From:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            <td><bean:write name="bbsUserSingle" property="userFrom"/></td>
	        </tr>
            <tr height="60"><td colspan="2" align="center" style="text-indent:20"><html:button property="back" value="Back" onclick="javascript:window.history.go(-1)"/></td></tr>      	      
        </table>
        <br>
    </center>
  </body>
</html>