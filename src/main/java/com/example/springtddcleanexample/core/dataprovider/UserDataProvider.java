package com.example.springtddcleanexample.core.dataprovider;

import java.util.List;

import com.example.springtddcleanexample.core.domain.User;

public interface UserDataProvider {
  List<User> findAll();
}
