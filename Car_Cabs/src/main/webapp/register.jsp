<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Cab Reservation</title>
    <link rel="stylesheet" type="text/css" href="styles/css.css">
    <script>
        function toggleDriverFields() {
            var role = document.getElementById("role").value;
            var driverFields = document.getElementById("driverFields");
            driverFields.style.display = (role === "driver") ? "block" : "none";
        }
     

    </script>
</head>
<body>
    <div class="registration-container">
        <div class="registration-image">
            <h2 style="text-align:center;">User Registration</h2>
        </div>
        <div class="registration-form">
            <form action="<%= request.getContextPath()%>/CustomerRegisterServlet" method="post" id="registrationForm"  style="margin:auto;">
                <div class="form-group">
                    <label for="role">Select Role:</label>
                    <select name="role" id="role" onchange="toggleDriverFields()" required>
                        <option value="customer">Customer</option>
                        <option value="driver">Driver</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username" placeholder="Enter Username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" placeholder="Enter Password" required>
                </div>

                <div class="form-group">
                    <label for="confirm_password">Confirm Password:</label>
                    <input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" required>
                </div>

                <div class="form-group">
                    <label for="name">Full Name:</label>
                    <input type="text" name="name" id="name" placeholder="Enter Full Name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" placeholder="Enter Email Address" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" name="phone" id="phone" placeholder="Enter Phone Number" required>
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea name="address" id="address" placeholder="Enter Address" required></textarea>
                </div>

                <div class="form-group">
                    <label for="nic">NIC (National ID):</label>
                    <input type="text" name="nic" id="nic" placeholder="Enter NIC" required>
                </div>

                <div id="driverFields" style="display: none;">
                    <div class="form-group">
                        <label for="license">Driver License Number:</label>
                        <input type="text" name="license" id="license" placeholder="Enter License Number" required>
                    </div>
                    <div class="form-group">
                        <label for="vehicle">Vehicle Type:</label>
                        <select name="vehicle" id="vehicle" required>
                            <option value="Car">Car</option>
                            <option value="Van">Van</option>
                            <option value="Bike">Bike</option>
                            <option value="Bus">Bus</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="plate">Vehicle Plate Number:</label>
                        <input type="text" name="plate" id="plate" placeholder="Enter Plate Number" required>
                    </div>
                    <div class="form-group">
                        <label for="capacity">Vehicle Capacity:</label>
                        <input type="number" name="capacity" id="capacity" placeholder="Enter Capacity" min="1" required>
                    </div>
                </div>

                <button type="submit" class="btn">Register</button>
                <p style="text-align:center;">Already have an account? <a href="login.jsp">Login here</a></p>
            </form>
        </div>
    </div>
</body>
</html>
