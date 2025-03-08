<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to Customer Dashboard</h1>
        <a href="logout.jsp" class="btn">Logout</a>
        <hr>

        <!-- Quick Booking Form -->
        <section>
            <h2>Quick Booking</h2>
            <form action="CustomerBookingServlet" method="post">
                <input type="hidden" name="bookingType" value="quick">
                
                <label for="pickup">Pickup Location:</label>
                <select name="pickup" id="pickup" required>
                    <option value="">Select Pickup City</option>
                    <option value="Batticaloa">Batticaloa</option>
                    <option value="Trincomalee">Trincomalee</option>
                    <option value="Ampara">Ampara</option>
                    <option value="Bathula">Bathula</option>
                    <option value="Hatton">Hatton</option>
                    <option value="Jaffna">Jaffna</option>
                    <option value="Colombo">Colombo</option>
                    <option value="Kandy">Kandy</option>
                    <option value="Galle">Galle</option>
                    <option value="Matara">Matara</option>
                </select><br>

                <label for="dropoff">Drop-off Location:</label>
                <select name="dropoff" id="dropoff" required>
                    <option value="">Select Drop-off City</option>
                    <option value="Batticaloa">Batticaloa</option>
                    <option value="Trincomalee">Trincomalee</option>
                    <option value="Ampara">Ampara</option>
                    <option value="Bathula">Bathula</option>
                    <option value="Hatton">Hatton</option>
                    <option value="Jaffna">Jaffna</option>
                    <option value="Colombo">Colombo</option>
                    <option value="Kandy">Kandy</option>
                    <option value="Galle">Galle</option>
                    <option value="Matara">Matara</option>
                </select><br>

                <label for="vehicleType">Vehicle Type:</label>
                <select name="vehicleType" id="vehicleType" required>
                    <option value="">Select Vehicle Type</option>
                    <option value="Car">Car</option>
                    <option value="Van">Van</option>
                    <option value="Bus">Bus</option>
                    <option value="Bike">Bike</option>
                </select><br>

                <label for="time">Time:</label>
                <input type="time" name="time" required><br>

                <label for="passengerCount">Passenger Count:</label>
                <input type="number" name="passengerCount" min="1" max="10" required><br>

                <button type="submit" class="btn">Book Now</button>
            </form>
        </section>

        <hr>

        <!-- Planned Booking Form -->
        <section>
            <h2>Planned Booking</h2>
            <form action="CustomerBookingServlet" method="post">
                <input type="hidden" name="bookingType" value="planned">

                <label for="date">Select Date:</label>
                <input type="date" name="date" required><br>

                <label for="pickup">Pickup Location:</label>
                <select name="pickup" id="pickup" required>
                    <option value="">Select Pickup City</option>
                    <option value="Batticaloa">Batticaloa</option>
                    <option value="Trincomalee">Trincomalee</option>
                    <option value="Ampara">Ampara</option>
                    <option value="Bathula">Bathula</option>
                    <option value="Hatton">Hatton</option>
                    <option value="Jaffna">Jaffna</option>
                    <option value="Colombo">Colombo</option>
                    <option value="Kandy">Kandy</option>
                    <option value="Galle">Galle</option>
                    <option value="Matara">Matara</option>
                </select><br>

                <label for="dropoff">Drop-off Location:</label>
                <select name="dropoff" id="dropoff" required>
                    <option value="">Select Drop-off City</option>
                    <option value="Batticaloa">Batticaloa</option>
                    <option value="Trincomalee">Trincomalee</option>
                    <option value="Ampara">Ampara</option>
                    <option value="Bathula">Bathula</option>
                    <option value="Hatton">Hatton</option>
                    <option value="Jaffna">Jaffna</option>
                    <option value="Colombo">Colombo</option>
                    <option value="Kandy">Kandy</option>
                    <option value="Galle">Galle</option>
                    <option value="Matara">Matara</option>
                </select><br>

                <label for="vehicleType">Vehicle Type:</label>
                <select name="vehicleType" id="vehicleType" required>
                    <option value="">Select Vehicle Type</option>
                    <option value="Car">Car</option>
                    <option value="Van">Van</option>
                    <option value="Bus">Bus</option>
                    <option value="Bike">Bike</option>
                </select><br>

                <label for="time">Time:</label>
                <input type="time" name="time" required><br>

                <label for="passengerCount">Passenger Count:</label>
                <input type="number" name="passengerCount" min="1" max="10" required><br>

                <button type="submit" class="btn">Plan Booking</button>
            </form>
        </section>

        <hr>

        <!-- Booking History Section -->
      <!-- Booking History Section -->
<section>
    <h2>Your Booking History</h2>
    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Pickup</th>
            <th>Drop-off</th>
            <th>Date</th>
            <th>Time</th>
            <th>Vehicle Type</th>
            <th>Status</th>
            <th>Total Amount</th>
        </tr>
        <c:forEach var="booking" items="${bookingHistory}">
            <tr>
                <<td>${booking.requestId}</td>

                <td>${booking.pickupLocation}</td>
                <td>${booking.dropoffLocation}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.time}</td>
                <td>${booking.vehicleType}</td>
                <td>${booking.status}</td>
                <td>${booking.totalAmount}</td>
            </tr>
        </c:forEach>
    </table>
</section>

    </div>

</body>
</html>
