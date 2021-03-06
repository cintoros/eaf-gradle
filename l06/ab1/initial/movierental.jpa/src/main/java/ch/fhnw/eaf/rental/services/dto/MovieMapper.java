package ch.fhnw.eaf.rental.services.dto;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.model.dto.MovieDto;
import ch.fhnw.eaf.rental.model.dto.RentalDto;
import ch.fhnw.eaf.rental.model.dto.UserDto;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

  @Autowired
  PriceCategoryRepository pcRepo;

  @Mapping(source = "rentals", target = "rentalIds")
  public abstract UserDto userToUserDto(User user);

  @Mapping(source = "priceCategory", target = "priceCategoryId")
  public abstract MovieDto movieToMovieDto(Movie movie);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "rentalDays", target = "days")
  public abstract RentalDto rentalToRentalDto(Rental rental);

  @Mapping(source = "priceCategoryId", target = "priceCategory")
  public abstract Movie movieDtoToMovie(MovieDto movie);

  public Long rentalToLong(Rental r) {
    return r.getId();
  }

  public Long priceCategoryToLong(PriceCategory pc) {
    return pc.getId();
  }

  public PriceCategory longToPriceCategory(Long id) {
    return pcRepo.findById(id).get();
  }

}
