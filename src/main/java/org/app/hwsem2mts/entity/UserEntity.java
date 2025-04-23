package org.app.hwsem2mts.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  private Long id;
  private String email;
  private String name;
}
