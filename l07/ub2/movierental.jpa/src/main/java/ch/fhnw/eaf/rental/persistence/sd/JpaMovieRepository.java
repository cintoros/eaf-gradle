package ch.fhnw.eaf.rental.persistence.sd;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;

@Profile({"sd","prod"})
public interface JpaMovieRepository extends MovieRepository, JpaRepository<Movie, Long> {
}
