/**
 * @author abner
 */
package com.contamov.service.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoContaDTO {

  private Character tipoOperacao;
  private BigDecimal valorMovimentacao;
  private Date dataMovimentacao;
  
  public MovimentacaoContaDTO(Character tipoOperacao, BigDecimal valorMovimentacao, Date dataMovimentacao) {
    this.tipoOperacao = tipoOperacao;
    this.dataMovimentacao = dataMovimentacao;
    this.valorMovimentacao = valorMovimentacao;
  }
  
}