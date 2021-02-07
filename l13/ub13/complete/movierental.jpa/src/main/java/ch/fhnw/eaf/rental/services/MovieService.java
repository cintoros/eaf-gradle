package ch.fhnw.eaf.rental.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;

public interface MovieService {
	public Movie getMovieById(Long id);

	public List<Movie> getAllMovies();

	public List<Movie> getMoviesByTitle(String title);

    @Secured({"ROLE_ADMIN"})
	public Movie saveMovie(Movie movie);

    @Secured({"ROLE_ADMIN"})
    public void deleteMovie(Movie movie);

	public List<PriceCategory> getAllPriceCategories();
}
