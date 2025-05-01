package org.app.hwsem2mts.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.hwsem2mts.entity.WebsiteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Website Interface", description = "Управление вебсайтами")
@RestController
@RequestMapping("/api/websites")
public interface WebsiteInterfaces {
  @Operation(
          summary = "Добавление нового вебсайта",
          description = "Создание нового вебсайта в системе"
  )
  @ApiResponse(responseCode = "201", description = "Вебсайт успешно добавлен")
  @PostMapping
  ResponseEntity<WebsiteEntity> createWebsite(
          @Parameter(description = "Данные для добавления нового вебсайта", required = true)
          @RequestBody WebsiteEntity website
  );

  @Operation(
          summary = "Получение всех вебсайтов",
          description = "Возвращает список всех вебсайтов"
  )
  @ApiResponse(responseCode = "200", description = "Все вебсайты получены")
  @GetMapping
  ResponseEntity<List<WebsiteEntity>> getAllWebsites();

  @Operation(
          summary = "Получение вебсайта по ID",
          description = "Возвращает вебсайт по его ID"
  )
  @ApiResponse(responseCode = "200", description = "Вебсайт найден")
  @ApiResponse(responseCode = "404", description = "Вебсайт с таким ID не найден")
  @GetMapping("/{websiteId}")
  ResponseEntity<WebsiteEntity> getWebsiteById(
          @Parameter(description = "ID вебсайта", required = true)
          @PathVariable Long id
  );

  @Operation(
          summary = "Удаление вебсайта по ID",
          description = "Удаляет вебсайт по его ID"
  )
  @ApiResponse(responseCode = "204", description = "Вебсайт успешно удален")
  @ApiResponse(responseCode = "404", description = "Вебсайт с таким ID не найден")
  @DeleteMapping("/{websiteId}")
  ResponseEntity<Void> deleteWebsiteById(
          @Parameter(description = "ID вебсайта", required = true)
          @PathVariable Long id
  );
}