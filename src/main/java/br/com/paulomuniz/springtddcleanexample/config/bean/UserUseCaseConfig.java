package br.com.paulomuniz.springtddcleanexample.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.paulomuniz.springtddcleanexample.core.usecase.impl.UserUseCaseImpl;
import br.com.paulomuniz.springtddcleanexample.infra.repository.impl.UserDataProviderImpl;

@Configuration
public class UserUseCaseConfig {
  @Bean
  UserUseCaseImpl userUseCase(UserDataProviderImpl userDataProvider) {
    return new UserUseCaseImpl(userDataProvider);
  }
}
