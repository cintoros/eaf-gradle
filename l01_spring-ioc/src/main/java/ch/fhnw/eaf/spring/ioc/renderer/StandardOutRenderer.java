package ch.fhnw.eaf.spring.ioc.renderer;

import ch.fhnw.eaf.spring.ioc.provider.MessageProvider;

//@Component
//@Primary
public class StandardOutRenderer implements MessageRenderer {

  private MessageProvider provider;

  public StandardOutRenderer(MessageProvider provider) {
    this.provider = provider;
    System.out.println("StandardOutRenderer: " + provider);
  }

  public void setProvider(MessageProvider provider) {
    this.provider = provider;
  }

  @Override
  public void render() {
    System.out.println(provider.getMessage());
  }

}
