package ch.fhnw.eaf.rental.persistence.crit;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.model.User_;
import ch.fhnw.eaf.rental.persistence.UserRepository;

@Repository
@Profile("crit")
public class JpaUserRepository extends AbstractJpaRepository<User> implements UserRepository {

	public JpaUserRepository() {
		super(User.class);
	}

	@Override
	public List<User> findByLastName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		
		// SELECT u FROM User u
		Root<User> u = criteriaQuery.from(User.class);
		criteriaQuery.select(u);
		
		// WHERE u.lastName = :name
		criteriaQuery.where(builder.equal(u.get(User_.lastName), name));
		
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<User> findByFirstName(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		
		// SELECT u FROM User u
		Root<User> u = criteriaQuery.from(User.class);
		criteriaQuery.select(u);
		
		// WHERE u.lastName = :name
		criteriaQuery.where(builder.equal(u.get(User_.firstName), name));
		
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<User> findByEmail(String email) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		
		// SELECT u FROM User u
		Root<User> u = criteriaQuery.from(User.class);
		criteriaQuery.select(u);
		
		// WHERE u.lastName = :name
		criteriaQuery.where(builder.equal(u.get(User_.email), email));
		
		return em.createQuery(criteriaQuery).getResultList();
	}

}
