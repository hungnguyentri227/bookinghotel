<%-- 
    Document   : detailsHotel
    Created on : Mar 21, 2020, 3:24:48 PM
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
        <title>Hotel Page</title>
    </head>
    <body>

        <h1>Online Hotel Reversation</h1>
        <a href="AdminController?btAction=Search&pageid=1">Customer</a> |
        <a href="AdminController?btAction=ViewUser&pageid=1">User</a> | 
        <a href="AdminController?btAction=ViewHotel&pageid=1">Detail Hotel</a> |
        <a href="AdminController?btAction=ViewRoom&pageid=1">Room Management</a> |
         <a href="bookinghistory.jsp">Booking History</a><br/>
        <h2>Hotel Management</h2>
         <p>Welcome, <font color="red">${sessionScope.ADMIN1}</font></p>
        <c:if test="${empty sessionScope.ADMIN1}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>

        <div><font color="red">${requestScope.error}</font></div>
        <form action="Hotel_Insert_Controller1">
            <table>
                <tr>
                    <td>Hotel Name:</td>
                    <td><input type="text" name="txthotelname" value="${param.txthotelname}" /></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="txtaddress" value="${param.txtaddress}" /></td>
                </tr>
                <tr>
                    <td>Country Name:</td>
                    <td>
                        <select name="dropdownCountry">
                            <c:forEach items="${LIST}" var="result">
                                <option value="${result.country_id}">${result.country_name} </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert" name="btAction"/></td>
                </tr>
            </table>            
        </form>

        <c:set var="result" value="${requestScope.LISTTABLE}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.1</th>
                        <th>Hotel Name</th>
                        <th>Address</th>
                        <th>Country Name</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="Hotel_Update_Controller">
                        <tr>
                            <td>${counter.count}</td>
                            <td>                    
                                <input type="text" name="txthotelname1" value="${dto.hotelname}"/>
                            </td>
                            <td>
                                <input type="text" name="txtaddress1" value=" ${dto.address}"/>
                            </td>
                            <td>
                                ${dto.country_name}
                                <input type="hidden" name="txtcountryname1" value=" ${dto.country_name}"/>
                            </td>
                            <td>
                                <c:url var="DeleteLink" value="Hotel_Delete_Controller">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtid" value="${dto.hotelid}"/>
                                </c:url>
                                <a href="${DeleteLink}">Delete</a>| 
                                <input type="hidden" name="txtid1" value="${dto.hotelid}"/>
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
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=1" class="page-link">1</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=2" class="page-link">2</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=3" class="page-link">3</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=${numberpage + 1}" class="page-link">Next</a></li>
                </ul>
            </nav>
        </c:if>
        <c:if test="${numberpage == maxpageid}">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=${numberpage - 1}" >Previous</span></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=1" class="page-link">1</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=2" class="page-link">2</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=3" class="page-link">3</a></li>
                    <li class="page-item disabled">Next</a></li>
                </ul>
            </nav>
        </c:if>
        <c:if test="${numberpage > 1 && numberpage < maxpageid}">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=${numberpage - 1}" >Previous</span></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=1" class="page-link">1</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=2" class="page-link">2</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=3" class="page-link">3</a></li>
                    <li class="page-item"><a href="AdminController?btAction=ViewHotel&pageid=${numberpage + 1}" class="page-link">Next</a></li>
                </ul>
            </nav>
        </c:if>


</body>
</html>
