package ch.fhnw.eaf.rental;

import ch.fhnw.eaf.rental.gui.BusinessLogic;
import ch.fhnw.eaf.rental.gui.MovieRentalApplicationGui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MovieRentalClient implements CommandLineRunner {

  @Autowired
  BusinessLogic logic;

  public static void main(String[] args) {
    new SpringApplicationBuilder(MovieRentalClient.class)
        .headless(false) // we start a GUI and are not headless though
        .web(WebApplicationType.NONE) // set to SERVLET if h2 console should be enabled
        .run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    java.awt.EventQueue.invokeLater(() -> new MovieRentalApplicationGui(logic).setVisible(true));
  }
}
