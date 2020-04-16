<%-- 
Document   : bookinghistory
Created on : Mar 14, 2020, 10:36:35 AM
Author     : hungn
--%>

<%@page import="hung.DTO.UserDTO"%>
<%@page import="hung.DTO.BillDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History</title>
    </head>

    <h1>Booking History</h1>
    <c:if test="${not empty sessionScope.ADMIN1}">
        <a href="LogoutServlet">Log out</a>
    </c:if>
    <h1><font color="red">Welcome, ${sessionScope.ADMIN1}</font></h1>
    <a href="AdminController?btAction=Search&pageid=1">Customer</a> |
        <a href="AdminController?btAction=ViewUser&pageid=1">User</a> | 
        <a href="AdminController?btAction=ViewHotel&pageid=1">Detail Hotel</a> |
        <a href="AdminController?btAction=ViewRoom&pageid=1">Room Management</a> |
        <a href="bookinghistory.jsp">Booking History</a><br/>
    <form action="BookingHistoryServlet">
        <input type="hidden" name="pageid" value="1"/>
        Booking Date: <input type="date" name="txtdate" value="${param.txtdate}"/>
        <input type="submit" value="Search"/>
    </form>

    <c:set var="dto" value="${requestScope.HISTORY}"/>
    <c:if test="${not empty dto}">
        <table border="1">
            <tr>
                <td>No.</td>
                <td>User Name</td>
                <td>Hotel Name</td>
                <td>Room Name</td>
                <td>Category Name</td>
                <td>Total Price</td>
            </tr>
            <tbody>
                <c:forEach var="elem" items="${dto}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${elem.username}</td>
                        <td>${elem.hotelName}</td>
                        <td>${elem.room}</td>
                        <td>${elem.categoryName}</td>
                        <td>${elem.totalprice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    


    <c:if test="${not empty HISTORY}">
        <c:if test="${numberpage == 1}">
            <nav>
                <ul class="pagination">
                    <li class="page-item disabled">Previous</span></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=1&txtdate=${DATE}" class="page-link">1</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=2&txtdate=${DATE}" class="page-link">2</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=3&txtdate=${DATE}" class="page-link">3</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=${numberpage + 1}&txtdate=${DATE}" class="page-link">Next</a></li>
                </ul>
            </nav>
        </c:if>
        <c:if test="${numberpage == maxpageid}">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=${numberpage - 1}&txtdate=${DATE}" >Previous</span></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=1&txtdate=${DATE}" class="page-link">1</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=2&txtdate=${DATE}" class="page-link">2</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=3&txtdate=${DATE}" class="page-link">3</a></li>
                    <li class="page-item disabled">Next</a></li>
                </ul>
            </nav>
        </c:if>
        <c:if test="${numberpage > 1 && numberpage < maxpageid}">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=${numberpage - 1}&txtdate=${DATE}" >Previous</span></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=1&txtdate=${DATE}" class="page-link">1</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=2&txtdate=${DATE}" class="page-link">2</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=3&txtdate=${DATE}" class="page-link">3</a></li>
                    <li class="page-item"><a href="BookingHistoryServlet?pageid=${numberpage + 1}&txtdate=${DATE}" class="page-link">Next</a></li>
                </ul>
            </nav>
        </c:if>

    </c:if>
</html>
