package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.app.hwsem2mts.repository.ArticlesRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

  @Cacheable(value = "articlesByUrl", key = "#url")
  public Optional<ArticlesEntity> getArticleByUrl(String url) {
    log.debug("getArticleByUrl() method was called");
    return articlesRepository.getArticleByUrl(url);
  }

  @CacheEvict(value = "fullUpdateArticle", key = "#id")
  public Optional<ArticlesEntity> updateArticle(Long id, ArticlesEntity updatedArticle) {
    log.debug("updateArticle() method was called");
    return articlesRepository.updateArticle(id, updatedArticle);
  }

  @CacheEvict(value = "updateArticle", key = "#id")
  public Optional<ArticlesEntity> patchArticle(Long id, ArticlesEntity partialArticle) {
    log.debug("patchArticle() method was called");
    return articlesRepository.patchArticle(id, partialArticle);
  }
}