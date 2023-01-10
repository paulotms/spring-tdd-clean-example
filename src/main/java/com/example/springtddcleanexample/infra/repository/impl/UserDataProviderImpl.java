package com.example.springtddcleanexample.infra.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springtddcleanexample.core.dataprovider.UserDataProvider;
import com.example.springtddcleanexample.core.domain.User;
import com.example.springtddcleanexample.infra.repository.UserRepository;
import com.example.springtddcleanexample.infra.repository.mapper.UserRepositoryMapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {
  private final UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll()
        .stream()
        .map(UserRepositoryMapper::toDomain)
        .collect(Collectors.toList());
  }
}
