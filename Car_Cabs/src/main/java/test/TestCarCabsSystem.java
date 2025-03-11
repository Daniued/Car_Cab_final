package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Bean.Customer;
import Bean.Driver;
import Bean.BookingRequest;
import Bean.BookingRequest;

import dao.AdminCustomerDAO;
import dao.DriverBookingDAO;


public class TestCarCabsSystem {

    private AdminCustomerDAO adminCustomerDAO;
    private DriverBookingDAO driverBookingDAO;


    @Before
    public void setUp() {
        adminCustomerDAO = new AdminCustomerDAO();
        driverBookingDAO = new DriverBookingDAO();
      
    }

    // Test 1: Validate customer list retrieval
    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = adminCustomerDAO.getAllCustomers();
        assertNotNull("Customer list should not be null", customers);
        assertTrue("Customer list size should be non-negative", customers.size() >= 0);
    }

    // Test 2: Validate customer details
    @Test
    public void testCustomerDetails() {
        List<Customer> customers = adminCustomerDAO.getAllCustomers();
        if (!customers.isEmpty()) {
            Customer customer = customers.get(0);
            assertNotNull("Customer name should not be null", customer.getName());
            assertNotNull("Customer phone should not be null", customer.getPhone());
        }
    }

  

   

    // Test 5: Validate booking request creation
    @Test
    public void testCreateBookingRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setUsername("customer1");
        bookingRequest.setPickupLocation("Location A");
        bookingRequest.setDropoffLocation("Location B");
        bookingRequest.setVehicleType("Car");
        bookingRequest.setTotalAmount(100.0);
        bookingRequest.setStatus("Pending");
        assertNotNull("Booking request should be created successfully", bookingRequest);
    }
}
