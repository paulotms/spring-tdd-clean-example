package br.com.paulomuniz.springtddcleanexample.core.usecase;

import java.util.List;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;

public interface UserUseCase {
  List<User> findAll();

  User create(User user);
}
