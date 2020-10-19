package ch.fhnw.eaf.rental.services.dto;

import ch.fhnw.eaf.rental.model.dto.RentalDto;

import java.util.Date;
import java.util.List;

public interface DtoRentalService {
  List<RentalDto> getAllRentals();

  RentalDto getRentalById(Long id);

  void deleteRental(Long id);

  int calcRemainingDaysOfRental(RentalDto rental, Date date);
}
