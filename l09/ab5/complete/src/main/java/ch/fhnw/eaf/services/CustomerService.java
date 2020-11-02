package ch.fhnw.eaf.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.model.Customer;
import ch.fhnw.eaf.persistence.CustomerRepository;

@Service
// @Transactional
public class CustomerService {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CustomerRepository customerRepository;

  // @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  // @Transactional(propagation = Propagation.SUPPORTS)
  // //aufgabe4
  public Customer getById(Long id) {
    Customer c = customerRepository.findById(id).get(); // aufgabe4 final
    // Customer c = customerRepository.getOne(id);
    log.debug("Found customer " + c.getLastName());
    return c;
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS) // aufgabe7
  // @Transactional(readOnly = true) //aufgabe7
  public List<Customer> findAllCustomers() {
    return customerRepository.findAll();
  }

  @Transactional // aufgabe1
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

  // @Transactional //aufgabe7
  @Transactional(rollbackFor = ServiceException.class) // aufgabe7
  public Customer saveOrUpdateCustomerWithException(Customer customer) throws ServiceException {
    if (customer == null) {
      throw new ServiceException("parameter is not set!");
    }
    try {
      if (customer.getId() != null) {
        findByIdAndUpdateCustomer(customer.getId(), customer);
      } else {
        customer = customerRepository.save(customer);
        log.debug("saved new customer[" + customer.getId() + "]");
      }
      // following line only needed for aufgabe 7
      // throw new CustomerServiceException("just for testing"); //aufgabe7
      throw new ServiceException("just for testing"); // aufgabe7
    } finally {
      // do nothing
    }
  }

  @Transactional // aufgabe5
  public void findByIdAndUpdateCustomer(Long id, Customer customer) throws ServiceException {
    if ((id == null) || (customer == null)) {
      throw new ServiceException("parameters are not set!");
    }
    Customer c = customerRepository.getOne(id);
    if (c != null) {
      c.setFirstName(customer.getFirstName());
      c.setLastName(customer.getLastName());
      /*
       * no 'save' is needed, because the managed entity will be persisted with the
       * transaction commit
       */
      // customerRepository.save(c);   // aufgabe2
    } else {
      log.info("No customer with id " + id + " found");
    }
  }

  @Transactional // aufgabe3
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
