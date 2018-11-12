<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>Add new forum category</title>
  </head>
  <body>
    <center>
      <br>
      <table border="0" height="120" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" rules="all">
      <html:form action="needLogin/admin/addClass.do">
          <input type="hidden" name="method" value="addClass">
          <input type="hidden" name="validate" value="yes">
          <tr>
                 <td>Forum Name:</td>
                 <td><html:text property="className" size="50"></html:text>
          </tr>
          <tr>
                 <td></td>
                 <td><html:errors property="className"/></td>
          </tr>
          <tr>
                 <td>Forum Intro:</td>
                 <td><html:text property="classIntro" size="50"></html:text>
          </tr>
          <tr>
                 <td></td>
                 <td><html:errors property="classIntro"/></td>
          </tr>
          <tr>
                 <td></td>
                 <td>
                     <html:submit value="Submit"/>
                     <html:reset value="Reset"/>
                 </td>  
          </tr>
      </html:form>
      </table>
    </center>
  </body>
</html>