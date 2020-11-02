package ch.fhnw.eaf.rental.services.impl;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import ch.fhnw.eaf.rental.services.RentalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    rental.getUser().getRentals().remove(rental);
    rental.getMovie().setRented(false);

    rentalRepo.delete(rental);

    if (log.isDebugEnabled()) {
      log.debug("rental[" + rental.getId() + "] deleted");
    }
  }
}
