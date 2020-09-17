package ch.fhnw.eaf.spring.ioc.provider;

import org.springframework.stereotype.Component;

@Component
public class WelcomeProvider implements MessageProvider {

	@Override
	public String getMessage() {
		return "Welcome!";
	}

}
