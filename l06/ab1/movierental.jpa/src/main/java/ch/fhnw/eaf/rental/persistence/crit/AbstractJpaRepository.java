package ch.fhnw.eaf.rental.persistence.crit;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import ch.fhnw.eaf.rental.persistence.Repository;

public abstract class AbstractJpaRepository<T> implements Repository<T, Long> {

	@PersistenceContext
	protected EntityManager em;

	final private Class<T> type;

	protected AbstractJpaRepository(Class<T> type) {
		this.type = type;
	}

	@Override
	public Optional<T> findById(Long id) {
		return Optional.ofNullable(em.find(type, id));
	}

	@Override
	public List<T> findAll() {
		CriteriaQuery<T> builder = em.getCriteriaBuilder().createQuery(type);
		return em.createQuery(builder.select(builder.from(type))).getResultList();
	}

	@Override
	public T save(T entity) {
//		PersistenceUnitUtil puu = em.getEntityManagerFactory().getPersistenceUnitUtil();
//		if (puu.getIdentifier(entity) != null) {
//			return em.merge(entity);
//		} else {
//			em.persist(entity);
//			return entity;
//		}
		return em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void deleteById(Long id) {
		em.remove(em.getReference(type, id));
	}

	@Override
	public boolean existsById(Long id) {
		return em.find(type, id) != null;
		// improve the above implementation to avoid creation of the entity
	}

	@Override
	public long count() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		return em.createQuery(criteriaQuery.select(builder.count(criteriaQuery.from(type)))).getSingleResult();
	}
}
