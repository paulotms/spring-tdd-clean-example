package br.com.paulomuniz.springtddcleanexample.core.usecase.impl;

import java.util.List;

import br.com.paulomuniz.springtddcleanexample.core.dataprovider.UserDataProvider;
import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.core.exception.EntityAlreadyExistsException;
import br.com.paulomuniz.springtddcleanexample.core.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {
  private final UserDataProvider userDataProvider;

  @Override
  public List<User> findAll() {
    return userDataProvider.findAll();
  }

  @Override
  public User create(User user) {
    verifyUserWithDuplicateEmail(user.getEmail());
    return userDataProvider.create(user);
  }

  private void verifyUserWithDuplicateEmail(String email) {
    userDataProvider.findByEmail(email)
      .ifPresent((u) -> {
        throw new EntityAlreadyExistsException();
      });
  }

}
