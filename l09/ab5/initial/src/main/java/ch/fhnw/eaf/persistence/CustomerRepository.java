package ch.fhnw.eaf.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.eaf.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByLastName(String lastname);

  Customer getOne(Long id);

  List<Customer> findAll();
}

