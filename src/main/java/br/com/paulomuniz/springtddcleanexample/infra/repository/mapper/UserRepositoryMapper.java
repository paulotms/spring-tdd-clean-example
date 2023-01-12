package br.com.paulomuniz.springtddcleanexample.infra.repository.mapper;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.infra.entity.UserEntity;

public class UserRepositoryMapper {
  public static User toDomain(UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }

  public static UserEntity toEntity(User user) {
    return UserEntity.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
