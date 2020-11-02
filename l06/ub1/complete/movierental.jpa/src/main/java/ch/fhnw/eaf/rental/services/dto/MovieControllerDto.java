package ch.fhnw.eaf.rental.services.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.eaf.rental.model.dto.MovieDto;

@RestController
@RequestMapping(path = "/movierentaldto")
public class MovieControllerDto {

  @Autowired
  private DtoMovieService movieService;

  @GetMapping(path = "/movies")
  public List<MovieDto> getAllMovies() {
    return movieService.getAllMovies();
  }

  @PostMapping(path = "/movies")
  public Long createMovie(@RequestBody MovieDto movie) {
    return movieService.saveOrUpdateMovie(movie);
  }

  @GetMapping(path = "/movies/{id}")
  public MovieDto getMovie(@PathVariable Long id) {
    return movieService.getMovieById(id);
  }

  @DeleteMapping(path = "/movies/{id}")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
  }

  @PutMapping(path = "/movies/{id}")
  public Long updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
    assert id == movie.getId();
    return movieService.saveOrUpdateMovie(movie);
  }

}
