package ch.fhnw.eaf.jpa.test3;

import ch.fhnw.eaf.jpa.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
public class Test3 implements CommandLineRunner {

  @PersistenceContext
  EntityManager em;

  public static void main(String[] args) {
    new SpringApplicationBuilder(Test3.class).run(args);
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    System.out.println("done");
    System.out.println("inspect now the generated schema using the h2 console");
  }
}
