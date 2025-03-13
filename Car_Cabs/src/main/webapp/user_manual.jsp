<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Manual - Cab Reservation System</title>
    <link rel="stylesheet" type="text/css" href="styles/ccs1.css">
</head>
<body>
    <header class="navbar">
        <div class="logo">
            <img src="img/logo.png" alt="Cab Reservation Logo">
            <h1>Cab Cars</h1>
        </div>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
            <a href="bookings.jsp">Book a Ride</a>
            <a href="user_manual.jsp" class="active">User Manual</a>
        </nav>
    </header>

    <div class="container">
        <h2>User Manual</h2>
        <p>Welcome to the Cab Reservation System! This guide will walk you through how to use the platform efficiently.</p>

        <h3>1. Getting Started</h3>
        <p>The system allows customers to book cabs, drivers to manage rides, and admins to monitor bookings.</p>

        <h3>2. How to Register</h3>
        <ul>
            <li>Go to the <a href="register.jsp">Registration Page</a>.</li>
            <li>Fill in your details, including username, password, and contact information.</li>
            <li>Choose your role: <b>Customer</b> or <b>Driver</b>.</li>
            <li>If registering as a driver, enter vehicle details.</li>
            <li>Click <b>Register</b>, and your account will be created.</li>
        </ul>

        <h3>3. Logging In</h3>
        <ul>
            <li>Go to the <a href="login.jsp">Login Page</a>.</li>
            <li>Enter your username and password.</li>
            <li>Click <b>Login</b> to access your dashboard.</li>
        </ul>

        <h3>4. Booking a Cab (For Customers)</h3>
        <ul>
            <li>After logging in, go to the <a href="bookings.jsp">Booking Page</a>.</li>
            <li>Enter pickup and drop-off locations.</li>
            <li>Choose the vehicle type.</li>
            <li>Select a time and confirm your booking.</li>
            <li>Wait for driver confirmation.</li>
        </ul>

        <h3>5. Managing Rides (For Drivers)</h3>
        <ul>
            <li>Drivers can view available ride requests on their dashboard.</li>
            <li>They can <b>Accept</b> or <b>Reject</b> bookings.</li>
            <li>Upon acceptance, they can start and complete the ride.</li>
        </ul>

        <h3>6. Admin Panel</h3>
        <ul>
            <li>Admins can manage customer and driver accounts.</li>
            <li>They can view all bookings and ride history.</li>
            <li>Admins have access to revenue reports.</li>
        </ul>

        <h3>7. Logging Out</h3>
        <p>Click the <b>Logout</b> button on your dashboard to securely exit your account.</p>

        <h3>8. Support</h3>
        <p>If you encounter any issues, please contact our support team at <b>support@cabreservationsystem.com</b>.</p>

        <footer>
            <p>&copy; 2025 Cab Reservation System. All Rights Reserved.</p>
        </footer>
    </div>
</body>
</html>
