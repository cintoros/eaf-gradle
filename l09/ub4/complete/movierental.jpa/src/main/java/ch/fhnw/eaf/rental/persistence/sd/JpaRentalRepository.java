package ch.fhnw.eaf.rental.persistence.sd;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile({"sd", "prod"})
public interface JpaRentalRepository extends RentalRepository, JpaRepository<Rental, Long> {
}
