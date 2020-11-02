package ch.fhnw.eaf.persistence;

import ch.fhnw.eaf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByLastName(String lastname);

  // @Transactional(propagation = Propagation.REQUIRES_NEW)  //aufgabe1
  Customer getOne(Long id);

  // @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)  //aufgabe6
  List<Customer> findAll();
}

