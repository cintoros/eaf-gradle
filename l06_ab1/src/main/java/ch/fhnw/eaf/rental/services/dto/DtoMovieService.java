package ch.fhnw.eaf.rental.services.dto;

import ch.fhnw.eaf.rental.model.dto.MovieDto;

import java.util.List;

public interface DtoMovieService {
  List<MovieDto> getAllMovies();

  MovieDto getMovieById(Long id);

  List<MovieDto> getMoviesByTitle(String title);

  Long saveOrUpdateMovie(MovieDto movie);

  void deleteMovie(Long id);
}
