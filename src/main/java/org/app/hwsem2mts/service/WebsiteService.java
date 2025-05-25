package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.WebsiteRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsiteService {
  private final WebsiteRepository websiteRepository;

  @Transactional
  public List<WebsiteEntity> findAll() {
    log.info("all websites found");
    return websiteRepository.findAll();
  }

  @Transactional
  public boolean deleteWebsite(Long id) {
    log.info("deleting website {}", id);
    if (websiteRepository.existsById(id)) {
      websiteRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  /*Пытаемся получить вебсайт по ID с повторными попытками через каждые 10 секунд, 5 раз
   * например, может быть недоступен сервер или задержка появления этих данных в базе*/
  @Retryable(
          retryFor = EntityNotFoundException.class,
          maxAttempts = 5,
          backoff = @Backoff(delay = 10_000)
  )
  public WebsiteEntity getWebsiteById(Long id) {
    log.info("getting website {}", id);
    return websiteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
  }

  @Transactional
  public WebsiteEntity addWebsite(WebsiteEntity website) {
    log.info("adding website {}", website);
    return websiteRepository.save(website);
  }
}