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
    <title>Get Passwd</title>
    <base href="<%=basePath%>">
    <link style="text/css" rel="stylesheet" href="css/style.css">    
  </head>
  <body bgcolor="#F9FDFF">
    <center>
    <html:form action="/user/getpasswd.do" focus="userName">
    	<input type="hidden" name="method" value="getPasswd">
        <table border="0" width="325" height="223" cellpadding="0" cellspacing="0" style="margin-top:150">
            <tr>
                <td align="center">
                    <b>Input your username please, the system will send the password to your registed email, please check:</b><html:text property="userName" size="30"/><input type="submit" value="Submit">
                    <br><br>
                    <html:errors/>
                    <logic:present name="message">
                        <bean:write name="message" filter="false"/>                    
                    </logic:present>
                    <br><br><br>
                    <a href="<%=basePath%>pages/login.jsp">Back</a>
                </td>
            </tr>
        </table>
    </html:form>
    </center>
  </body>
</html>