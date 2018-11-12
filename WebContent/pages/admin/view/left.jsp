<%@ page contentType="text/html; charset=utf-8"%>
<html>
  <head>
    <title>Left Menu</title>
  </head>
  <body>
    <center>
      <table border="1" width="100%" height="100%" cellspacing="0" cellpadding="0"">
        <!-- <tr height="99"><td align="center" colspan="2"><img src="images/admin/adminL.jpg"></td></tr> -->
<!--         <tr height="35">
              <td align="center"><a href="goIndex.do"><button>Back to Homepage</button></a></td>
              <td align="center"><a href="user/logout.do?method=logout"><button>Log Out</button></a></td>
        </tr> -->
        <!-- <tr height="33"><td colspan="2"><img src="images/admin/menuT.jpg"></td></tr>-->    
        <tr>
              <td align="center" valign="top" colspan="2">
                  <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" style="margin-top:1">
                    <tr><td>
                      <tr bgcolor="#F0F0F0" height="33">
			              <td style="text-indent:5" colspan="2"><b><font color="blue">■ Category Mgmt</font></b></td>
				      </tr>
				      <tr height="34">  <td style="text-indent:40" colspan="2"><a href="needLogin/admin/getClassList.do?method=getClassList">Edit Category</a></td></tr>
				      <tr height="34">  <td style="text-indent:40" colspan="2"><a href="needLogin/admin/addClass.do?method=addClass">Add Category</a></td></tr>
				   </td></tr>
				   </table>
              </td>
              <td align="center" valign="top" colspan="2">
                  <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" style="margin-top:1">
				      <tr bgcolor="#F0F0F0" height="33">
				            <td style="text-indent:5" colspan="2"><b><font color="blue">■ Board Mgmt</font></b></td>
				      </tr>
				      <tr height="34">  <td style="text-indent:40" colspan="2"><a href="needLogin/admin/getBoardList.do?method=getBoardList">Edit Board</a></td></tr>
				      <tr height="34">  <td style="text-indent:40" colspan="2"><a href="needLogin/admin/addBoard.do?method=addBoard">Add Board</a></td></tr>
                  </table>
              </td>
              <td align="center" valign="top" colspan="2">
                  <table border="1" width="100%" cellspacing="0" cellpadding="0" bordercolor="lightgrey" bordercolorlight="lightgrey" bordercolordark="white" style="margin-top:1">
				      <tr bgcolor="#F0F0F0" height="33">
				            <td style="text-indent:5" colspan="2"><b><font color="blue">■ User Mgmt</font></b></td>
				      </tr>
				      <tr height="34">  <td style="text-indent:40" colspan="2"><a href="needLogin/admin/getUserList.do?method=getUserList">Edit Users</a></td></tr>				      
                  </table>
              </td>

        </tr>
        
      </table>
    </center>
  </body>
</html>