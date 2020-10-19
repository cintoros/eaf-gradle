package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
