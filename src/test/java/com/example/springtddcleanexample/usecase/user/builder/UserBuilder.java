package com.example.springtddcleanexample.usecase.user.builder;

import com.example.springtddcleanexample.core.domain.User;

public class UserBuilder {
  public static User createUser() {
    return User.builder()
        .id(1L)
        .name("John Doe")
        .email("john.doe@email.com")
        .password("password")
        .build();
  }
}
