package ch.fhnw.eaf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import ch.fhnw.eaf.model.Customer;
import ch.fhnw.eaf.services.CustomerService;
import ch.fhnw.eaf.services.ServiceException;


@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionTest {
	@Autowired 
	private CustomerService service;
    
    @Test
	public void aufgabe1() throws ServiceException {
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
		
		Customer c = new Customer(null, "Hugo", "Test");
		service.saveOrUpdateCustomer(c);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(5, customers.size());		
    }

	@Test
	public void aufgabe2() throws ServiceException {
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
		
		Customer c = new Customer(Long.valueOf(1), "Hugo", "Test");
		service.saveOrUpdateCustomer(c);
		
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals("Hugo", customers.get(0).getFirstName());		
    }
    
	@Test
	public void aufgabe3() throws ServiceException {
		service.deleteCustomerById(Long.valueOf(1));
		
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(3, customers.size());		
	}
	
	@Test
	public void aufgabe4() throws Exception {
		Customer c = service.getById(Long.valueOf(1));
		assertEquals(c.getLastName(), "Tester1");		
	}

	@Test
	public void aufgabe5() throws Exception {
		Customer c = new Customer();
		c.setFirstName("Maria");
		c.setLastName("Meier");
		service.findByIdAndUpdateCustomer(Long.valueOf(1), c);
		c = service.getById(Long.valueOf(1));
		assertEquals(c.getLastName(), "Meier");		
	}
	
	@Test
	public void aufgabe6() {
		List<Customer> customers = service.findAllCustomers();
		assertEquals(4, customers.size());		
	}
	
	@Test
	public void aufgabe7() {
		List<Customer> customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());

		Customer c = new Customer(null, "Hugo", "Tester");
		try {
			c = service.saveOrUpdateCustomerWithException(c);
		} catch (Exception e) {
			System.err.println("TransactionTest: Exception received");
			// do nothing
		}
		
		// no new customer should be added, 
		// exception should be thrown to force a rollback	
		customers = service.findAllCustomers();
		assertNotNull(customers);
		assertEquals(4, customers.size());
	}

}
