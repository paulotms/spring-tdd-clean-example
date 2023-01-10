package com.example.springtddcleanexample.core.usecase.impl;

import java.util.List;

import com.example.springtddcleanexample.core.dataprovider.UserDataProvider;
import com.example.springtddcleanexample.core.domain.User;
import com.example.springtddcleanexample.core.usecase.UserUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {
  private final UserDataProvider userDataProvider;

  @Override
  public List<User> findAll() {
    return userDataProvider.findAll();
  }
}
