package ch.fhnw.eaf.rental.persistence;

import java.util.List;

import ch.fhnw.eaf.rental.model.Movie;

public interface MovieRepository extends Repository<Movie, Long> {
	List<Movie> findByTitle(String title);
}
