package ch.fhnw.eaf.rental.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, Boolean> rented;
	public static volatile SingularAttribute<Movie, LocalDate> releaseDate;
	public static volatile SingularAttribute<Movie, PriceCategory> priceCategory;
	public static volatile SingularAttribute<Movie, Long> id;
	public static volatile SingularAttribute<Movie, String> title;

	public static final String RENTED = "rented";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String PRICE_CATEGORY = "priceCategory";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

