package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.WebsiteEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WebsiteRepository {
  private final List<WebsiteEntity> websites = new ArrayList<>();

  public WebsiteEntity addWebsite(WebsiteEntity website) {
    websites.add(website);
    return website;
  }

  public List<WebsiteEntity> getWebsites() {
    return websites;
  }

  public Optional<WebsiteEntity> getWebsiteById(Long id) {
    return websites.stream()
            .filter(website -> website.getId().equals(id))
            .findFirst();
  }

  public boolean deleteWebsiteById(Long id) {
    return websites.removeIf(website -> website.getId().equals(id));
  }
}