package br.com.paulomuniz.springtddcleanexample.usecase.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.paulomuniz.springtddcleanexample.usecase.user.builder.UserBuilder;

import br.com.paulomuniz.springtddcleanexample.core.dataprovider.UserDataProvider;
import br.com.paulomuniz.springtddcleanexample.core.domain.User;
import br.com.paulomuniz.springtddcleanexample.core.exception.EntityAlreadyExistsException;
import br.com.paulomuniz.springtddcleanexample.core.usecase.impl.UserUseCaseImpl;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {
  @Mock
  private UserDataProvider userDataProvider;

  @InjectMocks
  private UserUseCaseImpl useCaseImpl;

  @Test
  void findAll_shouldReturn_whenCalled() {
    List<User> usersMock = Collections.singletonList(UserBuilder.createUser());

    Mockito.when(userDataProvider.findAll()).thenReturn(usersMock);
    List<User> expectedUsers = useCaseImpl.findAll();

    Assertions.assertEquals(expectedUsers, usersMock);
    Assertions.assertInstanceOf(User.class, expectedUsers.get(0));
    Assertions.assertEquals(expectedUsers.size(), 1);
    Mockito.verify(userDataProvider, Mockito.times(1)).findAll();
  }

  @Test
  void findAll_shouldReturnAEmptyList_whenCalled() {
    List<User> usersMock = new ArrayList<>();

    Mockito.when(userDataProvider.findAll()).thenReturn(usersMock);
    List<User> expectedUsers = useCaseImpl.findAll();

    Assertions.assertEquals(expectedUsers, usersMock);
    Assertions.assertEquals(expectedUsers.size(), 0);
    Mockito.verify(userDataProvider, Mockito.times(1)).findAll();
  }

  @Test
  void createUser_shouldThrowException_whenCalledWithADuplicateEmail() {
    User userMock = UserBuilder.createUser();

    Mockito.when(userDataProvider.findByEmail(userMock.getEmail())).thenReturn(Optional.ofNullable(userMock));

    Assertions.assertThrows(EntityAlreadyExistsException.class, () -> {
      useCaseImpl.create(userMock);
    });
    Mockito.verify(userDataProvider, Mockito.times(1)).findByEmail(userMock.getEmail());
  }

  @Test
  void createUser_shouldReturnANewUser_whenCalled() {
    User userMock = UserBuilder.createUser();

    Mockito.when(userDataProvider.findByEmail(userMock.getEmail())).thenReturn(Optional.empty());
    Mockito.when(userDataProvider.create(userMock)).thenReturn(userMock);
    User expectedUser = useCaseImpl.create(userMock);

    Assertions.assertEquals(userMock, expectedUser);
    Assertions.assertInstanceOf(User.class, expectedUser);
    Assertions.assertEquals(userMock.getId(), 1);
    Mockito.verify(userDataProvider, Mockito.times(1)).findByEmail(userMock.getEmail());
    Mockito.verify(userDataProvider, Mockito.times(1)).create(userMock);
  }
}
