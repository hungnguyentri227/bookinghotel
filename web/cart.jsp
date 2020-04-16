<%-- 
    Document   : cart
    Created on : Mar 8, 2020, 12:24:30 PM
    Author     : hungn
--%>

<%@page import="hung.dbutils.StringUtil"%>
<%@page import="hung.user.UserInfo"%>
<%@page import="hung.DAO.HotelDAO"%>
<%@page import="hung.DTO.HotelDTO"%>
<%@page import="hung.DAO.DateObj"%>
<%@page import="hung.DTO.BillDTO"%>
<%@page import="hung.DTO.ProductCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <c:if test="${not empty sessionScope.USER || not empty sessionScope.USERFACEBOOKINFO}">
        <a href="LogoutServlet">Log out</a>
    </c:if><br/>
    <c:set var="userFaceInfo" value="${sessionScope.USERFACEBOOKINFO}" />
    <c:if test="${not empty userFaceInfo}">
        <font color="red"> Welcome , ${sessionScope.USERFACEBOOKINFO} </font> <br>
    </c:if>

    <h1>Your Shopping Cart: </h1>

    <a href="hotel.jsp">Hotel</a> |
    <a href="room.jsp">Room</a> |
    <a href="cart.jsp">Order</a><br/>
    <c:if test="${not empty sessionScope.USER}">
        <p><font color="red">Welcome, ${sessionScope.USER}</font></p>
        </c:if>



    <c:set var="date" value="${sessionScope.DATE}"/>
    DateBegin: ${date.datebegin}<br/>
    DateEnd: ${date.dateend}


    <%
        ProductCart cart = (ProductCart) session.getAttribute("SHOP");
        if (cart != null) {
    %>
    <form action="BookingServlet">
        <input type="hidden" name="txtfaceID" value="${sessionScope.USERFACEBOOKINFO}"/>
        <input type="hidden" name="txtusername" value="${sessionScope.USER}"/>
        <table border="1">
            <tr>
                <td>No.</td>
                <td>Bill ID</td>
                <td>Hotel Name</td>
                <td>Room</td>
                <td>Category</td>
                <td>Price</td>
                <td>Amount</td>
                <td>Total</td>
                <td>Action</td>
            </tr>
            <%
                int count = 0;
                double alltotal = 0;
                for (BillDTO dto : cart.getCart().values()) {
                    alltotal += dto.getPrice() * dto.getAmount();
                    String url = "CartController?btAction=Delete&txtbillid=" + dto.getBillID();
            %>
            <tr>

                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%= dto.getBillID()%>
                    <input type="hidden" name="txtbillid" value="<%= dto.getBillID()%>"/>
                </td>
                <td>
                    <%= dto.getHotelName()%>
                    <input type="hidden" name="txthotelname" value="<%= dto.getHotelName()%>"/>
                </td>
                <td>
                    <%= dto.getRoom()%>
                    <input type="hidden" name="txtroomname" value="<%= dto.getRoom()%>"/>
                </td>
                <td>
                    <%= dto.getCategoryName()%>
                    <input type="hidden" name="txtcategoryname" value="<%= dto.getCategoryName()%>"/>
                </td>
                <td>
                    <%= dto.getPrice()%>
                    <input type="hidden" name="txtprice" value="<%= dto.getPrice()%>"/>

                </td>
                <td>
                    <%= dto.getAmount()%>
                    <input type="hidden" name="txtamount" value="<%= dto.getAmount()%>"/>
                </td>
                <td>
                    <%= dto.getPrice() * dto.getAmount()%>
                    <input type="hidden" name="txttotalprice" value="<%= dto.getPrice() * dto.getAmount()%>"/>
                </td>
                <td>

                    <a href="<%=url%>">Delete</a>

                </td>
            </tr>

            <%                }
            %>

        </table>
        <h1> Total: <%= alltotal%></h1>

        <%
            }
        %>
        <input type="submit" value="Book" />
    </form>

    <form action="Cart_Addmore_Controller">
        <input type="hidden" name="txtaddmoreid" value="${sessionScope.HOTELID}"/>
        <input type="submit" value="Add More"/>
    </form>
</html>
