package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;

@Repository
public class JpaMovieRepository implements MovieRepository {

  @Override
  public Optional<Movie> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Movie> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Movie save(Movie t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(Movie entity) {
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

  @Override
  public List<Movie> findByTitle(String title) {
    // TODO Auto-generated method stub
    return null;
  }

}
