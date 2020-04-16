<%-- 
    Document   : room
    Created on : Mar 9, 2020, 9:07:20 PM
    Author     : hungn
--%>

<%@page import="hung.user.UserInfo"%>
<%@page import="hung.DAO.DateObj"%>
<%@page import="hung.DAO.HotelDAO"%>
<%@page import="hung.DTO.HotelDTO"%>
<%@page import="hung.DTO.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER}">
            <a href="LogoutServlet">Log out</a>
        </c:if>
        <h1>Room Reversation</h1>
        <c:set var="userFaceInfo" value="${sessionScope.USERFACEBOOKINFO}" />
        <c:if test="${not empty userFaceInfo}">
            <font color="red"> Welcome , ${sessionScope.USERFACEBOOKINFO} </font> <br>
        </c:if>
        <c:set var="hoteldto" value="${requestScope.HOTEL}"/>
        <c:set var="date" value="${sessionScope.DATE}"/>
        Hotel: ${hoteldto.hotelname}<br/>
        DateBegin: ${date.datebegin}<br/>
        DateEnd: ${date.dateend}<br/>
        Count: ${date.count}<br/>
        ---------------------------------------<br/>

        <a href="hotel.jsp">Hotel</a> |
        <a href="room.jsp">Room</a> |
        <a href="cart.jsp">Order</a>

        <c:set var="dto" value="${requestScope.ROOM}"/>
        <c:if test="${not empty dto}">
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>room name</td>
                    <td>floor</td>
                    <td>price</td>
                    <td>Category</td>
                    <td>Action</td>
                </tr>
                <tbody>
                    <c:forEach var="listDTO" items="${dto}">
                    <form action="CartController">
                        <input type="hidden" name="txthotelid" value="${hoteldto.hotelid}"/>
                        <input type="hidden" name="txthotelname" value="${hoteldto.hotelname}"/>
                        <input type="hidden" name="txtdatebegin" value="${date.datebegin}"/>
                        <input type="hidden" name="txtdateend" value="${date.dateend}"/>
                        <input type="hidden" name="txtcount" value="${date.count}"/>
                        <input type="hidden" name="txtusername" value="${sessionScope.USER}"/>
                        <tr>
                            <td>${listDTO.roomid} <input type="hidden" name="txtroomid" value="${listDTO.roomid}"/></td>
                            <td>${listDTO.roomname} <input type="hidden" name="txtroomname" value="${listDTO.roomname}"/></td>
                            <td>${listDTO.floor} <input type="hidden" name="txtfoor" value="${listDTO.floor}"/></td>
                            <td>${listDTO.price} <input type="hidden" name="txtprice" value="${listDTO.price}"/></td>
                            <td>${listDTO.categoryName} <input type="hidden" name="txtcategoryname" value="${listDTO.categoryName}"/></td>
                        <input type="hidden" name="txttotal" value=" ${date.count * listDTO.price} "/>
                        <td><input type="submit" value="Add To Cart" name="btAction"/></td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${numberpage_room == 1}">
        <nav>
            <ul class="pagination">
                <li class="page-item disabled">Previous</span></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=1" class="page-link">1</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=2" class="page-link">2</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=3" class="page-link">3</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=${numberpage_room + 1}" class="page-link">Next</a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${numberpage_room == maxpageid_room}">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=${numberpage_room - 1}" >Previous</span></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=1" class="page-link">1</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=2" class="page-link">2</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=3" class="page-link">3</a></li>
                <li class="page-item disabled">Next</a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${numberpage_room > 1 && numberpage_room < maxpageid_room}">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=${numberpage_room - 1}" >Previous</span></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=1" class="page-link">1</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=2" class="page-link">2</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=3" class="page-link">3</a></li>
                <li class="page-item"><a href="ClickHotelServlet?txthotelid=${hoteldto.hotelid}&pageid_room=${numberpage_room + 1}" class="page-link">Next</a></li>
            </ul>
        </nav>
    </c:if>


</body>
</html>
