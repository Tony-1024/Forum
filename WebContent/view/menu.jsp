<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>

<%
    String[] weekdays={"","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String[] days=new String[42];
    for(int i=0;i<42;i++)
    	days[i]="";

    Date currentDay = new Date();
    int year=1900+currentDay.getYear();
    int month=currentDay.getMonth();
    int today=currentDay.getDate();
    int weekday=currentDay.getDay()+1;
    
    String now=(month+1)+"-"+today+"-"+year+" "+weekdays[weekday];
%>

<html>
  <head>
    <title>Header</title>
<!--     <script type="text/javascript">
        function showTime(showWhere){
            var now=new Date();
	        var hour=now.getHours();
	        var minu=now.getMinutes();
	        var sec=now.getSeconds();
         
            if(hour<10) hour="0"+hour;
         	if(minu<10) minu="0"+minu;
         	if(sec<10) sec="0"+sec;
         	
         	showWhere.value=hour+":"+minu+":"+sec;
            setTimeout("showTime(time)",1000)
        }
      </script> -->
  </head>
  <body bgcolor="#F0F0F0">
    <center>
      <table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top:1">
        <tr height="38">
            <!-- <td colspan="2" background="images/index/menu.jpg"> -->
            <td colspan="2">
              <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr height="50%" style="font-size:10pt">
                    <%-- <td align="center">
                        <%//=now %>
                        <input type="text" style="border:0;background-color:#F7F7F7" disable="true" id="time" size="9">
                    </td> --%>
                    <td align="center" width="8%"><a href="goIndex.do">Home Page</a></td>
                    <td align="center" width="8%"><a href="user/isUserLogin.do?method=isUserLogin">Sign In</a></td>
                    <td align="center" width="10%"><a href="needLogin/my/listShow.do?method=lookMyBbs&showpage=1">My Posts</a></td>
                    <td align="center" width="10%"><a href="user/goodListShow.do?method=goodListShow&showpage=1">Essence Posts</a></td>
                    <td align="center" width="10%"><a href="user/isAdminLogin.do?method=isAdminLogin">Backend</a></td>
                    <td align="center" width="8%"><a href="user/reg.do?method=userReg">Sign Up</a></td>
                    <td align="center" width="8%"><a href="user/logout.do?method=logout">Log Out</a></td>
                </tr>                
              </table>
            </td>
        </tr>
<%--         <tr height="40">
            <td width="529" align="center" background="images/index/indexP.jpg">                
                <marquee 
			      onmouseover=this.stop()
				  onmouseout=this.start()				  
                  scrollamount="1"
				  scrolldelay="10"
				  direction="left"
				  width="470">
				  <img src="images/index/pcard.gif"> Welcome to use Student Forum
		        </marquee>
            </td>
            <td align="center" width="271" background="images/index/messages.jpg">
	            <html:errors property="userOpR"/>
	            <html:errors property="sqlvalue"/>
            </td>
        </tr> --%>
        <html:form action="/user/search.do" method="post">
        <input type="hidden" name="validate" value="yes">
        <tr height="38">
            <td colspan="2" background="images/index/indexS.jpg" align="center">
                <table border="0" width="98%">
                    <tr>
                        <td width="25%">
                        	Welcome:
                        	<logic:notPresent name="logoner">
                        		Guest
                        	</logic:notPresent>
                        	<logic:present name="logoner">
	                        	${sessionScope.logoner.userName}
                        	</logic:present>
                        </td>
                        <td align="right">                                        
			                Condition:
            			    <html:select property="subsql">
			                    <html:options collection="searchSQL" property="value" labelProperty="label"/>
            			    </html:select>
			                Keyword:
            			    <html:text property="sqlvalue" size="30"/>
			                <%-- <html:radio property="searchType" value="like">Fuzzy</html:radio> --%>
			                <%-- <html:radio property="searchType" value="all">Precision</html:radio> --%>
			                <%-- <html:submit value=" " style="background-image:url('images/index/bs.jpg');width:49;height:21;border:0"/> --%>
               				<html:submit value="Search" style="width:49;height:21;border:0"/>
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