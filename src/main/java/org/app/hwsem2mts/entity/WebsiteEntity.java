package org.app.hwsem2mts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "website")
public class WebsiteEntity {
  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "website_id", nullable = false)
  private Long id;

  @Getter
  @Setter
  @NotNull(message = "Url cannot be null")
  @Column(name = "website_url", nullable = false)
  private String url;

  protected WebsiteEntity() {}

  public WebsiteEntity(Long id, String url) {
    this.id = id;
    this.url = url;
  }

  @Getter
  @Setter
  @OneToMany(mappedBy = "website", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ArticlesEntity> articles = new ArrayList<>();
}