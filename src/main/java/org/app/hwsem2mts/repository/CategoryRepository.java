package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}