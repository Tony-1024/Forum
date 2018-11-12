<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*,com.yxq.actionform.UserForm" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>Show Post/Replies</title>
  </head>
  <body>
    <center>
      <!-- ****************show all the replies of the post**************** -->
      <html:link linkName="top"/>
      <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E0E0E0" bordercolordark="white" rules="all" style="margin-top:8;word-break:break-all">
          <tr height="30"><td colspan="3" background="images/index/classT.jpg" style="text-indent:5"><b><font color="white">■ Post</font></b></td></tr>
          <tr height="30" bgcolor="#F0F0F0">
              <td style="text-indent:5" width="27%">★ Publisher</td>
              <td colspan="2">Title: <bean:write name="bbsRootSingle" property="bbsTitle" filter="false"/></td>
          </tr>
          <tr bgcolor="#F9F9F9">
	          <!-- the publisher information -->
              <td rowspan="3" align="center" valign="top">
                  <table width="95%" height="180" border="0" cellspacing="0" cellpadding="0" style="margin-top:3">
                      <tr height="35%">
                          <%-- <td align="center" width="30%"><img src="images/face/user/${askUser.userFace}" style="border:1 solid;border-color:#E3E3E3"></td>  --%>
                          <td style="text-indent:10">
                              <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${sessionScope.bbsRootSingle.bbsSender}">
                                  <b><bean:write name="askUser" property="userName" filter="false"/></b>
                              </a>
                          </td>
                      </tr>
                      <tr>
                          <td align="center">Gender:</td>
                          <td style="text-indent:10">
                              <bean:write name="askUser" property="userSex"/>
                          </td>
                      </tr>
<%--                       <tr>
                          <td align="center">Facebook:</td>
                          <td style="text-indent:10">
                              <bean:write name="askUser" property="userFB"/>
                          </td>
                      </tr> --%>
                      <tr>
                          <td align="center">From:</td>
                          <td style="text-indent:10">
                              <bean:write name="askUser" property="subUserFrom[10]" filter="false"/>
                          </td>
                      </tr>
                      <!--the "return" hyperlink -->
                      <tr height="30"><td colspan="2" style="text-indent:70"><a href="${sessionScope.servletPath}?method=${sessionScope.method}">[Back to post list]</a></td></tr>
                  </table>
              </td>
              <!-- Replies info -->
              <%-- <td width="10%">Emoticon<img src="images/face/bbs/${sessionScope.bbsRootSingle.bbsFace}"></td> --%>
              <td height="30" align="right">Publish time: <bean:write name="bbsRootSingle" property="bbsSendTime"/>&nbsp;</td>
          </tr>
          <tr height="130"><td colspan="2" valign="top" style="padding-top:6;padding-left:6;padding-right:6;padding-bottom:6"><bean:write name="bbsRootSingle" property="bbsContent" scope="session" filter="false"/></td></tr>
          
          <!-- The hyperlink of operation for posts -->
          <tr height="30" bgcolor="#F9F9F9">
              <td align="right" colspan="2">
                  ◆<html:link href="view/indexTemp.jsp" anchor="answer">Reply this post&nbsp;</html:link>
                  
<%--                   <!-- If the post is not essence and top one -->
                  <logic:notEqual value="1" name="bbsRootSingle" property="bbsIsGood" scope="session">
                      <logic:notEqual value="1" name="bbsRootSingle" property="bbsIsTop">
                          <!--the hyperlink to make the post front-->
                          ◆<a href="needLogin/firstBbs.do?method=toFirstBbs&bbsId=${sessionScope.bbsRootSingle.bbsId}&bbsSender=${sessionScope.bbsRootSingle.bbsSender}" title="the post creater/board admin/admin">Move forward the post</a>&nbsp;
                      </logic:notEqual>
                  </logic:notEqual>--%>
                  
                  <!-- if the post is not topped post -->
                  <logic:notEqual value="1" name="bbsRootSingle" property="bbsIsTop">
					  <!-- show the hyperlink of topped post-->
       <%--   ◆<a href="needLogin/admin/doTopGood.do?method=setTopBbs&bbsId=${sessionScope.bbsRootSingle.bbsId}" title="Admin operation">Top Post</a>&nbsp; --%>
                  </logic:notEqual> 
                  
                  <!-- If the post is not essence-->
                  <logic:notEqual value="1" name="bbsRootSingle" property="bbsIsGood">
                      <!-- show the hyperlink of setting to be essence -->
                      ◆<a href="needLogin/admin/doTopGood.do?method=setGoodBbs&bbsId=${sessionScope.bbsRootSingle.bbsId}" title="Admin operation">Set to Essence</a>&nbsp;                  
                  </logic:notEqual> 
                                   
                   <!-- show the hyperlink of deleting the post-->
                  ◆<a href="needLogin/deleteRoot.do?method=deleteRootBbs&bbsId=${sessionScope.bbsRootSingle.bbsId}&bbsSender=${sessionScope.bbsRootSingle.bbsSender}" title="Post creater/Board admin/Admin Operation">Delete post</a>&nbsp;
              </td>
          </tr>
      </table>
      
      <!-- ****************Pagination navigator**************** -->
      <table  border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white">
          <tr height="30">
              <td align="right" background="images/index/boardE.jpg">
                  <jsp:include flush="true" page="/pages/page.jsp">
                      <jsp:param name="gowhich" value="user/openShow.do?method=openShow"/>
                  </jsp:include>
              </td>
          </tr>
      </table>
      
      <!-- ****************Show post replies**************** -->      
      <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" style="margin-top:2;word-break:break-all">
          <tr height="30"><td background="images/index/classT.jpg" style="text-indent:5"><b><font color="white">■ Replies</font></b></td></tr>
      </table>
      <!-- reply list doesn't exist -->
      <logic:notPresent name="answerbbslist" scope="session">
          <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="all">
              <tr height="70" bgcolor="#F9F9F9"><td align="center"><li>No replies so far!</li></td></tr>
          </table>
      </logic:notPresent>      
      <!-- if the reply list is existed -->
      <logic:present name="answerbbslist" scope="session">
      	  <!--when the reply list is empty -->
          <logic:empty name="answerbbslist" scope="session">
              <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="all">
                  <tr height="70" bgcolor="#F9F9F9"><td align="center"><li>No replies so far!</li></td></tr>
              </table>                        
          </logic:empty>
          <!-- when the reply list is not empty-->
          <logic:notEmpty name="answerbbslist" scope="session">
              <!-- Traversing the reply list of the post -->
              <logic:iterate id="answerbbsSingle" name="answerbbslist" indexId="idind">
              <bean:define id="answererName" name="answerbbsSingle" property="bbsAnswerSender"/>
              <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="all" style="word-break:break-all">
                  <tr height="25" bgcolor="#F0F0F0">
                      <td style="text-indent:5" width="27%">Reply-${(page.currentP-1)*page.perR+(idind+1)}</td>
                      <td colspan="2">Content:<bean:write name="answerbbsSingle" property="bbsAnswerTitle" filter="false"/></td>              
                  </tr>                  
                  <tr bgcolor="#F9F9F9">
	                  <!-- Replier info -->	                  
	                  
	                  <logic:iterate id="singleMap" name="answerMap">
	                      <logic:equal value="${answererName}" name="singleMap" property="key">
                              <bean:define id="answerSingle" name="singleMap" property="value"/>
	                      </logic:equal>
	                  </logic:iterate>
                      
                      <td rowspan="3" align="center" valign="top">
                          <table width="95%" height="150" border="0" cellspacing="0" cellpadding="0" style="margin-top:3">
                              <tr height="35%">
                                 <%--  <td align="center" width="30%"><img src="images/face/user/${answerSingle.userFace}" style="border:1 solid;border-color:#E0E0E0"></td> --%>
                                  <td style="text-indent:10">
                                      <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${answererName}">
                                          <b>${answerMap[answererName].userName}</b>
                                      </a>
                                  </td>
                              </tr>
                              <tr>
                                  <td align="right">Gender:</td>
                                  <td style="text-indent:10">
                                      ${answerMap[answererName].userSex}
                                  </td>
                              </tr>
<%--                               <tr>
                                  <td align="right">Facebook：</td>
                                  <td style="text-indent:10">
                                      ${answerMap[answererName].userFB}
                                  </td>
                              </tr> --%>
                              <tr>
                                  <td align="right">From:</td>
                                  <td style="text-indent:10">
                                      ${answerMap[answererName].userFrom}
                                  </td>
                              </tr>                              
                          </table>
                      </td>
                      <!-- reply the post -->
                      <%-- <td width="10%">Emoticon<img src="images/face/bbs/${answerbbsSingle.bbsFace}"></td> --%>
                      <td align="right" height="30">Reply time: <bean:write name="answerbbsSingle" property="bbsAnswerSendTime"/>&nbsp;</td>
                  </tr>
                  <tr height="130"><td colspan="2" valign="top" style="padding-top:6;padding-left:6;padding-right:6;padding-bottom:6"><bean:write name="answerbbsSingle" property="bbsAnswerContent" filter="false"/></td></tr>
                  <!-- Hyperlink of deleting post-->
                  <tr height="30" bgcolor="#F9F9F9">
                      <td align="right" colspan="2">
                          ◆<a href="needLogin/admin/deleteAnswer.do?method=deleteAnswerBbs&bbsId=${bbsRootSingle.bbsId}&delbbsAnswerId=${answerbbsSingle.bbsAnswerId}">Delete Reply</a>&nbsp;
                          ◆<html:link href="view/indexTemp.jsp" anchor="top">View Replies</html:link>&nbsp;
                      </td>            
                  </tr>        
              </table>
              <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white">
                  <tr height="15"><td background="images/index/boardE.jpg"></td></tr>
              </table>
              </logic:iterate>          
          </logic:notEmpty>
      </logic:present>
      
      <!-- ****************Pagination navigator**************** -->
      <table  border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white">
          <tr height="30">
              <td align="right" background="images/index/boardE.jpg">
                  <jsp:include flush="true" page="/pages/page.jsp">
                      <jsp:param name="gowhich" value="user/openShow.do?method=openShow"/>
                  </jsp:include>
              </td>
          </tr>
      </table>
      
      <!-- ****************Reply the post**************** -->
      <html:link linkName="answer"/>
      <table border="1" width="99%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="all">
      <html:form action="needLogin/answerBbs.do">
          <input type="hidden" name="method" value="answerBbs">
		  <tr bgcolor="#F0F0F0" height="30"><td colspan="2" style="text-indent:5" background="images/index/classT.jpg"><b><font color="white">■ Reply the post-<bean:write name="bbsRootSingle" property="bbsTitle" filter="false"/></font></b></td></tr>          
          <tr>
              <td width="27%" bgcolor="#F9F9F9" align="center" valign="top">
                  <table border="0" width="90%" height="100%">
                      <tr height="30%">
                          <td valign="top">
                              <table border="0" style="margin-top:10">
                                  <tr>
			                          <td><html:errors property="bbsAnswerTitle"/></td>
            			          </tr>
			                      <tr>
            			              <td><html:errors property="bbsAnswerContent"/></td>
			                      </tr>
                              </table>
                          </td>
                      </tr>
                      <tr>
                      	  <td valign="top">
                      	      <table border="0" width="90%" style="margin-top:10">
                      	          <tr><td>Tips:</td></tr>
                      	          <tr><td>Please make sure the content is related to the post!</td></tr>
                      	      </table>
                      	  </td>
                      </tr>
                  </table>
              </td>
              <td>
                  <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="rows">
                      <tr height="30">
                          <td width="15%" align="center">Theme:</td>
                          <td align="center"><html:text property="bbsAnswerTitle" size="77" maxlength="35"/></td>
                      </tr>
                      <%-- <tr bgcolor="#F9F9F9">
                          <td align="center">【Emoticon】</td>
                          <td><%@ include file="/pages/add/face.jsp" %></td>
                      </tr>
                      <tr height="30">
                          <td align="center">Content:</td>
                          <td align="center"><%@ include file="/pages/add/font.jsp"%></td>
                      </tr> --%>
                      <tr height="30" bgcolor="#F9F9F9">
                      	  <td align="center">Content:</td>
                          <td colspan="2" align="right"><%@ include file="/pages/add/count.jsp" %></td>
                      </tr>
                      <tr><td colspan="2" align="center"><html:textarea property="bbsAnswerContent" rows="15" cols="79" styleId="content" onkeydown="check(content,ContentUse,ContentRem,1000)" onkeyup="check(content,ContentUse,ContentRem,1000)" onchange="check(content,ContentUse,ContentRem,1000)"/></td></tr>
                      <tr height="30" align="center">
                          <td colspan="2">
                              <html:submit value="Submit"/>
                              <html:reset value="Reset"/>
                          </td>
                      </tr>
                  </table>
              </td>
          </tr>
      </html:form>
      </table>
    </center>
  </body>
</html>