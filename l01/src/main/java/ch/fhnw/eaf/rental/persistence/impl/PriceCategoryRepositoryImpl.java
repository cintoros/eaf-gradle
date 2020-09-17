package ch.fhnw.eaf.rental.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.model.PriceCategoryChildren;
import ch.fhnw.eaf.rental.model.PriceCategoryNewRelease;
import ch.fhnw.eaf.rental.model.PriceCategoryRegular;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;

@Component
public class PriceCategoryRepositoryImpl implements PriceCategoryRepository {
	private Map<Long, PriceCategory> data = new HashMap<Long, PriceCategory>();
	private long nextId = 1;

	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		save(new PriceCategoryRegular());
		save(new PriceCategoryChildren());
		save(new PriceCategoryNewRelease());
	}
	
	@Override
	public Optional<PriceCategory> findById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public List<PriceCategory> findAll() {
		return new ArrayList<PriceCategory>(data.values());
	}

	@Override
	public PriceCategory save(PriceCategory category) {
		if (category.getId() == null)
			category.setId(nextId++);
		data.put(category.getId(), category);
		return category;
	}

	@Override
	public void delete(PriceCategory priceCategory) {
		if(priceCategory == null) throw new IllegalArgumentException();
		if (data.get(priceCategory.getId()) == null)
			throw new RuntimeException("PriceCategory entity with id " + priceCategory.getId() + " does not exixt");
		data.remove(priceCategory.getId());
		priceCategory.setId(null);
	}

	@Override
	public void deleteById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		findById(id).ifPresentOrElse(e -> delete(e), () -> {
			throw new RuntimeException("PriceCategory entity with id " + id + " does not exixt");
		});
	}

	@Override
	public boolean existsById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return data.get(id) != null;
	}

	@Override
	public long count() {
		return data.size();
	}

}
