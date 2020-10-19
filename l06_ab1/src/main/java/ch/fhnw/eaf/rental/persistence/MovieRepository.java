package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.Movie;

import java.util.List;

public interface MovieRepository extends Repository<Movie, Long> {
  List<Movie> findByTitle(String title);
}
