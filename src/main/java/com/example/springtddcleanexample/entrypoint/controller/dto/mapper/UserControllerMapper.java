package com.example.springtddcleanexample.entrypoint.controller.dto.mapper;

import com.example.springtddcleanexample.core.domain.User;
import com.example.springtddcleanexample.entrypoint.controller.dto.response.UserResponseDTO;

public class UserControllerMapper {
  public static UserResponseDTO toResponse(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }
}
