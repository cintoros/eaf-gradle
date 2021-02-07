package ch.fhnw.eaf.rental.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;
import ch.fhnw.eaf.rental.services.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    private Log log = LogFactory.getLog(this.getClass());

    final private MovieRepository movieRepo;
    final private PriceCategoryRepository priceCategoryRepo;

    // @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, PriceCategoryRepository priceCategoryRepository) {
        this.movieRepo = movieRepository;
        this.priceCategoryRepo = priceCategoryRepository;
    }
    // @PostConstruct
    // private void foo() {
    // System.out.println(">> MovieServiceImpl has been constructed");
    // System.out.println(">> "+ movieRepo);
    // System.out.println(">> "+ movieRepo.getClass().getName());
    // }

    public Movie getMovieById(Long id) {
        return movieRepo.findById(id).orElse(null);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        log.debug("getAllMovies() done");
        return movies;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepo.findByTitle(title);
    }

    public Movie saveMovie(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("'movie' parameter is not set!");
        }
        movie = movieRepo.save(movie);
        log.debug("saved or updated movie[" + movie.getId() + "]");
        return movie;
    }

    public void deleteMovie(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("'movie' parameter is not set!");
        }
        if (movie.isRented()) {
            throw new RuntimeException("movie is still used");
        }

        movieRepo.delete(movie);

        if (log.isDebugEnabled()) {
            log.debug("movie[" + movie.getId() + "] deleted");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<PriceCategory> getAllPriceCategories() {
        return priceCategoryRepo.findAll();
    }

}
