package org.app.hwsem2mts.controller;

import lombok.RequiredArgsConstructor;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public UserEntity createUser(@RequestBody UserEntity user) {
    return userService.createUser(user);
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    userService.delete(userId);
  }
}