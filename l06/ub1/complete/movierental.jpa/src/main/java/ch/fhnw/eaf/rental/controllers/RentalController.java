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
      new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Rental>>(rentals, HttpStatus.OK);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Entity created"),
      @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<Rental> createRental(@Valid @RequestBody CreateRentalData data, BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<Rental>(HttpStatus.PRECONDITION_FAILED);
    }
    User user = userService.getUserById(data.userId);
    Movie movie = movieService.getMovieById(data.movieId);
    Rental rental = userService.rentMovie(user, movie, data.rentalDays);
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
      new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
    }
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
      new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
    }
    rentalService.deleteRental(rentalService.getRentalById(id));
    return new ResponseEntity<Rental>(HttpStatus.OK);
  }

}
