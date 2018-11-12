<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String backMainPage=(String)session.getAttribute("backMainPage");
  if(backMainPage==null||backMainPage.equals(""))
	  backMainPage="default.jsp";
%>

<html>
  <head>
    <title>Admin Console</title>
    <base href="<%=basePath%>">
    <link style="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/bbs.js"></script>  
  </head>
  <body bgcolor="gray">
    <center>
      <table bgcolor="#F0F0F0" border="0" width="800" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white">
        <tr><td><jsp:include page="../../../view/top.jsp"/></td></tr>
        <tr><td><jsp:include page="../../../view/menu.jsp"/></td></tr>
        <tr><td align="left"><jsp:include page="left.jsp"/></td></tr>
        <tr><td align="right" width="70%"><jsp:include page="<%=backMainPage%>"/></td></tr>
        <tr><td align="left"><jsp:include page="../../../view/end.jsp"/></td></tr>
      </table>
    </center>
  </body>
</html>
