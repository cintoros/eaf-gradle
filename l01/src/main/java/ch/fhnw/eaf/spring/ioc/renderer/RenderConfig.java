package ch.fhnw.eaf.spring.ioc.renderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import ch.fhnw.eaf.spring.ioc.provider.MessageProvider;

@Configuration
public class RenderConfig {
	@Autowired
	private MessageProvider provider;

	@Bean
	@Primary
	MessageRenderer getOutRenderer() {
		return new StandardOutRenderer(provider);
	}

	@Bean
	MessageRenderer getErrRenderer() {
		return new StandardErrRenderer(provider);
	}

}
