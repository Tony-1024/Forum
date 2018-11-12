<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>show search result</title>
  </head>
  <body>
    <center>
      <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#E3E3E3" bordercolorlight="#E3E3E3" bordercolordark="white" rules="rows" style="margin-top:8">
          <tr height="30"><td colspan="5" background="images/index/classT.jpg" style="text-indent:5"><b><font color="white">■ Search Post</font></b></td></tr>
          <tr height="30" align="center" bgcolor="#F0F0F0">
              <td width="7%">Status</td>
              <td width="35%">Post Title</td>
              <td width="8%">Replynum</td>
              <td width="25%">Publisher</td>
              <td width="25%">Last reply</td>
          </tr>
          <!-- searching post list is not existed-->
          <logic:notPresent name="searchlist" scope="session">
              <tr height="180"><td align="center" colspan="5" bgcolor="#F9F9F9"><li>Sorry, no matching post is found!</li></td></tr>
          </logic:notPresent>
          <!-- no match for post searching -->
          <logic:present name="searchlist" scope="session">
              <!-- the search list is empty -->
              <logic:empty name="searchlist" scope="session">
                  <tr height="180"><td align="center" colspan="5" bgcolor="#F9F9F9"><li>Sorry, no matching post is found!</li></td></tr>
              </logic:empty>
              <!--search post list is not empty-->
              <logic:notEmpty name="searchlist" scope="session">
			  <tr>
                  <td colspan="5">
                      <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#F0F0F0" bordercolorlight="#F0F0F0" bordercolordark="white" rules="all">                                    
	                  <!-- Traversing the post list-->
	                  <logic:iterate id="searchSingle" name="searchlist">
                      <tr height="30" bgcolor="#F9F9F9">
                          <td width="7%" align="center">
   	                          <logic:equal value="1" name="searchSingle" property="bbsIsGood">
                  	    		  Essence
	                  	      </logic:equal>
                        	  <logic:equal value="1" name="searchSingle" property="bbsIsTop">
      		                	  Top
                   		      </logic:equal> 
                   		      <logic:equal value="0" name="searchSingle" property="bbsIsGood">
                  	    		  <logic:equal value="0" name="searchSingle" property="bbsIsTop">
      		                	   Ordinary
                    		      </logic:equal> 
	                  	      </logic:equal>                          
                          </td>
                          <td width="35%" style="text-indent:10">
	                          <%-- <img src="images/face/bbs/${searchSingle.bbsFace}"> --%>
                              <a href="user/openShow.do?method=openShow&bbsId=${searchSingle.bbsId}"><bean:write name="searchSingle" property="subBbsTitle[18]" filter="false"/></a>
                          </td>
                          <td width="8%" align="center"><bean:write name="searchSingle" property="bbsAnswerNum"/></td>
                          <td width="25%" align="center">
                              <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${searchSingle.bbsSender}">                              
                                  <b><bean:write name="searchSingle" property="bbsSender" filter="false"/></b>
                                  <br><bean:write name="searchSingle" property="bbsSendTime"/>
                              </a>
                          </td>
                          <td width="25%" align="center">
                              <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${searchSingle.bbsLastUpdateUser}">                                                        
                                  <b><bean:write name="searchSingle" property="bbsLastUpdateUser" filter="false"/></b>
                                  <br><bean:write name="searchSingle" property="bbsLastUpdateTime"/>                                  
                              </a>
                          </td>
                      </tr>
                  	  </logic:iterate>                  
                  	  </table>
                  </td>
              </tr>
              </logic:notEmpty>
          </logic:present>
          
          <tr height="10"><td colspan="6"></td></tr>
          <tr height="30">          
              <td colspan="6" background="images/index/boardE.jpg">
                  <jsp:include flush="true" page="/pages/page.jsp">
                      <jsp:param name="gowhich" value="user/search.do"/>
                  </jsp:include>
              </td>
          </tr>
          <tr height="10"><td colspan="6"></td></tr>           
      </table>
    </center>
  </body>
</html>