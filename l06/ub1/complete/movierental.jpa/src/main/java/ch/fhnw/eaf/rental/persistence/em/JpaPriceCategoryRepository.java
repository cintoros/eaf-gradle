package ch.fhnw.eaf.rental.persistence.em;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;

@Repository
@Profile("em")
public class JpaPriceCategoryRepository extends AbstractJpaRepository<PriceCategory> implements PriceCategoryRepository {
}
