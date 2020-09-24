/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Conta;
import lombok.Getter;
  
@Getter
public class ContaDTO {
  
  private String numeroAgencia;
  private String numeroConta;
  
  public Conta transformaParaObjeto() {
    return new Conta(numeroAgencia, numeroConta);
  }

  
}
