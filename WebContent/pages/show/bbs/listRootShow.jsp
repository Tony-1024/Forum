<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<html>
  <head>
    <title>Display all posts of a category</title>
  </head>
  <body>
    <center>
      <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#E0E0E0" bordercolorlight="#E3E3E3" bordercolordark="white" rules="rows" style="margin-top:8">          
          <tr height="45" bgcolor="#F9F9F9">
              <!-- notice of this category -->
              <td colspan="4" style="text-indent:10">
                  Notice:<bean:write name="boardPcard" filter="false"/>
              </td>
              <!-- The hyperlink of creating a new post -->
              <td colspan="2" align="center"><a href="needLogin/addBbs.do?method=addBbs">[Create A New Post]</a></td>
          </tr>
          
          <!-- Change Topic within the same category -->
          <tr height="30" bgcolor="#F9F9F9">
              <html:form action="user/jumpBoard.do">              
              <td colspan="6" align="right">
                  <input type="hidden" name="method" value="rootListShow">
                  Change Topic:
                  <html:select property="boardId">
                      <html:option value="">-Select Topic--</html:option>
                      <html:options collection="jumpBoard" property="value" labelProperty="label"/>
                  </html:select>
                  <html:submit value="Goto"/>
                  &nbsp;
              </td>
              </html:form>
          </tr>
          
          <!-- ****************Display the top post**************** -->
<!--           <tr height="30"><td colspan="6" style="text-indent:5" background="images/index/classT.jpg"><b><font color="white">■ Top Post</font></b></td></tr>
          <tr height="30" align="center">
              <td width="8%">Status</td>
              <td width="35%">Title</td>
              <td width="7%">Replynum</td>
              <td width="22%">Publisher</td>
              <td width="28%" colspan="2">Last Reply</td>
          </tr> -->
          <!-- No top post -->
<%--           <logic:notPresent name="topbbslist" scope="session">
              <tr height="70"><td bgcolor="#F9F9F9" align="center" colspan="6"><font color="red"><li>No top post!</li></font></td></tr>
          </logic:notPresent>
          <!-- top post exist -->
          <logic:present name="topbbslist" scope="session">
	          <!-- the list of top post is empty-->
              <logic:empty name="topbbslist" scope="session">
                  <tr height="70"><td bgcolor="#F9F9F9" align="center" colspan="6"><font color="red"><li>No top post!</li></font></td></tr>
              </logic:empty>
              <!--the list of top post is not empty-->
              <logic:notEmpty name="topbbslist" scope="session">
                  <tr>
                      <td colspan="6">
                          <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#F0F0F0" bordercolorlight="#F0F0F0" bordercolordark="white" rules="all">
                              <!-- Traversing the list of top post -->
                              <logic:iterate id="topBbsSingle" name="topbbslist">
                                  <tr height="35" style="text-indent:5" bgcolor="#FBFBFB">
                       			      <td width="8%" align="center">Top</td>
			                          <td width="35%" style="text-indent:10">                             
            			                  <img src="images/face/bbs/${topBbsSingle.bbsFace}">
                        			      <a href="user/openShow.do?method=openShow&showpage=1&bbsId=${topBbsSingle.bbsId}"><bean:write name="topBbsSingle" property="subBbsTitle[18]" filter="false"/></a>
			                          </td>
            			              <td width="7%" align="center"><bean:write name="topBbsSingle" property="bbsAnswerNum"/></td>
                        			  <td width="22%" align="center">
			                              <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${topBbsSingle.bbsSender}">
            			                      <b><bean:write name="topBbsSingle" property="bbsSender" filter="false"/></b>
                        			          <br><bean:write name="topBbsSingle" property="bbsSendTime"/>
                			              </a>
			                          </td>
            			              <td width="28%" align="center" colspan="2">
                        			      <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${topBbsSingle.bbsLastUpdateUser}">
		                            	      <b><bean:write name="topBbsSingle" property="bbsLastUpdateUser" filter="false"/></b>
        		                        	  <br><bean:write name="topBbsSingle" property="bbsLastUpdateTime"/>
                        			      </a>
    			                      </td>                          
                			      </tr>
			                  </logic:iterate>
			              </table>
			          </td>
			      </tr>              
              </logic:notEmpty>
          </logic:present>
          <tr height="30"><td colspan="6" align="right" background="images/index/boardE.jpg"></td></tr>
  --%>         
          <!-- ****************Display the ordinary posts**************** -->
          <tr height="30"><td colspan="6" style="text-indent:5" background="images/index/classT.jpg"><b><font color="white">■ Posts</font></b></td></tr>
          <tr height="30" align="center" bgcolor="#F4F4F4">
              <td width="8%">Status</td>
              <td width="35%">Title</td>
              <td width="7%">Replynum</td>
              <td width="22%">Publisher</td>
              <td width="22%">Last Reply</td>
              <td width="6%">Operation</td>
          </tr>
          <!-- Ordinary posts are not exist -->
          <logic:notPresent name="otherbbslist" scope="session">
              <tr height="180"><td bgcolor="#F9F9F9" align="center" colspan="6"><font color="red"><li>Sorry, post list is not exist!</li></font></td></tr>
          </logic:notPresent>
          <!-- when post list is exist!-->
          <logic:present name="otherbbslist" scope="session">
              <!-- post list is empty -->
              <logic:empty name="otherbbslist" scope="session">
                  <tr height="180"><td bgcolor="#F9F9F9" align="center" colspan="6"><font color="red"><li>Sorry, post list is not exist!</li></font></td></tr>
              </logic:empty>
              <!-- ordinary post list is not empty-->
              <logic:notEmpty name="otherbbslist" scope="session">
                  <tr>
                      <td colspan="6">
                          <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="#F0F0F0" bordercolorlight="#F0F0F0" bordercolordark="white" rules="all">
			 	              <!-- Traverse the post list-->
                  		      <logic:iterate id="otherBbsSingle" name="otherbbslist">
			                      <tr height="35" bgcolor="#F9F9F9">
            			              <!-- Display the post status -->
            			              <td align="center" width="8%">
                        			      <logic:equal value="1" name="otherBbsSingle" property="bbsIsGood">
                            	    		  Essence
		                        	      </logic:equal>
        		                    	  <logic:equal value="0" name="otherBbsSingle" property="bbsIsGood">
                		                	  Ordinary
	                        		      </logic:equal>                              
			                          </td>
			                          <!-- Display the post title -->
            			              <td style="text-indent:10" width="35%">
                        			     <%--  <img src="images/face/bbs/${otherBbsSingle.bbsFace}"> --%>
			                              <a href="user/openShow.do?method=openShow&showpage=1&bbsId=${otherBbsSingle.bbsId}"><bean:write name="otherBbsSingle" property="subBbsTitle[18]" filter="false"/></a>
            			              </td>
            			              <!-- Display the post replynum -->
    			                      <td align="center" width="7%"><bean:write name="otherBbsSingle" property="bbsAnswerNum"/></td>
                			          <!-- Display the post publisher-->
                			          <td align="center" width="22%">
                            			  <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${otherBbsSingle.bbsSender}">
			                                  <b><bean:write name="otherBbsSingle" property="bbsSender" filter="false"/></b>
        		                        	  <br><bean:write name="otherBbsSingle" property="bbsSendTime"/>
                        			      </a>
    			                      </td>
                			          <!-- the last replier -->
                			          <td align="center" width="22%">
                            			  <a href="bbs/user/getUserSingle.do?method=getUserSingle&userName=${otherBbsSingle.bbsLastUpdateUser}">
			                                  <b><bean:write name="otherBbsSingle" property="bbsLastUpdateUser" filter="false"/></b>
            			                      <br><bean:write name="otherBbsSingle" property="bbsLastUpdateTime"/>
                        			      </a>
			                          </td>
			                          <!-- delete the hyperlink-->
            			              <td align="center" width="6%"><a href="needLogin/deleteRoot.do?method=deleteRootBbs&bbsId=${otherBbsSingle.bbsId}&bbsSender=${otherBbsSingle.bbsSender}" title="Publisher/Administrator operation" onclick="javaScript:return confirm('Are you sure to delete the post?')">delete</a></td>
			                      </tr>
            			      </logic:iterate>
		                  </table>
	                  </td>
	              </tr>                  
              </logic:notEmpty>
          </logic:present>          

          <tr height="10" bgcolor="#F4F4F4"><td colspan="6" align="right"></td></tr>                    
          <!-- ****************Pagination navigation**************** -->          
          <tr height="30">
              <td colspan="6" align="right" background="images/index/boardE.jpg">
                  <jsp:include flush="true" page="/pages/page.jsp">
                      <jsp:param name="gowhich" value="user/listShow.do?method=rootListShow"/>
                  </jsp:include>
              </td>
          </tr>          
          <tr height="10" bgcolor="#F4F4F4"><td colspan="6" align="right"></td></tr>          
      </table>
    </center>
  </body>
</html>