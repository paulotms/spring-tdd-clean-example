package br.com.paulomuniz.springtddcleanexample.core.dataprovider;

import java.util.List;
import java.util.Optional;

import br.com.paulomuniz.springtddcleanexample.core.domain.User;

public interface UserDataProvider {
  List<User> findAll();

  Optional<User> findByEmail(String email);

  User create(User user);
}
