package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.WebsiteRepository;
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