package ch.fhnw.eaf.rental.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile ListAttribute<User, Rental> rentals;
	public static volatile SingularAttribute<User, String> email;

	public static final String LAST_NAME = "lastName";
	public static final String FIRST_NAME = "firstName";
	public static final String ID = "id";
	public static final String RENTALS = "rentals";
	public static final String EMAIL = "email";

}

