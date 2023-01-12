package br.com.paulomuniz.springtddcleanexample.integrated.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import br.com.paulomuniz.springtddcleanexample.infra.entity.UserEntity;
import br.com.paulomuniz.springtddcleanexample.infra.repository.UserRepository;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  void findAll_shouldReturn_whenCalled() {
    List<UserEntity> findAll = userRepository.findAll();

    Assertions.assertEquals(1, findAll.size());
  }

  @Test
  void findByEmail_shouldReturn_whenCalled() {
    String email = "john.doe@email.com";

    Optional<UserEntity> expectedUser = userRepository.findByEmail(email);

    Assertions.assertTrue(expectedUser.isPresent());
  }

  @Test
  void findByEmail_shouldNotReturn_whenCalled() {
    String email = "john.fail@email.com";

    Optional<UserEntity> expectedUser = userRepository.findByEmail(email);

    Assertions.assertTrue(expectedUser.isEmpty());
  }

  @Test
  void save_shouldReturn_whenCalled() {
    UserEntity newUser = UserEntity.builder()
        .name("Paulo")
        .email("paulotadeums@gmail.com")
        .password("password")
        .build();

    UserEntity userCreated = userRepository.save(newUser);

    Assertions.assertEquals(2L, userCreated.getId());
  }

  @Test
  void save_shouldThrow_whenCalledWithAEmptyEmail() {
    UserEntity newUser = UserEntity.builder()
        .name("Paulo")
        .password("password")
        .build();

    Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
      userRepository.save(newUser);
    });
  }
}
