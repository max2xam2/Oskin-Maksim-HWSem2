package org.app.hwsem2mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableRetry
public class Hwsem2MtsApplication {

  public static void main(String[] args) {
    SpringApplication.run(Hwsem2MtsApplication.class, args);
  }
}