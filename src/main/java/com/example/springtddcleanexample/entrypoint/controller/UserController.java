package com.example.springtddcleanexample.entrypoint.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springtddcleanexample.core.usecase.UserUseCase;
import com.example.springtddcleanexample.entrypoint.controller.dto.mapper.UserControllerMapper;
import com.example.springtddcleanexample.entrypoint.controller.dto.response.UserResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
