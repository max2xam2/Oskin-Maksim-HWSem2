package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.WebsiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<WebsiteEntity, Long> {
}