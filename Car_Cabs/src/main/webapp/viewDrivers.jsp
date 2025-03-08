<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View All Drivers</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <div class="container">
        <h2>All Drivers</h2>
        <table border="1">
    <tr>
        <th>Driver ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Phone</th>
        <th>License</th>
        <th>Vehicle Type</th>
        <th>Plate</th>
    </tr>
    <c:forEach var="driver" items="${driverList}">
        <tr>
            <td>${driver.driverId}</td>
            <td>${driver.username}</td>
            <td>${driver.name}</td>
            <td>${driver.phone}</td>
            <td>${driver.license}</td>
            <td>${driver.vehicleType}</td>
            <td>${driver.plate}</td>
        </tr>
    </c:forEach>
</table>

    </div>
</body>
</html>