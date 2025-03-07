package servlets;

import Bean.Driver;
import dao.DriverDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriverRegisterServlet")
public class DriverRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve parameters from the registration form
        String role = request.getParameter("role");
        if (!"driver".equalsIgnoreCase(role)) {
            response.getWriter().println("Error: Invalid role for driver registration.");
            return;
        }

        // Get common fields for login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("Error: Passwords do not match.");
            return;
        }

        // Retrieve driver-specific fields
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");  
        String license = request.getParameter("license");
        String vehicle = request.getParameter("vehicle");
        String plate = request.getParameter("plate");

        // Create Driver bean
        Driver driver = new Driver();
        driver.setUsername(username);
        driver.setPassword(password);
        driver.setName(name);
        driver.setPhone(phone);
        driver.setLicense(license);
        driver.setVehicleType(vehicle);
        driver.setPlate(plate);
        driver.setCapacity(vehicle.equals("Car") ? 4 : (vehicle.equals("Van") ? 8 : 2)); // Capacity based on vehicle type

        // Register driver using DAO
        String errorMessage = DriverDAO.registerDriver(driver);
        if (errorMessage.isEmpty()) {
            response.sendRedirect("index.jsp?message=Registration successful, please login!");
        } else {
            response.getWriter().println("Registration failed: " + errorMessage);
        }
    }
}
