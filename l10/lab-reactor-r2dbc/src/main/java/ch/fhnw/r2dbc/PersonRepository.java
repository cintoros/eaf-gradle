package ch.fhnw.r2dbc;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

	@Query("select id, name, age from person where name = $1")
	Flux<Person> findAllByName(String name);

	@Query("select * from person where age = $1")
	Flux<Person> findByAge(int age);
}