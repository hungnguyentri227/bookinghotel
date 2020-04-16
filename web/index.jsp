<%-- 
    Document   : index
    Created on : Mar 8, 2020, 10:52:29 AM
    Author     : hungn
--%>

<%@page import="hung.dbutils.APIWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>LOGIN</h1>
        <form action="LoginServlet" method="post">
            <div><font color="red">${requestScope.error}</font></div>
            Username: <input type="text" name="txtusername" value=""/><br/>
            Password: <input type="password" name="txtpassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>
            <a href="<%= APIWrapper.getDialogLink()%>" >Login Facebook</a><br/>
            <a href="register.jsp">Register</a>
    </body>
</html>
