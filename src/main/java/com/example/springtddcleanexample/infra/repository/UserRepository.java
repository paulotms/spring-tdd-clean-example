package com.example.springtddcleanexample.infra.repository;

import com.example.springtddcleanexample.infra.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
