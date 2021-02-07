package ch.fhnw.eaf.rental.integration;

import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@Ignore
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getUserById_ShouldReturnOK() throws Exception {
        String url = "http://localhost:" + port + "/movierental/users/1";
        webTestClient
            .get()
            .uri(URI.create(url))
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.lastName").isEqualTo("Keller")
            .jsonPath("$.firstName").isEqualTo("Marc")
            .jsonPath("$.rentals.length()").isEqualTo(2)
            .jsonPath("$.rentals[0].movie.title").isEqualTo("Marie Curie");
    }
        
    @Test
    public void getUserById_WrongID_ShouldReturnNotFound() throws Exception {
        fail();
    }

    @Test
    public void getAllUsers_ShouldReturnOK() throws Exception {
        fail();
    }
    
}
