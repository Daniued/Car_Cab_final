<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles/style.css">
    <script>
        function showSection(sectionId) {
            var sections = document.getElementsByClassName("admin-section");
            for (var i = 0; i < sections.length; i++) {
                sections[i].style.display = "none";
            }
            document.getElementById(sectionId).style.display = "block";
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <a href="LogoutServlet" class="btn">Logout</a>
        <hr>
        <nav>
           
            <ul>
    <li><a class="btn" onclick="showSection('manageCustomers')">View All Customers</a></li>
    <li><a class="btn" onclick="showSection('manageDrivers')">View All Drivers</a></li>
    <li><a class="btn" onclick="showSection('viewRevenue')">View Total Revenue</a></li>
    <li><a class="btn" onclick="showSection('viewOrders')">View Customer Orders</a></li>
   
    <li><button onclick="location.reload();" class="btn">Refresh Dashboard</button></li>
</ul>
            
        </nav>
        <hr>
        <!-- Sections to load dynamically -->
        <div id="manageCustomers" class="admin-section" style="display:none">
            <h2>Manage Customer Accounts</h2>
            <iframe src="ViewCustomersServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>

        <div id="manageDrivers" class="admin-section" style="display:none">
            <h2>Manage Driver Accounts</h2>
            <iframe src="ViewDriversServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>

        <div id="viewRevenue" class="admin-section" style="display:none">
            <h2>Total Revenue</h2>
            <iframe src="ViewTotalRevenueServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>

        <div id="viewOrders" class="admin-section" style="display:none">
            <h2>Customer Orders</h2>
            <iframe src="ViewOrdersServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>

        <div id="viewRides" class="admin-section" style="display:none">
            <h2>Ongoing and Completed Rides</h2>
            <iframe src="ViewRidesServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>

        <div id="bookingReport" class="admin-section" style="display:none">
            <h2>All Booking Details</h2>
            <iframe src="BookingReportServlet" frameborder="0" width="100%" height="400"></iframe>
        </div>
    </div>
</body>
</html>
