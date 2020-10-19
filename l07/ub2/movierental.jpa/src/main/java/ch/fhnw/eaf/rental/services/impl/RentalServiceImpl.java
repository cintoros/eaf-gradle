package ch.fhnw.eaf.rental.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import ch.fhnw.eaf.rental.services.RentalService;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private RentalRepository rentalRepo;

	@Override
	public List<Rental> getAllRentals() {
		List<Rental> rentals = rentalRepo.findAll();
		log.debug("getAllRentals() done");
		return rentals;
	}

	@Override
	public Rental getRentalById(Long id) {
		return rentalRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteRental(Rental rental) {
		if (rental == null) {
			throw new RuntimeException("'rental' parameter is not set!");
		}

		// add detached rental object to persistence context. This is necessary if the property
		// spring.jpa.open-in-view=false is set.
		rental = rentalRepo.save(rental);
		// now user and movie are managed as well as they are accessed over a managed entity.

		rental.getUser().getRentals().remove(rental);
		rental.getMovie().setRented(false);

		rentalRepo.delete(rental);

		if (log.isDebugEnabled()) {
			log.debug("rental[" + rental.getId() + "] deleted");
		}
	}
}
