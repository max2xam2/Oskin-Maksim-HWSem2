package org.app.hwsem2mts.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.hwsem2mts.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Tag(name = "User Interface", description = "Управление действиями пользователь")
@RequestMapping("/api/users")
public interface UserInterfaces {
  @Operation(
          summary = "Создание пользователя",
          description = "Создание нового пользователя в системе для последующей работы"
  )
  @ApiResponse(responseCode = "201", description = "Пользователь создан")
  @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя")
  @PostMapping
  ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user);

  @Operation(
          summary = "Получение всех пользователей",
          description = "Вывод списка всех зарегистрированных пользователей"
  )
  @ApiResponse(responseCode = "200", description = "Все пользователи получены")
  @ApiResponse(responseCode = "404", description = "Список пользователей пуст")
  @GetMapping
  ResponseEntity<List<UserEntity>> getAllUsers();

  @Operation(
          summary = "Удаление пользователя по id",
          description = "Удаление пользователя из системы по его id"
  )
  @ApiResponse(responseCode = "204", description = "Пользователь удален")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  @DeleteMapping("/{userId}")
  ResponseEntity<Void> deleteUser(
          @Parameter(description = "id пользователя", required = true)
          @PathVariable Long userId);

  @Operation(
          summary = "Обновление пользователя по ID",
          description = "Полное обновление данных пользователя по указанному ID"
  )
  @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён")
  @ApiResponse(responseCode = "400", description = "Некорректные данные для обновления пользователя")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  @PutMapping("/{userId}")
  ResponseEntity<UserEntity> updateUser(
          @PathVariable Long userId,
          @RequestBody UserEntity user
  );

  @Operation(
          summary = "Частичное обновление пользователя по ID",
          description = "Частичное обновление переданных данных пользователя"
  )
  @ApiResponse(responseCode = "200", description = "Пользователь успешно частично обновлён")
  @ApiResponse(responseCode = "400", description = "Некорректные данные для частичного обновления пользователя")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  @PatchMapping("/{userId}")
  ResponseEntity<UserEntity> patchUser(
          @PathVariable Long userId,
          @RequestBody UserEntity user
  );
}