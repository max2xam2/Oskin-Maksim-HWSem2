package org.app.hwsem2mts.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserEntity> getAllUsers() {
    log.info("get all users");
    return userRepository.findAll();
  }

  public UserEntity createUser(UserEntity user) {
    log.info("create new user");
    return userRepository.saveUser(user);
  }

  public void delete(Long userId) {
    log.info("delete user");
    userRepository.deleteById(userId);
  }

  public UserEntity updateUser(Long id, UserEntity user) {
    UserEntity existing = userRepository.findById(id);
    if (existing == null) {
      throw new EntityNotFoundException();
    }
    existing.setEmail(user.getEmail());
    existing.setName(user.getName());
    log.info("update user");
    return userRepository.updateUser(existing);
  }

  public UserEntity patchUser(Long id, UserEntity user) {
    UserEntity changing = userRepository.findById(id);
    if (changing == null) {
      throw new EntityNotFoundException();
    }
    if (user.getEmail() != null) changing.setEmail(user.getEmail());
    if (user.getName() != null) changing.setName(user.getName());
    log.info("patch user");
    return userRepository.updateUser(changing);
  }
}
