package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.WebsiteRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsiteService {
  private final WebsiteRepository websiteRepository;

  public List<WebsiteEntity> findAll() {
    log.info("all websites found");
    return websiteRepository.getWebsites();
  }

  public boolean deleteWebsite(Long id) {
    log.info("deleting website {}", id);
    return websiteRepository.deleteWebsiteById(id);
  }

  /*Пытаемся получить вебсайт по ID с повторными попытками через каждые 10 секунд, 5 раз
   * например, может быть недоступен сервер или задержка появления этих данных в базе*/
  @Retryable(
          retryFor = EntityNotFoundException.class,
          maxAttempts = 5,
          backoff = @Backoff(delay = 10_000)
  )
  public WebsiteEntity getWebsiteById(Long id) {
    log.info("getting website {}", id);
    return websiteRepository.getWebsiteById(id)
            .orElseThrow(EntityNotFoundException::new);
  }

  public WebsiteEntity addWebsite(WebsiteEntity website) {
    log.info("adding website {}", website);
    return websiteRepository.addWebsite(website);
  }
}