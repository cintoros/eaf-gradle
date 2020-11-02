package ch.fhnw.eaf.persistence;

import ch.fhnw.eaf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByLastName(String lastname);

  Customer getOne(Long id);

  List<Customer> findAll();
}

