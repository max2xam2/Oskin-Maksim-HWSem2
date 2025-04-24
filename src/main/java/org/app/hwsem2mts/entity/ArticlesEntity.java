package org.app.hwsem2mts.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesEntity {
  private Long id;
  private String title;
  private String url;
}