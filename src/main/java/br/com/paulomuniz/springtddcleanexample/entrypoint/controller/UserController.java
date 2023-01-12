package br.com.paulomuniz.springtddcleanexample.entrypoint.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.core.usecase.UserUseCase;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.mapper.UserControllerMapper;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.request.UserRequestDTO;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserUseCase userUseCase;

  @GetMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public List<UserResponseDTO> findAll() {
    return userUseCase.findAll()
        .stream()
        .map(UserControllerMapper::toResponse)
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CREATED)
  public UserResponseDTO create(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    User userToCreate = UserControllerMapper.toDomain(userRequestDTO);
    return UserControllerMapper.toResponse(userUseCase.create(userToCreate));
  }
}
