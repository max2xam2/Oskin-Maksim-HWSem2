package org.app.hwsem2mts.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Duration;

@Aspect
@Component
public class LoggingAspect {

  @Before("execution(* org.app.hwsem2mts.controller..*(..))")
  public void nameControllerAspect(JoinPoint joinPoint) {
    System.out.println("Вызван метод контроллера: " + joinPoint.getSignature().getName());
  }

  @Around("execution(* org.app.hwsem2mts.controller..*(..))")
  public Object checkTimeControllerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant startTime = Instant.now();
    Object result = joinPoint.proceed();
    Instant endTime = Instant.now();
    Duration duration = Duration.between(startTime, endTime);
    System.out.println(joinPoint.getSignature().getName() + "выполнялся" + duration.toMillis() + "мс");
    return result;
  }
}