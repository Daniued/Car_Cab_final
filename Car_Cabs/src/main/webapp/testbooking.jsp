<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <div class="container">
        <h1>Welcome, ${sessionScope.username} (Driver)</h1>
        <a href="logout.jsp" class="btn">Logout</a>
        
        <hr>
        
        <!-- Pending Ride Requests -->
        <section>
            <h2>Pending Ride Requests</h2>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>Request ID</th>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Passenger Count</th>
                    <th>Vehicle Type</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="request" items="${pendingRequests}">
                    <tr>
                        <td>${request.requestId}</td>
                        <td>${request.pickupLocation}</td>
                        <td>${request.dropoffLocation}</td>
                        <td>${request.bookingDate}</td>
                        <td>${request.time}</td>
                        <td>${request.passengerCount}</td>
                        <td>${request.vehicleType}</td>
                        <td>${request.totalAmount}</td>
                        <td>${request.status}</td>
                        <td>
                            <form action="DriverBookingServlet" method="post">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <button type="submit" name="action" value="accept" class="btn accept">Accept</button>
                                <button type="submit" name="action" value="reject" class="btn reject">Reject</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>

        <hr>
        
        <!-- Assigned Rides -->
        <section>
            <h2>Your Assigned Rides</h2>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>Booking ID</th>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <!-- Assigned rides data will be dynamically populated here -->
            </table>
        </section>

        <hr>
        
        <!-- Earnings Section -->
        <section>
            <h2>Total Earnings</h2>
            <p>Your total earnings:</p>
            <p class="earnings-amount">Rs. 0.00</p>
        </section>
    </div>
</body>
</html>
