package ch.fhnw.eaf.rental.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ch.fhnw.eaf.rental.model.Rental;

@Aspect
@Component
public class TraceAspect {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /*
   * Exercise 1
   * Intercept inbound call to any getAll-methods of our 'rental' application/package
   */
  @Before("execution(* *..rental..*.getAll*(..))")
  public void logGetAllBefore(JoinPoint jp) {
    logger.debug("Called logFindAllBefore() before calling " + jp.getSignature());
  }


  /*
   * Exercise 2
   * Intercept any call to any method in any package in 'ch.fhnw.eaf.rental.controllers'
   */
  // method execution only in Spring AOP!
  //  @Around("within(*..controllers..*)")
  // @Around("execution(* *..controllers..*.*(..))")
  public Object traceService(ProceedingJoinPoint pjp) throws Throwable {
    logger.debug("Calling Service in " + pjp.getSignature());
    Object o = pjp.proceed();
    logger.debug("Returning from Service called in " + pjp.getSignature());
    return o;
  }

  /*
   * Exercise 3
   * Intercept inbound call to any PageController-methods using one parameter of type Long
   */
  // @Before("execution(* *..*Controller.*(..)) && args(param)")
  // @Before("@within(org.springframework.web.bind.annotation.RestController) && args(param)")
  /*
   * ATTENTION: by using @target spring aop will created a proxy for each spring bean to be able to weave the advice during runtime.
   * This will generate an exception, because the application has not access to all classes (final classes!).
   * To prevent this, the pointcut has to have a stricter description e.g. of the package. Use view "AOP Event Trace" to see the matchings.
   */
  // @Before("@target(org.springframework.web.bind.annotation.RestController) && args(param) && execution(* *..rental..*(..))")
  public void logCallWithLongParameter(JoinPoint jp, Long param) {
    logger.debug("Calling " + jp.getSignature() + " with parameter of type " + param.getClass().getSimpleName());
  }


  /*
   * Exercise 4
   * Intercept outbound from any get-method returning a Rental instance
   */
  // @AfterReturning(pointcut = "execution(* *..*.rental..*.get*(..))", returning = "response")
  public void logReturningOfRental(ResponseEntity<Rental> response) {
    Rental rental = response.getBody();
    logger.debug("Successfully returned rental[" + rental.getId() + "]");
  }
}

