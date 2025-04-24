package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.CategoryEntity;
import org.app.hwsem2mts.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryEntity> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public Optional<CategoryEntity> getCategoryById(@RequestParam Long id) {
    return categoryService.getCategoryById(id);
  }

  @PostMapping
  public CategoryEntity createCategory(@RequestBody CategoryEntity category) {
    return categoryService.createCategory(category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}