package org.app.hwsem2mts.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "users")
public class UserEntity {
  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long id;

  @Getter
  @Setter
  @NotNull(message = "Email is required")
  @Column(name = "user_email", nullable = false)
  private String email;

  @Getter
  @Setter
  @NotNull(message = "Name is required")
  @Column(name = "user_name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "user", fetch = LAZY)
  private final Set<CategoryEntity> categories = new HashSet<>();

  protected UserEntity() {}

  public UserEntity(Long id,String email, String name) {
    this.id = id;
    this.email = email;
    this.name = name;
  }
}