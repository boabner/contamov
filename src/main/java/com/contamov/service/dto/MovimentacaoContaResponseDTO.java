/**
 * @author abner
 */
package com.contamov.service.dto;

import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
 
@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoContaResponseDTO {

  private String nomeArquivo;
  private String messageErro;
  private String messageSuccess;
  private HttpStatus status;
  //
  public MovimentacaoContaResponseDTO(String nomeArquivo, String messageErro, String messageSuccess, HttpStatus status) {
    this.messageErro = messageErro;
    this.status = status;
    this.messageSuccess = messageSuccess;
    this.nomeArquivo = nomeArquivo;
  }
  
}