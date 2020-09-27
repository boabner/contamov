/**
 * @author abner
 */
package com.contamov.service.validations;

import java.math.BigDecimal;

public class MovimentacaoContaValidation {
  
  
  public static String validarDadosMovimentacao(String linhaConta, int numLinha) {
    String[] values = linhaConta.split(",");
    if (values.length == 6) {
      String numeroAgencia, numeroConta, tipoOperacao, dataMovimentacao, valorMovimentacao;
      //----------------------------------------------------------------------------------------------------------------
      numeroAgencia = values[1];
      numeroConta = values[2];
      tipoOperacao = values[3];
      dataMovimentacao = values[4];
      valorMovimentacao = values[5];
      //----------------------------------------------------------------------------------------------------------------
      if (numeroAgencia.length() > 4)
        return "Erro na linha "+ numLinha +"! O número da agência deve ser composta por até quatro dígitos.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (numeroConta.length() > 8)
        return "Erro na linha "+ numLinha +"! O número da conta deve ser composto por até oito dígitos.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (!tipoOperacao.equals("C") && !tipoOperacao.equals("D"))
        return "Erro na linha "+ numLinha +"! O tipo da operação deve ser descrito como 'C' para crédito ou 'D' para débito.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (dataMovimentacao.length() != 19)
        return "Erro na linha "+ numLinha +"! data da movimentação fora do formato 'yyyy-dd-mm hh:mm:ss'.\n";
      //----------------------------------------------------------------------------------------------------------------
      try {
        new BigDecimal(valorMovimentacao);
      } catch (NumberFormatException e) {
        return "Erro na linha "+ numLinha +"! data da movimentação fora do formato 'yyyy-dd-mm hh:mm:ss'.\n";
      }
    }
    else
      return "Erro! Dados faltantes na linha " + numLinha + 
          ", o padrão correto é 'C,AGENCIA,CONTA,TIPO DE OPERAÇÃO,DATA DA MOVIMENTAÇÃO,VALOR DA MOVIMENTAÇÃO'.\n";
    //------------------------------------------------------------------------------------------------------------------
    return "ok";
  }
  
}
