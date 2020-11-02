package ch.fhnw.eaf.rental.persistence.em;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;

@Repository
@Profile("em")
public class JpaMovieRepository extends AbstractJpaRepository<Movie> implements MovieRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Movie> findByTitle(String title) {
    return em.createNamedQuery(Movie.FIND_BY_TITLE, Movie.class)
        .setParameter("title", title)
        .getResultList();
  }

}
