package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Bean.Customer;
import dao.AdminCustomerDAO;

public class TestAdminCustomerDAO {

    private AdminCustomerDAO adminCustomerDAO;

    @Before
    public void setUp() {
        adminCustomerDAO = new AdminCustomerDAO();
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = adminCustomerDAO.getAllCustomers();
        assertNotNull("Customer list should not be null", customers);
        assertTrue("Customer list size should be non-negative", customers.size() >= 0);
    }

    @Test
    public void testCustomerDetails() {
        List<Customer> customers = adminCustomerDAO.getAllCustomers();
        if (!customers.isEmpty()) {
            Customer customer = customers.get(0);
            assertNotNull("Customer name should not be null", customer.getName());
            assertNotNull("Customer phone should not be null", customer.getPhone());
        }
    }
}
