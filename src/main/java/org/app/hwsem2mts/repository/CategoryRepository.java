package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
  List<CategoryEntity> categories = new ArrayList<>();

  public List<CategoryEntity> getAll() {
    return new ArrayList<>(categories);
  }

  public CategoryEntity save(CategoryEntity category) {
    categories.add(category);
    return category;
  }

  public Optional<CategoryEntity> getById(Long id) {
    return categories.stream()
            .filter(category -> category.getId().equals(id))
            .findFirst();
  }

  public boolean deleteById(Long id) {
    return categories.removeIf(category -> category.getId().equals(id));
  }
}