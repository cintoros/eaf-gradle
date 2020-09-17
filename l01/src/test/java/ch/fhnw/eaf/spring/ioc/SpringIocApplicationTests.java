package ch.fhnw.eaf.spring.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.fhnw.eaf.spring.ioc.provider.WelcomeProvider;

@SpringBootTest
class SpringIocApplicationTests {

	@Autowired
	WelcomeProvider welcomeProvider;
	
	@Test
	void testProviderName() {
		Assertions.assertEquals("Welcome!", welcomeProvider.getMessage());
	}

}
