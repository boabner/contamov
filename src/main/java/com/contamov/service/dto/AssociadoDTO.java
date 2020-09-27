/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {
  
  private Long id;
  private String nome;
  private String cnpjCpf;
  private String numeroConta;
  private String numeroAgencia;

  public AssociadoDTO(String nome, String cnpjCpf, String numeroConta, String numeroAgencia) {
    this.nome = nome;
    this.cnpjCpf = cnpjCpf;
    this.numeroConta = numeroConta;
    this.numeroAgencia = numeroAgencia;
  }
  
  public Associado transformaParaObjeto() {
    return new Associado(nome, cnpjCpf);
  }
  
  public Associado transformaParaObjetoComID() {
    return new Associado(id, nome, cnpjCpf);
  }

  
}
