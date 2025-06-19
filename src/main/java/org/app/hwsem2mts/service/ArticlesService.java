package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.ArticlesEntity;
import org.app.hwsem2mts.repository.ArticlesRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticlesService {
  private final ArticlesRepository articlesRepository;

  @Transactional(readOnly = true)
  public List<ArticlesEntity> getAll() {
    log.debug("getAll() method was called");
    return articlesRepository.findAll();
  }

  @Transactional
  public boolean deleteArticle(Long id) {
    log.debug("deleteArticle() method was called");
    if (articlesRepository.existsById(id)) {
      articlesRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Transactional
  public ArticlesEntity createArticle(ArticlesEntity article) {
    log.debug("createArticle() method was called");
    return articlesRepository.save(article);
  }

  @Transactional
  @Cacheable(value = "articlesByUrl", key = "#url")
  public Optional<ArticlesEntity> getArticleByUrl(String url) {
    log.debug("getArticleByUrl() method was called");
    return articlesRepository.findByUrl(url);
  }

  @Transactional
  @CacheEvict(value = "fullUpdateArticle", key = "#id")
  public Optional<ArticlesEntity> updateArticle(Long id, ArticlesEntity updatedArticle) {
    log.debug("updateArticle() method was called");
    return articlesRepository.findById(id)
            .map(existing -> {
              existing.setTitle(updatedArticle.getTitle());
              existing.setUrl(updatedArticle.getUrl());
              return articlesRepository.save(existing);
            });
  }

  @Transactional
  @CacheEvict(value = "updateArticle", key = "#id")
  public Optional<ArticlesEntity> patchArticle(Long id, ArticlesEntity partialArticle) {
    log.debug("patchArticle() method was called");
    return articlesRepository.findById(id)
            .map(existing -> {
              if (partialArticle.getTitle() != null) {
                existing.setTitle(partialArticle.getTitle());
              }
              if (partialArticle.getUrl() != null) {
                existing.setUrl(partialArticle.getUrl());
              }
              return articlesRepository.save(existing);
            });
  }
}