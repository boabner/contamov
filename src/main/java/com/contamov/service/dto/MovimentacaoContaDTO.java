/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Conta;
import com.contamov.model.MovimentacaoConta;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
  
@Getter
public class MovimentacaoContaDTO {
  
  private Conta conta;
  private Character tipoOperacao;
  private BigDecimal valorMovimentacao;
  private Date dataMovimentacao;
  
  public MovimentacaoConta transformaParaObjeto() {
    return new MovimentacaoConta(conta, tipoOperacao, valorMovimentacao, dataMovimentacao);
  }

  
}
