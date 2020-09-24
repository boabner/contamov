/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Conta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ContaResponseDTO {

  private final Long id;
  private final String numeroAgencia;
  private final String numeroConta;

  public static ContaResponseDTO transformaEmDTO(Conta conta) {
    return new ContaResponseDTO(conta.getId(), conta.getNumeroAgencia(), conta.getNumeroConta());
  }

}
