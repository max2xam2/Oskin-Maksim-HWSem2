package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.ArticlesEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticlesRepository {
  List<ArticlesEntity> articles = new ArrayList<>();

  public List<ArticlesEntity> getAllArticles() {
    return new ArrayList<>(articles);
  }

  public ArticlesEntity createArticle(ArticlesEntity article) {
    articles.add(article);
    return article;
  }

  public boolean deleteArticleById(Long id) {
    return articles.removeIf(article -> article.getId().equals(id));
  }

  public Optional<ArticlesEntity> getArticleByUrl(String url) {
    return articles.stream()
            .filter(article -> article.getUrl().equals(url))
            .findFirst();
  }

  public Optional<ArticlesEntity> getArticleById(Long id) {
    return articles.stream()
            .filter(article -> article.getId().equals(id))
            .findFirst();
  }

  public Optional<ArticlesEntity> updateArticle(Long id, ArticlesEntity updatedArticle) {
    for (int i = 0; i < articles.size(); i++) {
      if (articles.get(i).getId().equals(id)) {
        updatedArticle.setId(id);
        articles.set(i, updatedArticle);
        return Optional.of(updatedArticle);
      }
    }
    return Optional.empty();
  }

  public Optional<ArticlesEntity> patchArticle(Long id, ArticlesEntity partialArticle) {
    for (ArticlesEntity article : articles) {
      if (article.getId().equals(id)) {
        if (partialArticle.getTitle() != null) {
          article.setTitle(partialArticle.getTitle());
        }
        if (partialArticle.getUrl() != null) {
          article.setUrl(partialArticle.getUrl());
        }
        return Optional.of(article);
      }
    }
    return Optional.empty();
  }
}