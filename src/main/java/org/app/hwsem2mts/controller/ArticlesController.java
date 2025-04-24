package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.app.hwsem2mts.service.ArticlesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {
  private final ArticlesService articlesService;

  @GetMapping
  public List<ArticlesEntity> getAllArticles() {
    return articlesService.getAll();
  }

  @PostMapping
  public ArticlesEntity createArticle(@RequestBody ArticlesEntity articlesEntity) {
    return articlesService.createArticle(articlesEntity);
  }

  @GetMapping("/by-url")
  public ResponseEntity<ArticlesEntity> getArticleByUrl(@RequestParam String url) {
    return articlesService.getArticleByUrl(url)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public void deleteArticleByUrl(@PathVariable Long id) {
    articlesService.deleteArticle(id);
  }
}