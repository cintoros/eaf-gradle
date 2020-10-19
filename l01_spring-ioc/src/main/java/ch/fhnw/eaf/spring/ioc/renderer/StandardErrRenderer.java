package ch.fhnw.eaf.spring.ioc.renderer;

import ch.fhnw.eaf.spring.ioc.provider.MessageProvider;

//@Component
public class StandardErrRenderer implements MessageRenderer {

  private MessageProvider provider;

  public StandardErrRenderer(MessageProvider provider) {
    this.provider = provider;
  }

  @Override
  public void render() {
    System.err.println(provider.getMessage());
  }

}
