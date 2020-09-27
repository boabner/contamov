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
public class ContaResponseDTO {
  
  private String numeroAgencia;
  private String numeroConta;
  private String message;
  private HttpStatus status;

}
