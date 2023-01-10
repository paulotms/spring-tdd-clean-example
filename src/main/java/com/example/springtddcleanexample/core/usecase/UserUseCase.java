package com.example.springtddcleanexample.core.usecase;

import java.util.List;

import com.example.springtddcleanexample.core.domain.User;

public interface UserUseCase {
  List<User> findAll();
}
