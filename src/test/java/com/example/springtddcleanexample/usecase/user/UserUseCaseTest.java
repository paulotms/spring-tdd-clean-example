package com.example.springtddcleanexample.usecase.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springtddcleanexample.core.dataprovider.UserDataProvider;
import com.example.springtddcleanexample.core.domain.User;
import com.example.springtddcleanexample.core.usecase.impl.UserUseCaseImpl;
import com.example.springtddcleanexample.usecase.user.builder.UserBuilder;

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
}
