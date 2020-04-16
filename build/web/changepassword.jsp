<%-- 
    Document   : changepassword
    Created on : Mar 24, 2020, 7:26:16 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
        <c:if test="${not empty sessionScope.USER1}">
        <a href="LogoutServlet">Log out</a>
    </c:if>
    </head>
    <body>
        <h1>Customer Reversation</h1>
        
        <form action="Customer_ChangePass_Controller" method="post">
            <input type="hidden" name="txtusername" value="${sessionScope.USER}"/>
            <div><font color="red">${requestScope.error}</font></div>
            Password: <input type="password" name="txtpassword" value="${param.txtpassword}"/><br/>
            New Password: <input type="password" name="txtnewpass" value="${param.txtnewpass}"/><br/>
            Confirm Password: <input type="password" name="txtconfirm" value="${param.txtconfirm}" /><br/>
            <input type="submit" value="Update"/> |
            <a href="index.jsp">Turn back</a>
            
        </form>
    </body>
</html>
