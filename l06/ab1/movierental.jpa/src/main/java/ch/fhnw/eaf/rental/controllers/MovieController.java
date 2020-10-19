package ch.fhnw.eaf.rental.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.services.MovieService;

@RestController
@RequestMapping(path = "/movierental")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping(path = "/movies")
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}

	@PostMapping(path = "/movies")
	public Movie createMovie(@RequestBody Movie movie) {
		movieService.saveMovie(movie);
		return movie;
	}

	@GetMapping(path = "/movies/{id}")
	public Movie getMovie(@PathVariable Long id) {
		return movieService.getMovieById(id);
	}

	@DeleteMapping(path = "/movies/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(movieService.getMovieById(id));
	}

	@PutMapping(path = "/movies/{id}")
	public void updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
		movieService.saveMovie(movie);
	}

}