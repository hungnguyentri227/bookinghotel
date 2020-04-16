<%-- 
    Document   : detailsRoom
    Created on : Mar 21, 2020, 9:11:26 PM
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
        <title>Room Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ADMIN1}">
            <a href="LogoutServlet">Log out</a>
        </c:if>
        <c:if test="${empty sessionScope.ADMIN1}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <h1>Online Hotel Reversation</h1>
        <p>Welcome, <font color="red">${sessionScope.ADMIN1}</font></p>
        <a href="AdminController?btAction=Search&pageid=1">Customer</a> |
        <a href="AdminController?btAction=ViewUser&pageid=1">User</a> | 
        <a href="AdminController?btAction=ViewHotel&pageid=1">Detail Hotel</a> |
        <a href="AdminController?btAction=ViewRoom&pageid=1">Room Management</a> |
        <a href="bookinghistory.jsp">Booking History</a><br/>
        <h2>Room Management</h2>
        <div><font color="red">${requestScope.error}</font></div>
        <form action="Room_Insert_Controller">
            <table>
                <tr>
                    <td>Room Name:</td>
                    <td>
                        <input type="text" name="txtroomname" value="${param.txtroomname}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Floor:
                    </td>
                    <td>
                        <input type="number" min="0" name="txtfloor" value="${param.txtfloor}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price:
                    </td>
                    <td>
                        <input type="text" name="txtprice" value="${param.txtprice}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Hotel:
                    </td>
                    <td>
                        <select name="dropdownHotel">
                            <c:forEach items="${LIST2}" var="result">
                                <option value="${result.hotelid}">${result.hotelname} </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category:</td>
                    <td>
                        <select name="dropdownCategory">
                            <c:forEach items="${LIST3}" var="result">
                                <option value="${result.categoryID}">${result.categoryName} </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Create"/></td>
                </tr>
            </table>
        </form>

        <c:set var="result" value="${requestScope.LISTTABLE2}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.1</th>
                        <th>Room Name</th>
                        <th>Floor</th>
                        <th>Price</th>
                        <th>Hotel Name</th>
                        <th>Category Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="Room_Update_Servlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${dto.roomname}
                                <input type="hidden" name="txtroomname1" value="${dto.roomname}"/>
                            </td>
                            <td>
                                ${dto.floor}
                                <input type="hidden" name="txtfloor1" value="${dto.floor}"/>
                            </td>
                            <td>
                                <input type="text" name="txtprice1" value="${dto.price}"/>

                            </td>
                            <td>${dto.hotel_name}</td>
                            <td>${dto.categoryName}</td>
                            <td>
                                <c:url var="DeleteLink" value="Room_Delete_Controller">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtid" value="${dto.roomid}"/>
                                </c:url>
                                <a href="${DeleteLink}">Delete</a>|
                                <input type="hidden" name="txtid" value="${dto.roomid}"/>
                                <input type="submit" value="Update"/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

    </c:if>

    <c:if test="${numberpage == 1}">
        <nav>
            <ul class="pagination">
                <li class="page-item disabled">Previous</span></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=1" class="page-link">1</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=2" class="page-link">2</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=3" class="page-link">3</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=${numberpage + 1}" class="page-link">Next</a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${numberpage == maxpageid}">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=${numberpage - 1}" >Previous</span></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=1" class="page-link">1</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=2" class="page-link">2</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=3" class="page-link">3</a></li>
                <li class="page-item disabled">Next</a></li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${numberpage > 1 && numberpage < maxpageid}">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=${numberpage - 1}" >Previous</span></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=1" class="page-link">1</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=2" class="page-link">2</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=3" class="page-link">3</a></li>
                <li class="page-item"><a href="AdminController?btAction=ViewRoom&pageid=${numberpage + 1}" class="page-link">Next</a></li>
            </ul>
        </nav>
    </c:if>
</body>
</html>
