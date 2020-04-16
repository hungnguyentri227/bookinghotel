<%-- 
    Document   : detailsCustomer
    Created on : Mar 20, 2020, 8:14:22 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
    </head>
    <body>

        <c:if test="${not empty sessionScope.ADMIN1}">
            <a href="LogoutServlet">Log out</a>
        </c:if>
        <c:if test="${empty sessionScope.ADMIN1}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <h1>Customer Reversation</h1>
        <p>Welcome, <font color="red">${sessionScope.ADMIN1}</font></p>
        <a href="AdminController?btAction=Search&pageid=1">Customer</a> |
        <a href="AdminController?btAction=ViewUser&pageid=1">User</a> | 
        <a href="AdminController?btAction=ViewHotel&pageid=1">Detail Hotel</a> |
        <a href="AdminController?btAction=ViewRoom&pageid=1">Room Management</a> |
        <a href="bookinghistory.jsp">Booking History</a><br/>
        ---------------------------------------
        <form action="AdminController" >
            <input type="hidden" name="pageid" value="1"/>
            Show Table: <input type="submit" value="Search" name="btAction"/>
        </form>

        <c:set var="result" value="${requestScope.ADMIN}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.1</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Fullname</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.username}</td>
                            <td>${dto.password}</td>
                            <td>${dto.fullname}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <c:if test="${not empty ADMIN}">
            <c:if test="${numberpage == 1}">
                <nav>
                    <ul class="pagination">
                        <li class="page-item disabled">Previous</span></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=1" class="page-link">1</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=2" class="page-link">2</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=3" class="page-link">3</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=${numberpage + 1}" class="page-link">Next</a></li>
                    </ul>
                </nav>
            </c:if>
            <c:if test="${numberpage == maxpageid}">
                <nav>
                    <ul class="pagination">
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=${numberpage - 1}" >Previous</span></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=1" class="page-link">1</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=2" class="page-link">2</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=3" class="page-link">3</a></li>
                        <li class="page-item disabled">Next</a></li>
                    </ul>
                </nav>
            </c:if>
            <c:if test="${numberpage > 1 && numberpage < maxpageid}">
                <nav>
                    <ul class="pagination">
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=${numberpage - 1}" >Previous</span></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=1" class="page-link">1</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=2" class="page-link">2</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=3" class="page-link">3</a></li>
                        <li class="page-item"><a href="AdminController?btAction=Search&pageid=${numberpage + 1}" class="page-link">Next</a></li>
                    </ul>
                </nav>
            </c:if>

        </c:if>
    </body>
</html>
