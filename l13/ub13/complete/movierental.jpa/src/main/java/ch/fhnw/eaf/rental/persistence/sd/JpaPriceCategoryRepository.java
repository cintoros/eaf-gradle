package ch.fhnw.eaf.rental.persistence.sd;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;

@Profile({"sd","prod"})
public interface JpaPriceCategoryRepository extends PriceCategoryRepository, JpaRepository<PriceCategory, Long> {
}
