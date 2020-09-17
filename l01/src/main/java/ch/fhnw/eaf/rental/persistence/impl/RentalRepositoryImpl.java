package ch.fhnw.eaf.rental.persistence.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import ch.fhnw.eaf.rental.persistence.UserRepository;

@Component
public class RentalRepositoryImpl implements RentalRepository {
	private Map<Long, Rental> data = new HashMap<Long, Rental>();
	private long nextId = 1;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MovieRepository movieRepo;

	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		save(new Rental(userRepo.findById(1L).get(), movieRepo.findById(1L).get(), 14, LocalDate.of(2019, Month.SEPTEMBER, 28)));
		save(new Rental(userRepo.findById(1L).get(), movieRepo.findById(2L).get(), 14, LocalDate.of(2019, Month.SEPTEMBER, 30)));
		save(new Rental(userRepo.findById(3L).get(), movieRepo.findById(3L).get(), 14, LocalDate.of(2019, Month.SEPTEMBER, 25)));
	}

	@Override
	public Optional<Rental> findById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public List<Rental> findAll() {
		return new ArrayList<Rental>(data.values());
	}

	@Override
	public List<Rental> findByUser(User user) {
		List<Rental> res = new ArrayList<Rental>();
		for(Rental r : data.values()){
			if(r.getUser().equals(user)) res.add(r);
		}
		return res;
	}

	@Override
	public Rental save(Rental rental) {
		if (rental.getId() == null)
			rental.setId(nextId++);
		data.put(rental.getId(), rental);
		return rental;
	}

	@Override
	public void delete(Rental rental) {
		if(rental == null) throw new IllegalArgumentException();
		if (data.get(rental.getId()) == null)
			throw new RuntimeException("Rental entity with id " + rental.getId() + " does not exixt");
		data.remove(rental.getId());
		rental.setId(null);
	}

	@Override
	public void deleteById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		findById(id).ifPresentOrElse(e -> delete(e), () -> {
			throw new RuntimeException("Rental entity with id " + id + " does not exixt");
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
