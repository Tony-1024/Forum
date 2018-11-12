<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>Sign Up</title>
  </head>
  <body>
    <center>
          <table border="1" width="80%" bgcolor="#F9F9F9" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" rules="none" style="margin-top:5">
          <html:form action="user/reg.do">
          <input type="hidden" name="method" value="userReg">
          <input type="hidden" name="validate" value="yes">
	          <tr height="80">  <td colspan="5" style="text-indent:20">■User Sign Up</td></tr>
	          <tr height="45">
	                <td align="right" width="45%">User Name:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	            <td colspan="4"><html:text property="userName" size="40"/></td>
    	      </tr>
    	      <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userName"/></td>
    	      </tr>
	          <tr height="45">
	                <td align="right">Password:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="4"><html:password property="userPassword" size="40" redisplay="false"/></td>
	          </tr>
	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userPassword"/></td>
    	      </tr>
	          <tr height="45">
	                <td align="right">Confirm Password:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="3"><html:password property="aginPassword" size="40" redisplay="false"/></td>
	                <td rowspan="3" width="20%" valign="bottom" style="padding-bottom:10">	                    
	                   <!--  <img id="head" src="images/face/user/user0.gif" style="border:1 solid">    -->
	                </td>
	          </tr>
	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="aginPassword"/></td>
    	      </tr>
   	          <tr height="45">
	                <td align="right">Gender:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td>
	                    <html:radio property="userSex" value="male"/>Male
	                    <html:radio property="userSex" value="female"/>Female
	                </td>
	                <%-- <td width="15%" align="center">User Avatar:</td>
	                <td width="20%" align="center">
	                    <html:select property="userFace" onchange="showHead(this.options[this.selectedIndex].value)">
	                        <html:options collection="headFace" property="value" labelProperty="label" filter="false"/>
	                    </html:select>
	                </td> --%>
	          </tr>
   	          <tr height="45">
	                <td align="right">Telephone No:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="4"><html:text property="userPhone" size="40"/></td>
	          </tr>
	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userPhone"/></td>
    	      </tr>
<%-- 	          <tr height="45">
	                <td align="right">Facebook:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="4"><html:text property="userFB" size="40"/></td>
	          </tr> --%>
	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userFB"/></td>
    	      </tr>
   	          <tr height="45">
	                <td align="right">E-mail:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="4"><html:text property="userEmail" size="50"/></td>
	          </tr>
   	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userEmail"/></td>
    	      </tr>
   	          <tr height="45">
	                <td align="right">From:&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                <td colspan="4"><html:text property="userFrom" size="60"/></td>
	          </tr>
	          <tr>
    	      		<td></td>
    	      		<td colspan="4"><html:errors property="userFrom"/></td>
    	      </tr>
    	      <tr height="80">
	                <td>&nbsp;</td>
	                <td colspan="4">
	                    <html:submit value="Submit"/>
	                    <html:reset value="Reset"/>
    	            </td>
	          </tr>	      	      
          </html:form>
          </table>
          <br>
    </center>
  </body>
</html>