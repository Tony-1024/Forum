<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="struts-bean" prefix="bean" %>

<html>
<head><title>Pagination navigator</title></head>
<body>
    <table border="0" width="100%" cellspacing="0">
        <tr>
            <td width="40%" align="center"><bean:write name="page" property="pageInfo" filter="false"/></td>
            <td align="center" width="30%"><bean:write name="page" property="pageLink" filter="false"/></td>
            <form action="${param['gowhich']}" method="post">
            <td width="30%">
                Goto:
                <input type="text" name="showpage" size="7">
                <input type="submit" value="Change">
            </td>
            </form>
        </tr>
    </table>
</body>
</html>