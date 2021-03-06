package ch.fhnw.eaf.rental.persistence.crit;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("crit")
public class JpaRentalRepository extends AbstractJpaRepository<Rental> implements RentalRepository {

  public JpaRentalRepository() {
    super(Rental.class);
  }

  @Override
  public List<Rental> findByUser(User user) {
    return user.getRentals();
  }

}
