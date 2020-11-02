package ch.fhnw.eaf.rental.persistence.sd;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile({"sd", "prod"})
public interface JpaPriceCategoryRepository extends PriceCategoryRepository, JpaRepository<PriceCategory, Long> {
}
