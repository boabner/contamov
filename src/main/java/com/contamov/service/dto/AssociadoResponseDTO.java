/**
 * @author abner
 */
package com.contamov.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class AssociadoResponseDTO {

  private String nome;
  private String cnpjCpf;
  private String message;
  private HttpStatus status;

}
