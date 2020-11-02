package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.MovieService;
import ch.fhnw.eaf.rental.services.RentalService;
import ch.fhnw.eaf.rental.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
  private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

  @Autowired
  private RentalService rentalService;

  @Autowired
  private UserService userService;

  @Autowired
  private MovieService movieService;


  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<List<Rental>> getAllRentals() {
    List<Rental> rentals = rentalService.getAllRentals();
    if (rentals == null || rentals.size() == 0) {
      logger.debug("No rentals found");
      new ResponseEntity<List<Rental>>(HttpStatus.NO_CONTENT);
    }
    logger.debug("Successfully returned {} rentals", rentals.size());
    return new ResponseEntity<List<Rental>>(rentals, HttpStatus.OK);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Entity created"),
      @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Rental> createRental(@Valid @RequestBody CreateRentalData data, BindingResult result) {
    if (result.hasErrors()) {
      logger.error("Could not create rental. Precondition failed!");
      return new ResponseEntity<Rental>(HttpStatus.PRECONDITION_FAILED);
    }
    User user = userService.getUserById(data.userId);
    Movie movie = movieService.getMovieById(data.movieId);
    Rental rental = userService.rentMovie(user, movie, data.rentalDays);
    logger.debug("Successfully created rental[{}]", rental.getId());
    return new ResponseEntity<Rental>(rental, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Rental> getRental(@PathVariable Long id) {
    Rental rental = rentalService.getRentalById(id);
    if (rental == null) {
      logger.error("Could not find rental with id={}", id);
      new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
    }
    logger.debug("Successfully returned rental[{}]", id);
    return new ResponseEntity<Rental>(rental, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Rental> deleteRental(@PathVariable Long id) {
    Rental rental = rentalService.getRentalById(id);
    if (rental == null) {
      logger.error("Could not find rental with id={}", id);
      new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
    }
    rentalService.deleteRental(rentalService.getRentalById(id));
    logger.debug("Successfully deleted rental[{}]", id);
    return new ResponseEntity<Rental>(HttpStatus.OK);
  }

}
