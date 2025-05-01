package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.app.hwsem2mts.interfaces.ArticlesInterfaces;
import org.app.hwsem2mts.service.ArticlesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticlesController implements ArticlesInterfaces {
  private final ArticlesService articlesService;

  @Override
  public ResponseEntity<List<ArticlesEntity>> getAllArticles() {
    List<ArticlesEntity> all = articlesService.getAll();
    return ResponseEntity.ok(all);
  }

  @Override
  public ResponseEntity<ArticlesEntity> createArticle(ArticlesEntity articlesEntity) {
    ArticlesEntity created = articlesService.createArticle(articlesEntity);
    URI location = URI.create("/api/articles/" + created.getId());
    return ResponseEntity.created(location).body(created);
  }

  @Override
  public ResponseEntity<ArticlesEntity> getArticleByUrl(String url) {
    return articlesService.getArticleByUrl(url)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Void> deleteArticleById(Long id) {
    boolean deleted = articlesService.deleteArticle(id);
    if (deleted) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<ArticlesEntity> updateArticle(Long id, ArticlesEntity updatedArticle) {
    return articlesService.updateArticle(id, updatedArticle)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<ArticlesEntity> patchArticle(Long id, ArticlesEntity partialArticle) {
    return articlesService.patchArticle(id, partialArticle)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }
}