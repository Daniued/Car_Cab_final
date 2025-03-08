package servlets;

import Bean.BookingRequest;
import dao.AdminOrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        System.out.println("Fetching orders with status: " + status);

        List<BookingRequest> orders = AdminOrdersDAO.getCustomerOrders(status);
        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
    }
}
