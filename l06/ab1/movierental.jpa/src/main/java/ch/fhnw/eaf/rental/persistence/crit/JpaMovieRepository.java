package ch.fhnw.eaf.rental.persistence.crit;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Movie_;
import ch.fhnw.eaf.rental.persistence.MovieRepository;

@Repository
@Profile("crit")
public class JpaMovieRepository extends AbstractJpaRepository<Movie> implements MovieRepository {
	
	public JpaMovieRepository() {
		super(Movie.class);
	}

	@Override
	public List<Movie> findByTitle(String title) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Movie> criteriaQuery = builder.createQuery(Movie.class);
		
		// SELECT m FROM Movie m
		Root<Movie> m = criteriaQuery.from(Movie.class);
		criteriaQuery.select(m);
		
		// WHERE m.title = :title
		criteriaQuery.where(builder.equal(m.get(Movie_.title), title));
		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
}
