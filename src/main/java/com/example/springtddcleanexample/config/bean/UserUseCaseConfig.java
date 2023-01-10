package com.example.springtddcleanexample.config.bean;

import com.example.springtddcleanexample.core.usecase.impl.UserUseCaseImpl;
import com.example.springtddcleanexample.infra.repository.impl.UserDataProviderImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {
  @Bean
  UserUseCaseImpl userUseCase(UserDataProviderImpl userDataProvider) {
    return new UserUseCaseImpl(userDataProvider);
  }
}
