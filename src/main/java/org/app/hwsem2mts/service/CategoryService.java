package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.CategoryEntity;
import org.app.hwsem2mts.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<CategoryEntity> getAllCategories() {
    log.debug("getAllCategories() method was called");
    return categoryRepository.getAll();
  }

  public Optional<CategoryEntity> getCategoryById(Long id) {
    log.debug("getCategoryById method was called");
    return categoryRepository.getById(id);
  }

  public boolean deleteCategory(Long id) {
    log.debug("deleteCategory method was called");
    return categoryRepository.deleteById(id);
  }

  public CategoryEntity createCategory(CategoryEntity category) {
    log.debug("create new category");
    return categoryRepository.save(category);
  }
}