package br.com.paulomuniz.springtddcleanexample.entrypoint.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
  private Long id;
  private String name;
  private String email;
}
