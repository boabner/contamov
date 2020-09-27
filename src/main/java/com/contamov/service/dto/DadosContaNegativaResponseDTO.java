/**
 * @author abner
 */
package com.contamov.service.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class DadosContaNegativaResponseDTO {

  private ContaDTO conta;
  private List<AssociadoDTO> associados;
  private BigDecimal saldoFinal;

  public DadosContaNegativaResponseDTO(
    ContaDTO conta, List<AssociadoDTO> associados, BigDecimal saldoFinal
  ) {
    this.conta = conta;
    this.saldoFinal = saldoFinal;
    this.associados = associados;
  }
  
}
