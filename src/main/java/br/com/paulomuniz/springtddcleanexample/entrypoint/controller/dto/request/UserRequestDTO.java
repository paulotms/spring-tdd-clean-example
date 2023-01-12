package br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
  @NotBlank
  @Length(min = 3, max = 45)
  private String name;

  @Email
  @NotBlank
  @Length(min = 5, max = 100)
  private String email;

  @NotBlank
  @Length(min = 8, max = 12)
  private String password;
}
