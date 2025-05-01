package org.app.hwsem2mts.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.hwsem2mts.entity.CategoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Categories Interface", description = "Управление категориями пользователя")
@RestController
@RequestMapping("/api/categories")
public interface CategoryInterfaces {
  @Operation(
          summary = "Добавление категории",
          description = "Добавление новой категории у пользователя"
  )
  @ApiResponse(responseCode = "201", description = "Категория добавлена")
  @PostMapping
  ResponseEntity<CategoryEntity> createCategory(
          @Parameter(description = "Создание категории", required = true)
          @RequestBody CategoryEntity category
  );

  @Operation(
          summary = "Получение всех категорий",
          description = "Получение всех выбранных категорий пользователем"
  )
  @ApiResponse(responseCode = "200", description = "Все категории получены")
  @GetMapping
  ResponseEntity<List<CategoryEntity>> getAllCategories();

  @Operation(
          summary = "Получение категории",
          description = "Получение категории по ее id"
  )
  @ApiResponse(responseCode = "200", description = "Категория по id получена")
  @GetMapping("/{categoryId}")
  ResponseEntity<CategoryEntity> getCategoryById(
          @Parameter(description = "ID категории", required = true)
          @PathVariable Long id
  );

  @Operation(
          summary = "Удаление категории",
          description = "Удаление категории по её id"
  )
  @ApiResponse(responseCode = "204", description = "Категория удалена")
  @ApiResponse(responseCode = "404", description = "Категории по такому id не найдено")
  @DeleteMapping("/{categoryId}")
  ResponseEntity<Void> deleteCategory(
          @Parameter(description = "id категории", required = true)
          @PathVariable Long id
  );
}