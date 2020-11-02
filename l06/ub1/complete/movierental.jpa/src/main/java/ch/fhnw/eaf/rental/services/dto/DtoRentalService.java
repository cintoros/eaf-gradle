package ch.fhnw.eaf.rental.services.dto;

import java.util.Date;
import java.util.List;

import ch.fhnw.eaf.rental.model.dto.RentalDto;

public interface DtoRentalService {
  List<RentalDto> getAllRentals();

  RentalDto getRentalById(Long id);

  void deleteRental(Long id);

  int calcRemainingDaysOfRental(RentalDto rental, Date date);
}
