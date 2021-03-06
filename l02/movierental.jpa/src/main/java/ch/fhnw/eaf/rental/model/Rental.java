package ch.fhnw.eaf.rental.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Rental.class)
public class Rental {
  private Long id;

  private Movie movie;
  private User user;
  private LocalDate rentalDate;
  private int rentalDays;

  public Rental() {
    this.rentalDate = LocalDate.now();
  }

  public Rental(User user, Movie movie, int rentalDays) {
    this(user, movie, rentalDays, LocalDate.now());
  }

  public Rental(User user, Movie movie, int rentalDays, LocalDate rentalDate) {
    if (user == null || movie == null || rentalDays <= 0) {
      throw new NullPointerException("not all input parameters are set!" + user + "/" + movie + "/" + rentalDays);
    }
    if (movie.isRented()) {
      throw new IllegalStateException("movie is already rented!");
    }
    this.user = user;
    user.getRentals().add(this);
    this.movie = movie;
    movie.setRented(true);
    this.rentalDays = rentalDays;
    this.rentalDate = rentalDate;
  }

  public double getRentalFee() {
    return movie.getPriceCategory().getCharge(rentalDays);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDate getRentalDate() {
    return rentalDate;
  }

  public void setRentalDate(LocalDate rentalDate) {
    this.rentalDate = rentalDate;
  }

  public int getRentalDays() {
    return rentalDays;
  }

  public void setRentalDays(int rentalDays) {
    this.rentalDays = rentalDays;
  }

}
