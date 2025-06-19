package org.app.hwsem2mts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "articles")
public class ArticlesEntity {
  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "article_id", nullable = false)
  private Long id;

  @Getter
  @Setter
  @NotNull(message = "Title cannot be null")
  @Column(name = "article_title", nullable = false)
  private String title;

  @Getter
  @Setter
  @NotNull(message = "URL cannot be null")
  @Column(name = "article_url", nullable = false)
  private String url;

  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  @NotNull
  private CategoryEntity category;

  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "website_id", nullable = false)
  @NotNull
  private WebsiteEntity website;

  protected ArticlesEntity() {}

  public ArticlesEntity(Long id,String title, String url) {
    this.id = id;
    this.title = title;
    this.url = url;
  }
}