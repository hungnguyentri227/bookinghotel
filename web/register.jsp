<%-- 
    Document   : register
    Created on : Mar 14, 2020, 9:35:05 AM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register User</title>
    </head>
    <body>
        <h1>Register User</h1>
        <form action="RegisterServlet" method="post">
            <div><font color="red">${requestScope.error}</font></div>
            Username: <input type="text" name="txtusername" value="${param.txtusername}"/><br/>
            Password: <input type="password" name="txtpassword" value="" /><br/>
            Confirm: <input type="password" name="txtconfirm" value="" /><br/>
            Fullname: <input type="text" name="txtfullname" value="${param.txtfullname}" /><br/>
            <input type="submit" value="Register" />
            </form>
    </body>
</html>
