<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>Log in</title>
    <base href="<%=basePath%>">
    <link style="text/css" rel="stylesheet" href="css/style.css">
  </head>
  <body bgcolor="#FFFFFF">
    <center>
      <html:form action="user/login.do" focus="userName">
          <input type="hidden" name="method" value="login">
          <table background="images/login/login_JB.jpg" width="500" height="300" border="0" cellspacing="0" cellpadding="0" style="margin-top:90">
              <tr height="175">
                  <td colspan="2" style="text-indent:65;color:red">
                      <html:errors/>
                      <logic:present name="message">
                          <bean:write name="message" filter="false"/>
                      </logic:present>
                  </td>
              </tr>
              <tr height="45">
                  <td align="right" width="30%">User Name:</td>
                  <td>&nbsp;&nbsp;<html:text property="userName" size="30"/>&nbsp;&nbsp;<a href="user/reg.do?method=userReg">[Sign up]</a></td>
              </tr>
              <tr height="30">
                  <td align="right">Password:</td>
                  <td>&nbsp;&nbsp;<html:password property="userPassword" redisplay="false" size="30"/>&nbsp;&nbsp;<a href="index.jsp">[Use as guest]</a></td>
              </tr>
              <tr>
                  <td></td>
                  <td>
                      &nbsp;
                      <html:image src="images/login/login.gif"/>&nbsp;&nbsp;<a href="<%=basePath%>pages/getPasswd.jsp">Forget Password?</a>
                  </td>
              </tr>
          </table>
          <table width="500" border="0" width="350" cellspacing="0" cellpadding="0">
              <tr><td colspan="2">
          </table>
      </html:form>
    </center>
  </body>
</html>