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
    <title>Tips</title>
    <base href="<%=basePath%>">
    <link style="text/css" rel="stylesheet" href="css/style.css">    
  </head>
  <body bgcolor="#F9FDFF">
    <center>
        <table border="0" width="325" height="223" cellpadding="0" cellspacing="0" style="margin-top:150">
            <tr>
                <td align="center" background="images/login/mess.gif">
                    <html:errors/>
                    <logic:present name="message">
                        <bean:write name="message" filter="false"/>                    
                    </logic:present>
                    <html:errors property="getPasswd"/>
                    <br><br>
                    <a href="javascript:window.history.go(-1)">Back</a>
                </td>
            </tr>
        </table>
    </center>
  </body>
</html>