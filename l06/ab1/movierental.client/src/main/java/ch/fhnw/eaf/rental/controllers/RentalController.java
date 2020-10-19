package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.MovieService;
import ch.fhnw.eaf.rental.services.RentalService;
import ch.fhnw.eaf.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public List<Rental> getAllRentals() {
    return rentalService.getAllRentals();
  }

  @PostMapping
  public Rental createRental(@RequestBody CreateRentalData data) {
    User user = userService.getUserById(data.userId);
    Movie movie = movieService.getMovieById(data.movieId);
    return userService.rentMovie(user, movie, data.rentalDays);
  }

  @GetMapping("/{id}")
  public Rental getRental(@PathVariable Long id) {
    return rentalService.getRentalById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteRental(@PathVariable Long id) {
    rentalService.deleteRental(rentalService.getRentalById(id));
  }

}
