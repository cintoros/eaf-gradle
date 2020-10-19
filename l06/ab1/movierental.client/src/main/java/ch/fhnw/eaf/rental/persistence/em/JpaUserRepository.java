package ch.fhnw.eaf.rental.persistence.em;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Profile("em")
public class JpaUserRepository extends AbstractJpaRepository<User> implements UserRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<User> findByLastName(String lastName) {
    return em.createNamedQuery(User.FIND_BY_LAST_NAME, User.class)
        .setParameter("name", lastName)
        .getResultList();
  }

  @Override
  public List<User> findByFirstName(String firstName) {
    return em.createNamedQuery(User.FIND_BY_FIRST_NAME, User.class)
        .setParameter("name", firstName)
        .getResultList();
  }

  @Override
  public List<User> findByEmail(String email) {
    return em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
        .setParameter("email", email)
        .getResultList();
  }
}
