package ch.fhnw.eaf.services;

import ch.fhnw.eaf.model.Customer;
import ch.fhnw.eaf.persistence.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CustomerRepository customerRepository;

  public Customer getById(Long id) {
    // Customer c = customerRepository.findById(id).get();  //aufgabe3
    Customer c = customerRepository.getOne(id);
    //log.debug("Found customer " + c.getLastName());
    return c;
  }

  public List<Customer> findByName(String name) {
    return customerRepository.findByLastName(name);
  }

  public List<Customer> findAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer saveOrUpdateCustomer(Customer customer) throws ServiceException {
    if (customer == null) {
      throw new ServiceException("parameter is not set!");
    }
    try {
      if (customer.getId() != null) {
        findByIdAndUpdateCustomer(customer.getId(), customer);
        log.debug("updated customer[" + customer.getId() + "]");
      } else {
        customer = customerRepository.save(customer);
        log.debug("saved new customer[" + customer.getId() + "]");
      }
    } finally {
      // do nothing
    }
    return customer;
  }

  public Customer saveOrUpdateCustomerWithException(Customer customer) throws ServiceException {
    if (customer == null) {
      throw new ServiceException("parameter is not set!");
    }
    try {
      if (customer.getId() != null) {
        findByIdAndUpdateCustomer(customer.getId(), customer);
        log.debug("updated customer[" + customer.getId() + "]");
      } else {
        customer = customerRepository.save(customer);
        log.debug("saved new customer[" + customer.getId() + "]");
      }
      // following line only needed for aufgabe 7
      throw new CustomerServiceException("just for testing");  //aufgabe7
      // throw new ServiceException("just for testing");  //aufgabe7
    } finally {
      // do nothing
    }
  }

  public void findByIdAndUpdateCustomer(Long id, Customer customer) throws ServiceException {
    if ((id == null) || (customer == null)) {
      throw new ServiceException("parameters are not set!");
    }
    Customer c = customerRepository.getOne(id);
    if (c != null) {
      c.setFirstName(customer.getFirstName());
      c.setLastName(customer.getLastName());
      customerRepository.save(c);
    } else {
      log.info("No customer with id " + id + " found");
    }
  }

  public void deleteCustomerById(Long id) throws CustomerServiceException {
    if (id == null) {
      throw new CustomerServiceException("'id' parameter is not set!");
    }

    Customer customer = customerRepository.getOne(id);
    if (customer != null) {
      customerRepository.delete(customer);
      log.debug("customer[" + customer.getId() + "] deleted");
    }
  }
}
