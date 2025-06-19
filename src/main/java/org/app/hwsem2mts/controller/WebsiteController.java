package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.interfaces.WebsiteInterfaces;
import org.app.hwsem2mts.service.WebsiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class WebsiteController implements WebsiteInterfaces {
  private final WebsiteService websiteService;

  @Override
  public ResponseEntity<List<WebsiteEntity>> getAllWebsites() {
    List<WebsiteEntity> websites = websiteService.findAll();
    return ResponseEntity.ok(websites);
  }

  @Override
  public ResponseEntity<WebsiteEntity> createWebsite(@RequestBody WebsiteEntity website) {
    WebsiteEntity created = websiteService.addWebsite(website);
    URI location = URI.create("/api/websites/" + created.getId());
    return ResponseEntity.created(location).body(created);
  }

  @Override
  public ResponseEntity<WebsiteEntity> getWebsiteById(@PathVariable Long id) {
    try {
      WebsiteEntity website = websiteService.getWebsiteById(id);
      return ResponseEntity.ok(website);
    } catch (EntityNotFoundException exception) {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<Void> deleteWebsiteById(@PathVariable Long id) {
    try {
      websiteService.deleteWebsite(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException exception) {
      return ResponseEntity.notFound().build();
    }
  }
}