package org.app.hwsem2mts.repository;

import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class UserRepository {

  private final List<UserEntity> users = new ArrayList<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  public List<UserEntity> findAll() {
    return users;
  }

  public UserEntity save(UserEntity user) {
    user.setId(idGenerator.getAndIncrement());
    users.add(user);
    return user;
  }

  public UserEntity findById(Long id) {
    return users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
  }

  public void deleteById(Long id) {
    users.removeIf(u -> u.getId().equals(id));
  }
}