package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.app.hwsem2mts.repository.ArticlesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticlesService {
  private final ArticlesRepository articlesRepository;

  public List<ArticlesEntity> getAll() {
    log.debug("getAll() method was called");
    return articlesRepository.getAllArticles();
  }

  public boolean deleteArticle(Long id) {
    log.debug("deleteArticle() method was called");
    return articlesRepository.deleteArticleById(id);
  }

  public ArticlesEntity createArticle(ArticlesEntity article) {
    log.debug("createArticle() method was called");
    return articlesRepository.createArticle(article);
  }

  public Optional<ArticlesEntity> getArticleByUrl(String url) {
    log.debug("getArticleByUrl() method was called");
    return articlesRepository.getArticleByUrl(url);
  }
}