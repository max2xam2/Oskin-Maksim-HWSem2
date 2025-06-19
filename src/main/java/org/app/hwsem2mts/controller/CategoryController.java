package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.CategoryEntity;
import org.app.hwsem2mts.interfaces.CategoryInterfaces;
import org.app.hwsem2mts.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController implements CategoryInterfaces {
  private final CategoryService categoryService;

  @Override
  public ResponseEntity<List<CategoryEntity>> getAllCategories() {
    List<CategoryEntity> all = categoryService.getAllCategories();
    return ResponseEntity.ok(all);
  }

  @Override
  public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Long id) {
    CategoryEntity category = categoryService.getCategoryById(id);
    if (category != null) {
      return ResponseEntity.ok(category);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
    CategoryEntity categoryEntity = categoryService.createCategory(category);
    URI location = URI.create("/api/categories/" + categoryEntity.getId());
    return ResponseEntity.created(location).body(categoryEntity);
  }

  @Override
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    boolean deleted = categoryService.deleteCategory(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}