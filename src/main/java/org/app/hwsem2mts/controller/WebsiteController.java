package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.app.hwsem2mts.service.WebsiteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/website")
@RequiredArgsConstructor
public class WebsiteController {
  private final WebsiteService websiteService;

  @GetMapping
  public List<WebsiteEntity> getAllWebsites() {
    return websiteService.findAll();
  }

  @PostMapping
  public WebsiteEntity createWebsite(@RequestBody WebsiteEntity website) {
    return websiteService.addWebsite(website);
  }

  @GetMapping("/{id}")
  public Optional<WebsiteEntity> getWebsiteById(@PathVariable Long id) {
    return websiteService.getWebsiteById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteWebsiteById(@PathVariable Long id) {
    websiteService.deleteWebsite(id);
  }
}