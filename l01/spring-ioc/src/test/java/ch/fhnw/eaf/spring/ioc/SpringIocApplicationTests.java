package ch.fhnw.eaf.spring.ioc;

import ch.fhnw.eaf.spring.ioc.provider.WelcomeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringIocApplicationTests {

  @Autowired
  WelcomeProvider welcomeProvider;

  @Test
  void testProviderName() {
    Assertions.assertEquals("Welcome!", welcomeProvider.getMessage());
  }

}
