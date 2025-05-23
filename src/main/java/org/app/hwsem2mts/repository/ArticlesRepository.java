package org.app.hwsem2mts.repository;

import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
public class ArticlesRepository {
  private final WebClient webClient = WebClient.create();
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
    RestTemplate restTemplate = new RestTemplate();
    String apiResponse = restTemplate.getForObject("https://habr.com/", String.class);
    System.out.println("Habr API Response: " + apiResponse);
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
    String response = webClient
            .get()
            .uri("https://habr.com/ru/feed/")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    log.info(response);
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