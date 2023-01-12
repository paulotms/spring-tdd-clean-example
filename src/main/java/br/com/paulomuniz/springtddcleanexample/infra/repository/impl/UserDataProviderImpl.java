package br.com.paulomuniz.springtddcleanexample.infra.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.paulomuniz.springtddcleanexample.core.dataprovider.UserDataProvider;
import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.infra.entity.UserEntity;
import br.com.paulomuniz.springtddcleanexample.infra.repository.UserRepository;
import br.com.paulomuniz.springtddcleanexample.infra.repository.mapper.UserRepositoryMapper;
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

  @Override
  public Optional<User> findByEmail(String email) {
    Optional<UserEntity> userOpt = userRepository.findByEmail(email);
    return userOpt.isPresent()
        ? Optional.ofNullable(UserRepositoryMapper.toDomain(userOpt.get()))
        : Optional.empty();
  }

  @Override
  public User create(User user) {
    UserEntity userToSave = UserRepositoryMapper.toEntity(user);
    userRepository.save(userToSave);
    return UserRepositoryMapper.toDomain(userToSave);
  }
}
