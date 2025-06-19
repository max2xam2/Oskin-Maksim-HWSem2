package org.app.hwsem2mts.repository;

import org.app.hwsem2mts.entity.UserEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
  private final List<UserEntity> users = new ArrayList<>();

  public List<UserEntity> findAll() {
    return users;
  }

  public UserEntity saveUser(UserEntity user) {
    users.add(user);
    return user;
  }

  public UserEntity findById(Long id) {
    return users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
  }

  public UserEntity updateUser(UserEntity updatedUser) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(updatedUser.getId())) {
        users.set(i, updatedUser);
        return updatedUser;
      }
    }
    return null;
  }

  public void deleteById(Long id) {
    users.removeIf(u -> u.getId().equals(id));
  }
}
