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

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.persistence.MovieRepository;
import ch.fhnw.eaf.rental.persistence.PriceCategoryRepository;

@Component
public class MovieRepositoryImpl implements MovieRepository {
	private Map<Long, Movie> data = new HashMap<Long, Movie>();
	private long nextId = 1;

	@Autowired
	private PriceCategoryRepository priceCategoryRepo;

	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		
		save(new Movie("Marie Curie",             LocalDate.of(2017, Month.JUNE, 2),       priceCategoryRepo.findById(1L).get()));
		save(new Movie("Curchill",                LocalDate.of(2017, Month.SEPTEMBER, 20), priceCategoryRepo.findById(1L).get()));
		save(new Movie("The Boss Baby",           LocalDate.of(2017, Month.AUGUST, 3),     priceCategoryRepo.findById(2L).get()));
		save(new Movie("Pirates of the Caribean: Salazar's Revenge", 
		                                          LocalDate.of(2017, Month.OCTOBER, 2),    priceCategoryRepo.findById(1L).get()));
		save(new Movie("Die göttliche Ordnung",   LocalDate.of(2017, Month.SEPTEMBER, 21), priceCategoryRepo.findById(1L).get()));

		save(new Movie("Loving Vincent",          LocalDate.of(2018, Month.MAY, 25),       priceCategoryRepo.findById(1L).get()));
		save(new Movie("Fast & Furious 7",        LocalDate.of(2018, Month.AUGUST, 13),    priceCategoryRepo.findById(1L).get()));
		save(new Movie("Momo",                    LocalDate.of(2018, Month.OCTOBER, 1),    priceCategoryRepo.findById(2L).get()));
		save(new Movie("Swimming with Men",       LocalDate.of(2018, Month.OCTOBER, 3),    priceCategoryRepo.findById(1L).get()));
		save(new Movie("Jurassic World",          LocalDate.of(2018, Month.OCTOBER, 22),   priceCategoryRepo.findById(2L).get()));

		save(new Movie("Bohemian Rhapsody",       LocalDate.of(2019, Month.FEBRUARY, 28),  priceCategoryRepo.findById(1L).get()));
		save(new Movie("Wolkenbruch",             LocalDate.of(2019, Month.MARCH, 13),     priceCategoryRepo.findById(1L).get()));
		save(new Movie("The old Man and the Gun", LocalDate.of(2019, Month.SEPTEMBER, 4),  priceCategoryRepo.findById(1L).get()));
		save(new Movie("Stan and Ollie",          LocalDate.of(2019, Month.SEPTEMBER, 20), priceCategoryRepo.findById(2L).get()));
		save(new Movie("Zwingli",                 LocalDate.of(2019, Month.SEPTEMBER, 25), priceCategoryRepo.findById(1L).get()));
		
		save(new Movie("Downtown Abbey",          LocalDate.of(2020, Month.JANUARY, 30),   priceCategoryRepo.findById(1L).get()));
		save(new Movie("Gut geten Nordwind",      LocalDate.of(2020, Month.MARCH, 12),     priceCategoryRepo.findById(3L).get()));
		save(new Movie("Moskau einfach!",         LocalDate.of(2020, Month.MAY, 1),        priceCategoryRepo.findById(3L).get()));
		save(new Movie("The Invisible Man",       LocalDate.of(2020, Month.JULY, 9),       priceCategoryRepo.findById(3L).get()));
	}

	@Override
	public Optional<Movie> findById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public List<Movie> findAll() {
		return new ArrayList<Movie>(data.values());
	}

	@Override
	public List<Movie> findByTitle(String name) {
		List<Movie> result = new ArrayList<Movie>();
		for(Movie m : data.values()){
			if(m.getTitle().equals(name)) result.add(m);
		}
		return result;
	}

	@Override
	public Movie save(Movie movie) {
		if (movie.getId() == null)
			movie.setId(nextId++);
		data.put(movie.getId(), movie);
		return movie;
	}

	@Override
	public void delete(Movie movie) {
		if(movie == null) throw new IllegalArgumentException();
		if (data.get(movie.getId()) == null)
			throw new RuntimeException("Movie entity with id " + movie.getId() + " does not exixt");
		data.remove(movie.getId());
		movie.setId(null);
	}

	@Override
	public void deleteById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		findById(id).ifPresentOrElse(e -> delete(e), () -> {
			throw new RuntimeException("Movie entity with id " + id + " does not exixt");
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
