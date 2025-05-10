package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.interfaces.UserInterfaces;
import org.app.hwsem2mts.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserInterfaces {
  private final UserService userService;

  @Override
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    List<UserEntity> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @Override
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity userEntity = userService.createUser(user);
    URI location = URI.create("/api/users/" + userEntity.getId());
    return ResponseEntity.created(location).body(userEntity);
  }

  @Override
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    try {
      userService.delete(userId);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException exception) {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<UserEntity> updateUser(@PathVariable Long userId, @RequestBody UserEntity user) {
    UserEntity updated = userService.updateUser(userId, user);
    return ResponseEntity.ok(updated);
  }

  @Override
  public ResponseEntity<UserEntity> patchUser(@PathVariable Long userId, @RequestBody UserEntity user) {
    UserEntity patched = userService.patchUser(userId, user);
    return ResponseEntity.ok(patched);
  }
}