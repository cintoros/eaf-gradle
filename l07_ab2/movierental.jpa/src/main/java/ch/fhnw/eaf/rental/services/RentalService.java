package ch.fhnw.eaf.rental.services;

import java.util.List;

import ch.fhnw.eaf.rental.model.Rental;

public interface RentalService {
	public Rental getRentalById(Long id);

	public List<Rental> getAllRentals();

	public void deleteRental(Rental rental);
}
