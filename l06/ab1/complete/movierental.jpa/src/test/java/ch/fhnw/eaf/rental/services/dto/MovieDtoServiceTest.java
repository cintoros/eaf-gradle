package ch.fhnw.eaf.rental.services.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.dto.MovieDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MovieDtoServiceTest {

  @Autowired
  private DtoMovieService movieService;

  private int totalNumberOfMovies;

  @Before
  public void setUp() {
    totalNumberOfMovies = movieService.getAllMovies().size();
  }

  @Test
  public void testChangeTitle() {
    String title = "Marie Curie";
    MovieDto movie = movieService.getMovieById(1L);
    assertEquals(movie.getTitle(), title);

    movie.setTitle("NEW");
    Long id = movieService.saveOrUpdateMovie(movie);

    assertEquals(movie.getId(), id);
    MovieDto updatedMovie = movieService.getMovieById(1L);
    System.out.println(updatedMovie);
    assertEquals("NEW", updatedMovie.getTitle());
  }

  @Test
  public void testCreateMovie() {
    List<MovieDto> movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies, movies.size());

    Long category = movies.get(0).getPriceCategoryId();

    MovieDto movie = new MovieDto("testMovie", LocalDate.now(), category);
    Long id = movieService.saveOrUpdateMovie(movie);

    movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies + 1, movies.size());

    MovieDto movie2 = movieService.getMovieById(id);
    assertTrue(movie.getReleaseDate().equals(movie2.getReleaseDate()));
    assertTrue(movie.getPriceCategoryId().equals(movie2.getPriceCategoryId()));
    assertTrue(movie.getTitle().equals(movie2.getTitle()));
  }

  @Test
  public void testDeleteMovie() {
    List<MovieDto> movies = movieService.getAllMovies();

    assertEquals(totalNumberOfMovies, movies.size());

    MovieDto movie = null;
    String title = "Die göttliche Ordnung";
    for (MovieDto m : movies) {
      if (m.getTitle().equals(title)) {
        movie = m;
      }
    }
    assertTrue("Movie " + title + "not found", movie != null);

    movieService.deleteMovie(movie.getId());

    movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies - 1, movies.size());
  }

  @Test(expected = RuntimeException.class)
  public void testDeleteRentedMovie() {
    MovieDto movie = movieService.getMovieById(1L);
    assertTrue(movie.isRented());
    movieService.deleteMovie(movie.getId());
  }

  @Test(expected = RuntimeException.class)
  public void testDeleteMovieUsedByRental() {
    List<MovieDto> movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies, movies.size());

    MovieDto movie = movies.get(0);
    assertEquals("Marie Curie", movie.getTitle());
    movieService.deleteMovie(movie.getId());
  }

  @Test
  public void testGetByTitle() {
    List<MovieDto> movies = movieService.getAllMovies();
    MovieDto m = movies.get(0);

    movies = movieService.getMoviesByTitle(m.getTitle());
    assertTrue("result must contain movie m", movies.size() > 0);
    assertTrue("result must contain movie m", movies.contains(m));
  }

  @Test
  public void testDeleteAndInsertMovie() {
    List<MovieDto> movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies, movies.size());

    MovieDto movie = movies.get(4);

    movieService.deleteMovie(movie.getId());

    movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies - 1, movies.size());

    Long id = movieService.saveOrUpdateMovie(movie);
    System.out.println(id);
    movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies, movies.size());
  }

  @Test
  public void changeMovie() {
    List<MovieDto> movies = movieService.getAllMovies();
    assertEquals(totalNumberOfMovies, movies.size());
    MovieDto m1 = movies.get(1);
    MovieDto m2 = new MovieDto("####", m1.getReleaseDate(), m1.getPriceCategoryId());
    m2.setId(m1.getId());
    m2.setRented(m1.isRented());
    movieService.saveOrUpdateMovie(m2);

    m1 = movieService.getMovieById(m1.getId());
    assertEquals(m2.getTitle(), m1.getTitle());
  }

}
