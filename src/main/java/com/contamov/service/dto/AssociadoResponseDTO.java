/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Associado;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AssociadoResponseDTO {

  private final Long id;
  private final String nome;
  private final String cnpjCpf;

  public static AssociadoResponseDTO transformaEmDTO(Associado associado) {
    return new AssociadoResponseDTO(associado.getId(), associado.getNome(), associado.getCnpjCpf());
  }

}
