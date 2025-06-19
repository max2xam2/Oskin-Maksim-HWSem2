package org.app.hwsem2mts.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.repository.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final Set<String> checkKeysServise = ConcurrentHashMap.newKeySet();

  public List<UserEntity> getAllUsers() {
    log.info("get all users");
    return userRepository.findAll();
  }

  /*Метод, который гарантирует создание пользователя 1 раз.
  * По ключу проверяем был ли уже обработан запрос,
  * если ключ не найден, то сохраняем пользователя,
  * после сохранения добавляем ключ в список обработанных,
  * после этого на запрос с таким же ключом,
  * операции выполняться не будут. А если ключ найден,
  * то пробрасываем ошибку */
  public UserEntity createUser(String keyID, UserEntity user) {
    if (!checkKeysServise.contains(keyID)) {
      log.info("create new user");
      UserEntity newUserSave = userRepository.saveUser(user);
      checkKeysServise.add(keyID);
      return newUserSave;
    } else {
      throw new IllegalStateException("Создание этого пользователя уже обработано");
    }
  }

  public void delete(Long userId) {
    log.info("delete user");
    userRepository.deleteById(userId);
  }

  @Async
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
