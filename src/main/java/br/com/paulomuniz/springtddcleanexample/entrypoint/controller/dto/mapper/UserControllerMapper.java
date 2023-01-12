package br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.mapper;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.request.UserRequestDTO;
import br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.response.UserResponseDTO;

public class UserControllerMapper {
  public static UserResponseDTO toResponse(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

  public static User toDomain(UserRequestDTO dto) {
    return User.builder()
        .name(dto.getName())
        .email(dto.getEmail())
        .password(dto.getPassword())
        .build();
  }
}
