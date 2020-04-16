<%-- 
    Document   : room
    Created on : Mar 8, 2020, 10:55:31 AM
    Author     : hungn
--%>

<%@page import="hung.user.UserInfo"%>
<%@page import="hung.DTO.HotelDTO"%>
<%@page import="hung.DTO.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hung.DTO.UserDTO"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Page</title>

    </head>
    <body>
        <h1>Hotel Reservation</h1>
        <c:set var="userFaceInfo" value="${sessionScope.USERFACEBOOKINFO}" />
        <c:if test="${not empty userFaceInfo}">
            <font color="red"> Welcome , ${sessionScope.USERFACEBOOKINFO} </font> <br>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
            <a href="CustomerController?txtusername=${sessionScope.USER}">Information</a>
            <p><font color="red">Welcome, ${sessionScope.USER}</font></p>
            </c:if>  

        <c:if test="${not empty sessionScope.USER || not empty sessionScope.USERFACEBOOKINFO}">
            <a href="LogoutServlet">Log out</a>
        </c:if><br/>
        <c:if test="${empty sessionScope.USER && empty sessionScope.USERFACEBOOKINFO}">
            <a href="index.jsp">Login</a>
        </c:if><br/>

        <a href="hotel.jsp">Hotel</a> |
        <a href="room.jsp">Room</a> |
        <a href="cart.jsp">Order</a>



        <form action="SearchServlet" method="post">
            <input type="hidden" name="pageid" value="1"/>

            Country: <input type="text" name="txtcountry"  value="${param.txtcountry}"/>
            Date Begin: <input type="date" name="txtdatebegin" value="${param.txtdatebegin}"/>
            Date End: <input type="date" name="txtdateend" value="${param.txtdateend}"/>
            <input type="submit" value="Search"/>
        </form>
        <div><font color="red">${requestScope.error}</font></div><br/>

        <c:set var="searchValue" value="${param.txtcountry}" />
        <c:if test="${not empty searchValue}" >
            <c:set var="list" value="${requestScope.SEARCHHOTEL}" />
            <c:if test="${not empty list}">
                <table border="1" >
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Floor</td>
                        <td>Price</td>
                        <td>Action</td>
                    </tr>
                    <tbody>
                        <c:forEach var="dto" items="${list}">
                            <tr>
                                <td>
                                    ${dto.hotelid}
                                    <input type="hidden" name="txthotelid" value=" ${dto.hotelid}"/>
                                </td>
                                <td>${dto.hotelname}</td>
                                <td>${dto.address}</td>
                                <td>${dto.country_name}</td>
                                <td>
                                    <c:url var="urlWriting" value="ClickHotelServlet">
                                        <c:param name="txthotelid" value="${dto.hotelid}"/>
                                        <c:param name="pageid_room" value="1"/>
                                    </c:url>
                                    <a href="${urlWriting}">Click</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:if>
                <c:if test="${empty list}">
                    <h2>No record is matched!!!</h2>
                </c:if>
            </c:if>

            <c:if test="${not empty COUNTRYNAME}">
                <c:if test="${numberpage == 1}">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item disabled">Previous</span></li>
                            <li class="page-item"><a href="SearchServlet?pageid=1&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">1</a></li>
                            <li class="page-item"><a href="SearchServlet?pageid=2&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">2</a></li>
                            <li class="page-item"><a href="SearchServlet?pageid=${numberpage + 1}&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">Next</a></li>
                        </ul>
                    </nav>
                </c:if>
                <c:if test="${numberpage == maxpageid}">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item"><a href="SearchServlet?pageid=${numberpage - 1}&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" >Previous</span></li>
                            <li class="page-item"><a href="SearchServlet?pageid=1&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">1</a></li>
                            <li class="page-item"><a href="SearchServlet?pageid=2&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">2</a></li>
                            <li class="page-item disabled">Next</a></li>
                        </ul>
                    </nav>
                </c:if>
                <c:if test="${numberpage > 1 && numberpage < maxpageid}">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item"><a href="SearchServlet?pageid=${numberpage - 1}&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" >Previous</span></li>
                            <li class="page-item"><a href="SearchServlet?pageid=1&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">1</a></li>
                            <li class="page-item"><a href="SearchServlet?pageid=2&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">2</a></li>
                            <li class="page-item"><a href="SearchServlet?pageid=${numberpage + 1}&txtcountry=${COUNTRYNAME}&txtdatebegin=${DATEBEGIN}&txtdateend=${DATEEND}" class="page-link">Next</a></li>
                        </ul>
                    </nav>
                </c:if>
            </c:if>
    </body>
</html>
