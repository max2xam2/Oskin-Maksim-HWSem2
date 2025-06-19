package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@TestPropertySource(locations = "classpath:application-repositorytest.yaml")
public class TestCategoryRepository {
  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("testdb")
          .withUsername("test")
          .withPassword("test");

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void testSaveCategory() {
    CategoryEntity category = new CategoryEntity();
    category.setName("new category");
    CategoryEntity saved = categoryRepository.save(category);
    assertThat(saved.getId()).isNotNull();
    assertThat(saved.getName()).isEqualTo("new category");
  }

  @Test
  void testFindById() {
    CategoryEntity category = new CategoryEntity();
    category.setName("find category by id");
    CategoryEntity saved = categoryRepository.save(category);
    Optional<CategoryEntity> found = categoryRepository.findById(saved.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("find category by id");
  }

  @Test
  void testFindAll() {
    categoryRepository.save(new CategoryEntity(null, "first category"));
    categoryRepository.save(new CategoryEntity(null, "second category"));
    var all = categoryRepository.findAll();
    assertThat(all).hasSizeGreaterThanOrEqualTo(2);
  }

  @Test
  void testDeleteById() {
    CategoryEntity category = new CategoryEntity();
    category.setName("delete category");
    CategoryEntity saved = categoryRepository.save(category);
    categoryRepository.deleteById(saved.getId());
    Optional<CategoryEntity> found = categoryRepository.findById(saved.getId());
    assertThat(found).isEmpty();
  }
}