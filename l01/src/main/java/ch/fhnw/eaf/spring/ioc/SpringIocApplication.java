package ch.fhnw.eaf.spring.ioc;

import ch.fhnw.eaf.spring.ioc.renderer.MessageRenderer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class SpringIocApplication implements CommandLineRunner {

  @Autowired
  MessageRenderer renderer;
  @Autowired
  BeanFactory ctx;

  public static void main(String[] args) {
    SpringApplication.run(SpringIocApplication.class, args);
  }

  @Override
  public void run(String... args) {
    // System.out.println("Hello World");
    System.out.println(renderer);
    renderer.render();

    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }

}
