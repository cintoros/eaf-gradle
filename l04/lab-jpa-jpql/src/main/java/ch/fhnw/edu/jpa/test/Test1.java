package ch.fhnw.edu.jpa.test;

import ch.fhnw.edu.jpa.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootApplication
@EntityScan(basePackageClasses = Movie.class)
public class Test1 implements CommandLineRunner {

  @PersistenceContext
  EntityManager em;

  public static void main(String[] args) {
    new SpringApplicationBuilder(Test1.class).run(args);
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    test1();
    System.out.println("done");
    // System.exit(0); // if you do not want to inspect the DB using the h2 console
  }

  private void test1() {
    Movie movie = em.find(Movie.class, 1L);
    System.out.println(movie);
    if (movie != null)
      System.out.println(movie.getTitle());

//		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ...", User.class);
//		query.setParameter("movie", movie.getTitle());
//
//		List<User> users = query.getResultList();
//		if (users.size() == 0) {
//			System.out.println("nicht ausgeliehen");
//		} else {
//			System.out.println("ausgeliehen an " + users.get(0).getEmail());
//		}

  }

}
