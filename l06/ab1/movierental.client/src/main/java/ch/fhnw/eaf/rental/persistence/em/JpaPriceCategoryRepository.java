package ch.fhnw.eaf.rental.persistence.em;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("em")
public class JpaPriceCategoryRepository extends AbstractJpaRepository<PriceCategory> implements PriceCategoryRepository {
}
