package br.com.paulomuniz.springtddcleanexample.entrypoint.controller.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.paulomuniz.springtddcleanexample.core.exception.EntityAlreadyExistsException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityAlreadyExistsException exception) {
        return buildResponseEntity(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    return buildResponseEntity(HttpStatus.BAD_REQUEST,
        "Malformed JSON body and/or field error",
        Collections.singletonList(ex.getLocalizedMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(fieldError -> errors
            .add("Field " + fieldError.getField().toUpperCase() + " " + fieldError.getDefaultMessage()));
    ex.getBindingResult().getGlobalErrors()
        .forEach(globalErrors -> errors
            .add("Object " + globalErrors.getObjectName() + " " + globalErrors.getDefaultMessage()));

    return buildResponseEntity(HttpStatus.BAD_REQUEST, "Informed argument(s) validation error(s)", errors);
  }

  private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message, List<String> errors) {
    ApiError apiError = ApiError.builder()
        .code(httpStatus.value())
        .status(httpStatus.getReasonPhrase())
        .message(message)
        .errors(errors)
        .timestamp(LocalDateTime.now())
        .build();
    return ResponseEntity.status(httpStatus).body(apiError);
  }

}
