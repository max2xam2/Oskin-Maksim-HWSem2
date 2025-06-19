package org.app.hwsem2mts.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Tag(name = "Articles Interface", description = "Управление статьями")
@RequestMapping("/api/articles")
public interface ArticlesInterfaces {
  @Operation(summary = "Создать новую статью", description = "Создает новую статью на основе переданных данных")
  @ApiResponse(responseCode = "201", description = "Статья успешно создана")
  @PostMapping
  ResponseEntity<ArticlesEntity> createArticle(
          @Parameter(description = "Данные для создания статьи", required = true)
          @RequestBody ArticlesEntity articlesEntity
  );

  @Operation(summary = "Получить все статьи", description = "Возвращает список всех статей")
  @ApiResponse(responseCode = "200", description = "Статьи получены")
  @GetMapping
  ResponseEntity<List<ArticlesEntity>> getAllArticles();

  @Operation(summary = "Получить статью по URL", description = "Возвращает статью по указанному URL")
  @ApiResponse(responseCode = "200", description = "Статья найдена")
  @ApiResponse(responseCode = "404", description = "Статья не найдена")
  @GetMapping("/{url}")
  ResponseEntity<ArticlesEntity> getArticleByUrl(
          @Parameter(description = "URL статьи", required = true)
          @RequestParam String url
  );

  @Operation(summary = "Удалить статью по ID", description = "Удаляет статью по ее ID")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Статья успешно удалена"),
          @ApiResponse(responseCode = "404", description = "Статья не найдена")
  })
  @DeleteMapping("/{articleId}")
  ResponseEntity<Void> deleteArticleById(
          @Parameter(description = "ID статьи", required = true)
          @PathVariable Long id
  );

  @Operation(summary = "Обновить статью по ID", description = "Полностью обновляет статью по ID")
  @PutMapping("/{articleId}")
  ResponseEntity<ArticlesEntity> updateArticle(
          @PathVariable Long id,
          @RequestBody ArticlesEntity updatedArticle
  );

  @Operation(summary = "Частично обновить статью по ID", description = "Частично обновляет статью по ID")
  @PatchMapping("/{articleId}")
  ResponseEntity<ArticlesEntity> patchArticle(
          @PathVariable Long id,
          @RequestBody ArticlesEntity partialArticle
  );
}