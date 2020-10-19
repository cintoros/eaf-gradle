package ch.fhnw.eaf.rental.persistence.em;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("em")
public class JpaRentalRepository extends AbstractJpaRepository<Rental> implements RentalRepository {

  @Override
  public List<Rental> findByUser(User user) {
    return user.getRentals();
  }

}
