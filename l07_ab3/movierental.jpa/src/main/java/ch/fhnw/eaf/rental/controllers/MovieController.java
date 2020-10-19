package ch.fhnw.eaf.rental.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

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
            logger.debug("No movies found");
            new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Successfully returned {} movies", movies.size());
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Entity created"),
        @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true))) 
    })
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Could not create movie. Precondition failed!");
            return new ResponseEntity<Movie>(HttpStatus.PRECONDITION_FAILED);
        }
		movieService.saveMovie(movie);
        logger.debug("Successfully created movie[{}]", movie.getId());
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
            logger.error("Could not find movie with id={}", id);
            new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Successfully returned movie[{}]", id);
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
            logger.error("Could not find movie with id={}", id);
            new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
        }
        movieService.deleteMovie(movie);
        logger.debug("Successfully deleted movie[{}]", id);
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
            logger.error("Could not create movie. Precondition failed!");
            return new ResponseEntity<Movie>(HttpStatus.PRECONDITION_FAILED);
        }
        if (movieService.getMovieById(id) == null) {
            logger.error("Could not find movie with id={}", id);
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movieService.saveMovie(movie);
        logger.debug("Successfully updated movie[{}]", id);
        return new ResponseEntity<Movie>(HttpStatus.OK);
	}

}