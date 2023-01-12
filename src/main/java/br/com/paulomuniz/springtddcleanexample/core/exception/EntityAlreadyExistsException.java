package br.com.paulomuniz.springtddcleanexample.core.exception;

public class EntityAlreadyExistsException extends RuntimeException {

  public EntityAlreadyExistsException() {
    super("Entity already exists");
  }
}
