package ch.fhnw.eaf.rental.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import ch.fhnw.eaf.rental.model.Rental;

public interface RentalService {
	public Rental getRentalById(Long id);

	public List<Rental> getAllRentals();

    @Secured({"ROLE_ADMIN"})
	public void deleteRental(Rental rental);
}
