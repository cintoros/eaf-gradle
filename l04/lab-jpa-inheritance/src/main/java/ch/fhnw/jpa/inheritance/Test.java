package ch.fhnw.jpa.inheritance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootApplication
public class Test implements CommandLineRunner {

  @PersistenceContext
  EntityManager em;

  public static void main(String[] args) {
    new SpringApplicationBuilder(Test.class).run(args);
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    test1();
    // test2();
  }

  private void test1() {
    Post p = new Post("First Post", "Dominik");
    Announcement a = new Announcement("First Announcement", "Dominik", LocalDate.now());
    em.persist(p);
    em.persist(a);
  }

  private void test2() {
    TypedQuery<Topic> q = em.createQuery("SELECT t FROM Topic t", Topic.class);
    for (Topic t : q.getResultList()) {
      System.out.println(t.getClass().getSimpleName());
    }
  }
}
