package ch.fhnw.eaf.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.eaf.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastname);
    // @Transactional(propagation = Propagation.REQUIRES_NEW)  //aufgabe1
    Customer getOne(Long id);
    // @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)  //aufgabe6
    List<Customer> findAll();
}

