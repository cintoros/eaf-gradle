package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {

  @Override
  public Optional<PriceCategory> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PriceCategory> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PriceCategory save(PriceCategory t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(PriceCategory entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean existsById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

}
