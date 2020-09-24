/**
 * @author abner
 */
package com.contamov.service.dto;

import com.contamov.model.Conta;
import com.contamov.model.MovimentacaoConta;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MovimentacaoContaResponseDTO {

  private final Long id;
  private final Conta conta;
  private final Character tipoOperacao;
  private final BigDecimal valorMovimentacao;
  private final Date dataMovimentacao;

  public static MovimentacaoContaResponseDTO transformaEmDTO(MovimentacaoConta associado) {
    return new MovimentacaoContaResponseDTO(associado.getId(), associado.getConta(), associado.getTipoOperacao(),
      associado.getValorMovimentacao(), associado.getDataMovimentacao());
  }

}
