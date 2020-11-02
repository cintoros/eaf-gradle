package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.services.MovieService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<List<Movie>> getAllMovies() {
    List<Movie> movies = movieService.getAllMovies();
    if (movies == null || movies.size() == 0) {
      new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Entity created"),
      @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie, BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<Movie>(HttpStatus.PRECONDITION_FAILED);
    }
    movieService.saveMovie(movie);
    return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
    Movie movie = movieService.getMovieById(id);
    if (movie == null) {
      new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Movie>(movie, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
    Movie movie = movieService.getMovieById(id);
    if (movie == null) {
      new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
    }
    movieService.deleteMovie(movie);
    return new ResponseEntity<Movie>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie, BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<Movie>(HttpStatus.PRECONDITION_FAILED);
    }
    if (movieService.getMovieById(id) == null) {
      return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
    }
    movieService.saveMovie(movie);
    return new ResponseEntity<Movie>(HttpStatus.OK);
  }

}
