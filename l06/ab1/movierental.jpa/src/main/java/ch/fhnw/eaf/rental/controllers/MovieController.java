package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  public List<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @PostMapping
  public Movie createMovie(@RequestBody Movie movie) {
    movieService.saveMovie(movie);
    return movie;
  }

  @GetMapping("/{id}")
  public Movie getMovie(@PathVariable Long id) {
    return movieService.getMovieById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(movieService.getMovieById(id));
  }

  @PutMapping("/{id}")
  public void updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    movieService.saveMovie(movie);
  }

}
