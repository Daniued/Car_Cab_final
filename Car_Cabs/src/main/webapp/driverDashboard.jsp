<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
 <style>
        .section {
            display: none; /* Hide sections by default */
            padding: 20px;
            border: 1px solid #ccc;
            margin-top: 10px;
        }
    </style>
<body style="background-image: url('img/img2.png');  background-repeat: no-repeat;
  background-attachment: fixed;
    background-size: 100% 100%;
">
    <div class="container">
        <h1>Welcome, ${sessionScope.username} (Driver)</h1>
         <a href="LogoutServlet" class="btn">Logout</a>
        <button onclick="window.location.href='TestBookingServlet'" class="btn">Refresh</button>
        <button onclick="showSection('section1')">Available Ride</button>
   
    <button onclick="showSection('section3')">History</button>
    <button onclick="showSection('section4')">Earnings</button>
        <hr>

        <!-- Pending Rides Section -->
        <section id="section1" class="section">
            <h1>Driver Dashboard - Pending Ride Requests</h1>
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
                <td>${request.bookingId}</td>
                <td>${request.pickupLocation}</td>
                <td>${request.dropoffLocation}</td>
                <td>${request.bookingDate}</td>
                <td>${request.time}</td>
                <td>${request.passengerCount}</td>
                <td>${request.vehicleType}</td>
                <td>${request.totalAmount}</td>
                <td>${request.status}</td>
                <td>
                   <form action="<%= request.getContextPath() %>/BookingConfirmationServlet"  method="post">
    <input type="hidden" name="requestId" value="${request.bookingId}">
    <button type="submit" name="action" value="accept" class="btn accept">Accept</button>
    <button type="submit" name="action" value="reject" class="btn reject">Reject</button>
</form>

                </td>
            </tr>
        </c:forEach>
    </table>
        </section>

      

        <!-- Assigned Rides Section -->
        <section id="section2" class="section">
            <h2>Your Assigned Rides</h2>
            <table border="1">
                <tr>
                    <th>Booking ID</th>
                    <th>Pickup</th>
                    <th>Drop-off</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <!-- Sample Data for Assigned Rides -->
                <c:forEach var="booking" items="${assignedRides}">
                    <tr>
                        <td>${booking.booking_id}</td>
                        <td>${booking.pickup_location}</td>
                        <td>${booking.drop_location}</td>
                        <td>${booking.booking_date}</td>
                        <td>${booking.time}</td>
                        <td>${booking.status}</td>
                        <td>
                            <form action="<%= request.getContextPath() %>/DriverRideServlet"  method="post" style="display:inline;">
                                <input type="hidden" name="booking_id" value="${booking.booking_id}" />
                                <button type="submit" name="action" value="start" class="btn">Start Ride</button>
                                <button type="submit" name="action" value="complete" class="btn">Complete Ride</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>

   

<!-- Ride History Section -->
<section id="section3" class="section">
    <h2>Ride History</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Booking ID</th>
            <th>Pickup Location</th>
            <th>Drop-off Location</th>
            <th>Date</th>
            <th>Time</th>
            <th>Vehicle Type</th>
            <th>Status</th>
            <th>Total Amount</th>
        </tr>
        <c:forEach var="ride" items="${rideHistory}">
            <tr>
               <td>${ride.bookingId}</td>
                <td>${ride.pickupLocation}</td>
                <td>${ride.dropoffLocation}</td>
                <td>${ride.bookingDate}</td>
                <td>${ride.time}</td>
                <td>${ride.vehicleType}</td>
                <td>${ride.status}</td>
                <td>${ride.totalAmount}</td>
            </tr>
        </c:forEach>
    </table>
</section>

<!-- Total Payment Section -->
<section id="section4" class="section">
    <h2>Total Ride Payments</h2>
    <p>Total Earnings: ${totalEarnings}</p>
</section>


    </div>
     <script>
        function showSection(sectionId) {
            // Hide all sections
            document.querySelectorAll('.section').forEach(section => {
                section.style.display = 'none';
            });

            // Show the selected section
            document.getElementById(sectionId).style.display = 'block';
        }
    </script>
</body>
</html>
