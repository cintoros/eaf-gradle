package ch.fhnw.eaf.rental.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rental.class)
public abstract class Rental_ {

	public static volatile SingularAttribute<Rental, LocalDate> rentalDate;
	public static volatile SingularAttribute<Rental, Movie> movie;
	public static volatile SingularAttribute<Rental, Long> id;
	public static volatile SingularAttribute<Rental, Integer> rentalDays;
	public static volatile SingularAttribute<Rental, User> user;

	public static final String RENTAL_DATE = "rentalDate";
	public static final String MOVIE = "movie";
	public static final String ID = "id";
	public static final String RENTAL_DAYS = "rentalDays";
	public static final String USER = "user";

}

