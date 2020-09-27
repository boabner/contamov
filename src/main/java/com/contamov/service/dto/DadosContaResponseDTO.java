/**
 * @author abner
 */
package com.contamov.service.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class DadosContaResponseDTO {

  private ContaDTO conta;
  private List<MovimentacaoContaDTO> movimentacoes;
  private List<AssociadoDTO> associados;
  private BigDecimal totalDebito;
  private BigDecimal totalCredito;
  private BigDecimal saldoFinal;

  public DadosContaResponseDTO(
    ContaDTO conta, List<MovimentacaoContaDTO> movimentacoes, List<AssociadoDTO> associados, BigDecimal totalDebito,
    BigDecimal totalCredito, BigDecimal saldoFinal
  ) {
    this.conta = conta;
    this.movimentacoes = movimentacoes;
    this.associados = associados;
    this.totalDebito = totalDebito;
    this.totalCredito = totalCredito;
    this.saldoFinal = saldoFinal;
  }
  
}
