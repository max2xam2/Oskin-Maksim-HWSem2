package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}