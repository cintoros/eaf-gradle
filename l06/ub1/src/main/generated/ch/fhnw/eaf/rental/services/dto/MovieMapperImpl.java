package ch.fhnw.eaf.rental.services.dto;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.model.dto.MovieDto;
import ch.fhnw.eaf.rental.model.dto.RentalDto;
import ch.fhnw.eaf.rental.model.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-19T08:55:11+0200",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 14.0.2 (N/A)"
)
@Component
public class MovieMapperImpl extends MovieMapper {

  @Override
  public UserDto userToUserDto(User user) {
    if (user == null) {
      return null;
    }

    UserDto userDto = new UserDto();

    userDto.setRentalIds(rentalListToLongSet(user.getRentals()));
    if (user.getId() != null) {
      userDto.setId(user.getId());
    }
    userDto.setLastName(user.getLastName());
    userDto.setFirstName(user.getFirstName());
    userDto.setEmail(user.getEmail());

    return userDto;
  }

  @Override
  public MovieDto movieToMovieDto(Movie movie) {
    if (movie == null) {
      return null;
    }

    MovieDto movieDto = new MovieDto();

    movieDto.setPriceCategoryId(priceCategoryToLong(movie.getPriceCategory()));
    movieDto.setId(movie.getId());
    movieDto.setTitle(movie.getTitle());
    movieDto.setReleaseDate(movie.getReleaseDate());
    movieDto.setRented(movie.isRented());

    return movieDto;
  }

  @Override
  public RentalDto rentalToRentalDto(Rental rental) {
    if (rental == null) {
      return null;
    }

    RentalDto rentalDto = new RentalDto();

    Long id = rentalUserId(rental);
    if (id != null) {
      rentalDto.setUserId(id);
    }
    rentalDto.setDays(rental.getRentalDays());
    if (rental.getId() != null) {
      rentalDto.setId(rental.getId());
    }

    return rentalDto;
  }

  @Override
  public Movie movieDtoToMovie(MovieDto movie) {
    if (movie == null) {
      return null;
    }

    Movie movie1 = new Movie();

    movie1.setPriceCategory(longToPriceCategory(movie.getPriceCategoryId()));
    movie1.setTitle(movie.getTitle());
    movie1.setReleaseDate(movie.getReleaseDate());
    movie1.setRented(movie.isRented());
    movie1.setId(movie.getId());

    return movie1;
  }

  protected Set<Long> rentalListToLongSet(List<Rental> list) {
    if (list == null) {
      return null;
    }

    Set<Long> set = new HashSet<Long>(Math.max((int) (list.size() / .75f) + 1, 16));
    for (Rental rental : list) {
      set.add(rentalToLong(rental));
    }

    return set;
  }

  private Long rentalUserId(Rental rental) {
    if (rental == null) {
      return null;
    }
    User user = rental.getUser();
    if (user == null) {
      return null;
    }
    Long id = user.getId();
    if (id == null) {
      return null;
    }
    return id;
  }
}
