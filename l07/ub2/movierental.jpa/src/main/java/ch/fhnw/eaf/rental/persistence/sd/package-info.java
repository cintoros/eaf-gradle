package ch.fhnw.eaf.rental.persistence.sd;

/*
 * This package implements the repositories using Spring Data.
 * 
 * They can be used when the springdata prifile is activated using
 * spring.profiles.active=springdata
 * 
 * The implementation could have been simplified as the interfaces defined in
 * the package ch.fhnw.eaf.rental.persistence are no longer needed, i.e. instead
 * of the interface
 * 
 *     public interface MovieRepository extends Repository<Movie, Long> {
 *         List<Movie> findByTitle(String title); 
 *     }
 * 
 * we could directly write
 * 
 *     public interface MovieRepository extends JpaRepository<Movie, Long> {
 *         List<Movie> findByTitle(String title);
 *     }
 *
 * The reason for this indirection is to provide different implementations in the
 * same project.
 */