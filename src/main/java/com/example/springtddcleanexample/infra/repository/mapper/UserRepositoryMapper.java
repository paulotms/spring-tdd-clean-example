package com.example.springtddcleanexample.infra.repository.mapper;

import com.example.springtddcleanexample.core.domain.User;
import com.example.springtddcleanexample.infra.entity.UserEntity;

public class UserRepositoryMapper {
  public static User toDomain(UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }
}
