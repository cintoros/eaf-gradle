package ch.fhnw.eaf.rental.services.dto;

import ch.fhnw.eaf.rental.model.dto.RentalDto;
import ch.fhnw.eaf.rental.model.dto.UserDto;

import java.util.List;

public interface DtoUserService {
  List<UserDto> getAllUsers();

  UserDto getUserById(Long id);

  List<UserDto> getUsersByName(String name);

  Long saveOrUpdateUser(UserDto user);

  void deleteUser(Long id);

  RentalDto rentMovie(Long userId, Long movieId);
}
