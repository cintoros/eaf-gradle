package ch.fhnw.eaf.spring.ioc.renderer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import ch.fhnw.eaf.spring.ioc.provider.MessageProvider;

//@Component
//@Primary
public class StandardOutRenderer implements MessageRenderer {

	private MessageProvider provider;

	public void setProvider(MessageProvider provider) {
		this.provider = provider;
	}

	public StandardOutRenderer(MessageProvider provider) {
		this.provider = provider;
		System.out.println("StandardOutRenderer: " + provider);
	}

	@Override
	public void render() {
		System.out.println(provider.getMessage());
	}

}
