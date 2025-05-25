package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.ArticlesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<ArticlesEntity, Long> {
  Optional<ArticlesEntity> findByUrl(String url);
}