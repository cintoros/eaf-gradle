package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

import java.util.List;

public interface RentalRepository extends Repository<Rental, Long> {
  List<Rental> findByUser(User user);
}
