package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.CategoryEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<CategoryEntity> getAllCategories() {
    log.debug("getAllCategories() method was called");
    return categoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public CategoryEntity getCategoryById(Long id) {
    log.debug("getCategoryById method was called");
    return categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
  }

  @Transactional
  public boolean deleteCategory(Long id) {
    log.debug("deleteCategory method was called");
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Transactional
  public CategoryEntity createCategory(CategoryEntity category) {
    log.debug("create new category");
    return categoryRepository.save(category);
  }
}