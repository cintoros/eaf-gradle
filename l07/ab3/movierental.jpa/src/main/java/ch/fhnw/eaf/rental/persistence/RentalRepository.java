package ch.fhnw.eaf.rental.persistence;

import java.util.List;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

public interface RentalRepository extends Repository<Rental, Long> {
	List<Rental> findByUser(User user);
}
