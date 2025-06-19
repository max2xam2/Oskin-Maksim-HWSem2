package org.app.hwsem2mts.aspect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoggingAspectTest {

  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private LoggingAspect loggingAspect;

  @Test
  public void testControllerAspect() {
    int before = loggingAspect.getCount();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = System.out;
    System.setOut(new PrintStream(outputStream));
    ResponseEntity<String> response = restTemplate.getForEntity("/api/users", String.class);
    System.setOut(printStream);
    String log = outputStream.toString();
    System.out.println(log);
    Assertions.assertThat(log).contains("getAllUsers");
    int after = loggingAspect.getCount();
    Assertions.assertThat(after).isEqualTo(before + 2);
  }
}
