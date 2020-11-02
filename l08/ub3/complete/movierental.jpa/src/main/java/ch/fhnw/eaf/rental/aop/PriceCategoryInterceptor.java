package ch.fhnw.eaf.rental.aop;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class PriceCategoryInterceptor {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${movierental.index.children}")
  private Long indexChildren;

  @Value("${movierental.index.regular}")
  private Long indexRegular;

  @Autowired
  private PriceCategoryRepository priceCategoryRepository;

  @Before("execution(* createMovie(ch.fhnw.eaf.rental.model.Movie,..)) && args(movie,..)")
  public void modifyPriceCategoryOnCreate(Movie movie) throws Throwable {
    modifyPriceCategoryFromChildrenToRegular(movie);
    logger.debug("modifyPriceCategoryOnCreate() changed to {}", movie.getPriceCategory());
  }

  @Before("execution(* updateMovie(java.lang.Long,ch.fhnw.eaf.rental.model.Movie,..)) && args(id,movie,..)")
  public void modifyPriceCategoryOnUpdate(Long id, Movie movie) throws Throwable {
    modifyPriceCategoryFromChildrenToRegular(movie);
    logger.debug("modifyPriceCategoryOnUpdate() changed to {}", movie.getPriceCategory());
  }

  private void modifyPriceCategoryFromChildrenToRegular(Movie movie) {
    PriceCategory pc = movie.getPriceCategory();
    // check if set to PriceCategoryChildren (id=2)
    if (pc.getId().equals(indexChildren)) {
      // get the pricecategory from the repo to prevent TransientPropertyValueException
      // get PriceCategoryRegular (id=1)
      Optional<PriceCategory> optional = priceCategoryRepository.findById(indexRegular);
      movie.setPriceCategory(optional.get());
    }
  }
}
