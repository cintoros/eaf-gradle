package ch.fhnw.eaf.mappers;

import ch.fhnw.eaf.rental.dto.RentalDto;
import ch.fhnw.eaf.rental.dto.UserDto;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  @Mapping(source = "rentals", target = "rentalIds")
  UserDto userToUserDto(User user);

  RentalDto rentalToRentalDto(Rental rental);

  default Long rentalToLong(Rental r) {
    return r.getId();
  }

}
