/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Associado;
import lombok.Getter;
  
@Getter
public class AssociadoDTO {
  
  private String nome;
  private String cnpjCpf;
  
  public Associado transformaParaObjeto() {
    return new Associado(nome, cnpjCpf);
  }

  
}
