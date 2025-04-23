package org.app.hwsem2mts.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<UserEntity> getAllUsers() {
    log.info("GET-ALL-USERS complete");
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
