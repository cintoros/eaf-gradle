package ch.fhnw.eaf.rental.persistence.em;

import ch.fhnw.eaf.rental.persistence.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaRepository<T> implements Repository<T, Long> {

  private final Class<T> type;
  @PersistenceContext
  private EntityManager em;

  @SuppressWarnings("unchecked")
  protected AbstractJpaRepository() {
    type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public Optional<T> findById(Long id) {
    return Optional.ofNullable(em.find(type, id));
  }

  @Override
  public T save(T m) {
    return em.merge(m);
  }

  @Override
  public void deleteById(Long id) {
    em.remove(em.getReference(type, id));
  }

  @Override
  public void delete(T entity) {
    em.remove(em.merge(entity));
  }

  @Override
  public List<T> findAll() {
    try {
      return em.createNamedQuery((String) type.getField("FIND_ALL").get(null), type).getResultList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean existsById(Long id) {
    try {
      return em.createNamedQuery((String) type.getField("EXISTS").get(null), Long.class)
          .setParameter("id", id)
          .getSingleResult() > 0;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public long count() {
    try {
      return em.createNamedQuery((String) type.getField("COUNT").get(null), Long.class).getSingleResult();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
