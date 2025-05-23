package org.app.hwsem2mts.controller;

import org.app.hwsem2mts.entity.UserEntity;
import org.app.hwsem2mts.exception.EntityNotFoundException;
import org.app.hwsem2mts.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.app.hwsem2mts.security.SecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @Test
  void createUsetTestMock() throws Exception {
    UserEntity testUser = new UserEntity(1L, "john.doe@example.com", "John Doe" );
    when(userService.createUser(Mockito.anyString(), Mockito.any(UserEntity.class)))
            .thenReturn(testUser);
    mockMvc.perform(post("/api/users")
                    .header("Authorization", "Bearer faketoken123")
                    .header("Idempotency-Key", "key-333")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/users/1"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    verify(userService, times(1)).createUser(Mockito.eq("key-333"),Mockito.any(UserEntity.class));
  }

  @Test
  void deleteUserTestMock() throws Exception {
    UserEntity testUser = new UserEntity(1L, "john.doe@example.com", "John Doe" );
    long nonExistentUserId = 99L;
    doThrow(new EntityNotFoundException()).when(userService).delete(nonExistentUserId);
    mockMvc.perform(delete("/api/users/{userId}", nonExistentUserId))
            .andExpect(status().isNotFound());
    verify(userService, times(1)).delete(nonExistentUserId);
  }

  @Test
  @WithMockUser(username = "admin", roles = {"USER"})
  void getAllUserTestMock() throws Exception {
    UserEntity user1 = new UserEntity(1L, "john.doe@example.com", "John Doe");
    UserEntity user2 = new UserEntity(2L, "jane.doe@example.com", "Jane Doe");
    List<UserEntity> users = Arrays.asList(user1, user2);
    when(userService.getAllUsers()).thenReturn(users);
    mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk()) // Проверка статуса 200 OK
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("John Doe"))
            .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Jane Doe"))
            .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"));
    verify(userService).getAllUsers();
  }
}