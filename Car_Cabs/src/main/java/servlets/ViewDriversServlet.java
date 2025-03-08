package servlets;

import Bean.Driver;
import dao.AdminDriverDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewDriversServlet")
public class ViewDriversServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Fetching all driver data for admin view...");
        List<Driver> driverList = AdminDriverDAO.getAllDriverAccounts();
        request.setAttribute("driverList", driverList);
        request.getRequestDispatcher("viewDrivers.jsp").forward(request, response);
    }
}
