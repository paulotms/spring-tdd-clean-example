package br.com.paulomuniz.springtddcleanexample.integrated.controller;

import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.core.exception.EntityAlreadyExistsException;
import br.com.paulomuniz.springtddcleanexample.core.usecase.UserUseCase;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.UserController;
import br.com.paulomuniz.springtddcleanexample.usecase.user.builder.UserBuilder;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
  private static final String BASE_URL = "/api/v1/users";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserUseCase userUseCase;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void findAll_shouldReturn_whenCalled() throws Exception {
    List<User> usersMock = Collections.singletonList(UserBuilder.createUser());

    Mockito.when(userUseCase.findAll()).thenReturn(usersMock);

    mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(usersMock.get(0).getId().intValue())));
  }

  @Test
  void create_shouldCreateANewUser_whenCalled() throws Exception {
    User createUser = UserBuilder.createUser();

    Mockito.when(userUseCase.create(Mockito.any())).thenReturn(createUser);

    mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createUser)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(createUser.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(createUser.getEmail())));
  }

  @Test
  void create_shouldThrow_whenCalledWithADuplicateEmail() throws Exception {
    User createUser = UserBuilder.createUser();
    createUser.setEmail("john.doe@email.com");

    Mockito.when(userUseCase.create(Mockito.any())).thenThrow(EntityAlreadyExistsException.class);

    mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createUser)))
        .andExpect(MockMvcResultMatchers.status().isConflict());
  }
}
