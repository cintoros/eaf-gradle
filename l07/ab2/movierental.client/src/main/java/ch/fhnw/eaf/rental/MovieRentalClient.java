package ch.fhnw.eaf.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.rental.gui.BusinessLogic;
import ch.fhnw.eaf.rental.gui.MovieRentalApplicationGui;

@SpringBootApplication
public class MovieRentalClient implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MovieRentalClient.class)
			.headless(false) // we start a GUI and are not headless though
			.web(WebApplicationType.NONE) // set to SERVLET if h2 console should be enabled
			.run(args);
	}

	@Autowired
	BusinessLogic logic;

	@Override
	public void run(String... args) throws Exception {
		java.awt.EventQueue.invokeLater(() -> new MovieRentalApplicationGui(logic).setVisible(true));
	}
}