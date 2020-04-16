<%-- 
    Document   : informationUser
    Created on : Mar 24, 2020, 6:48:54 PM
    Author     : hungn
--%>

<%@page import="hung.DAO.CustomerDAO"%>
<%@page import="hung.DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info Customer</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER1}">
            <a href="LogoutServlet">Log out</a>
        </c:if>
        <h1>Customer Reversation</h1>
        <c:if test="${not empty sessionScope.USER}">
            <% UserDTO result = (UserDTO) request.getAttribute("CUSTOMER1");%>
            Username: <%= result.getUsername()%><br/>
            Password: <%= result.getPassword()%><br/>
            Fullname: <%= result.getFullname()%><br/>
            <a href="changepassword.jsp">Change Password</a>
        </c:if>



    </body>
</html>
