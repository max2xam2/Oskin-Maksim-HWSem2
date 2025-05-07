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
}