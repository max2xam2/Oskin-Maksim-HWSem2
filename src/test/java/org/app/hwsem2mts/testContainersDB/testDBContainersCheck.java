package org.app.hwsem2mts.testContainersDB;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("testcontainers")
@Testcontainers
@Slf4j
public class testDBContainersCheck {

  @Container
  public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.3")
          .withDatabaseName("testdb")
          .withUsername("testuser")
          .withPassword("testpassword")
          .withInitScript("init.sql");

  @DynamicPropertySource
  static void datasourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }

  @BeforeAll
  public static void beforeAll() {
    String host = postgresContainer.getHost();
    Integer port = postgresContainer.getFirstMappedPort();
    String jdbcUrl = postgresContainer.getJdbcUrl();
    log.info("Контейнер запущен на : {}", host);
    log.info("Порт контейнера: {}", port);
    log.info("JDBC URL: {}", jdbcUrl);
  }

  @Test
  public void loggingCheckDB() {
    assertTrue(postgresContainer.isRunning());
  }
}