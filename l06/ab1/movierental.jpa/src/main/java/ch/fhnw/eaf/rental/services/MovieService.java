package ch.fhnw.eaf.rental.services;

import java.util.List;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;

public interface MovieService {
	public Movie getMovieById(Long id);

	public List<Movie> getAllMovies();

	public List<Movie> getMoviesByTitle(String title);

	public Movie saveMovie(Movie movie);

	public void deleteMovie(Movie movie);

	public List<PriceCategory> getAllPriceCategories();
}
