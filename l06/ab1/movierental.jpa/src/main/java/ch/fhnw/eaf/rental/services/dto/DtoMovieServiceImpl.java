package ch.fhnw.eaf.rental.services.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.dto.MovieDto;
import ch.fhnw.eaf.rental.services.MovieService;

@Service
@Transactional
public class DtoMovieServiceImpl implements DtoMovieService {
	
	@Autowired
	MovieMapper mapper;
	
	@Autowired
	MovieService movieService;
	
	@Override
	public List<MovieDto> getAllMovies() {
		return movieService.getAllMovies().stream().map(m -> mapper.movieToMovieDto(m)).collect(Collectors.toList());
	}

	@Override
	public MovieDto getMovieById(Long id) {
		return mapper.movieToMovieDto(movieService.getMovieById(id));
	}

	@Override
	public Long saveOrUpdateMovie(MovieDto movie) {
		Movie m = mapper.movieDtoToMovie(movie);
		movieService.saveMovie(m);
		return m.getId();
	}

	@Override
	public void deleteMovie(Long id) {
		movieService.deleteMovie(movieService.getMovieById(id));
	}

	@Override
	public List<MovieDto> getMoviesByTitle(String title) {
		return movieService.getMoviesByTitle(title).stream().map(m -> mapper.movieToMovieDto(m)).collect(Collectors.toList());
	}

}