package org.app.hwsem2mts.service;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }

  public UserEntity createUser(UserEntity user) {
    return userRepository.save(user);
  }

   public void delete(Long userId) {
    userRepository.deleteById(userId);
  }
}
