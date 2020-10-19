package ch.fhnw.eaf.spring.ioc.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class HelloWorldProvider implements MessageProvider {

  @Value("${ch.fhnw.eaf.msg:Hello World}")
  String message;

  @Override
  public String getMessage() {
    return message;
  }

}
