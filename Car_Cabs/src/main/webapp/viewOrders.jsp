<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Customer Orders</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <div class="container">
        <h1>Customer Orders</h1>
        <form method="get" action="ViewOrdersServlet">
            <label for="statusFilter">Filter by Status:</label>
            <select name="status" id="statusFilter">
                <option value="">All</option>
                <option value="Pending">Pending</option>
                <option value="Confirmed">Confirmed</option>
                <option value="Completed">Completed</option>
                <option value="Cancelled">Cancelled</option>
            </select>
            <button type="submit" class="btn">Apply Filter</button>
        </form>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Booking ID</th>
                <th>Customer</th>
                <th>Pickup Location</th>
                <th>Drop-off Location</th>
                <th>Date</th>
                <th>Time</th>
                <th>Vehicle Type</th>
                <th>Status</th>
                <th>Total Amount</th>
            </tr>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.bookingId}</td>
                    <td>${order.username}</td>
                    <td>${order.pickupLocation}</td>
                    <td>${order.dropoffLocation}</td>
                    <td>${order.bookingDate}</td>
                    <td>${order.time}</td>
                    <td>${order.vehicleType}</td>
                    <td>${order.status}</td>
                    <td>${order.totalAmount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
