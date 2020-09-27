/**
 * @author abner
 */
package com.contamov.service.validations;

import com.contamov.repository.ContaRepository;

public class ContaValidation {
  
  public static String validarDadosConta(String linhaConta, int numLinha) {
    String[] values = linhaConta.split(",");
    if (values.length == 5) {
      String numeroAgencia, numeroConta, nome, cnpjCpf;
      //----------------------------------------------------------------------------------------------------------------
      numeroAgencia = values[1];
      numeroConta = values[2];
      nome = values[3];
      cnpjCpf = values[4];
      //----------------------------------------------------------------------------------------------------------------
      if (numeroAgencia.length() > 4)
        return "Erro na linha "+ numLinha +"! O número da agência deve ser composta por até quatro dígitos.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (numeroConta.length() > 8)
        return "Erro na linha "+ numLinha +"! O número da conta deve ser composto por até oito dígitos.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (cnpjCpf.length() < 11 || cnpjCpf.length() > 14)
        return "Erro na linha "+ numLinha +"! O número do documento CPF/CNPJ deve ser composto por onze ou catorze dígitos.\n";
      //----------------------------------------------------------------------------------------------------------------
      if (nome.length() <= 1)
        return "Erro na linha "+ numLinha +"! O nome do associado deve conter no mínimo duas letras.\n";
      //----------------------------------------------------------------------------------------------------------------
    }
    else
      return "Erro! Dados faltantes na linha " + numLinha + 
          ", o padrão correto é 'C,AGENCIA,CONTA,CNPJ/CPF ASSOCIADO,NOME ASSOCIADO'.\n";
    //------------------------------------------------------------------------------------------------------------------
    return "ok";
  }
  
  public static String validarContaExistente(String numeroAgencia, String numeroConta, ContaRepository contaRepository) {
    if (contaRepository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta).isPresent())
      return "Erro! Já existe um registro com esse mesmo número de conta e agência.";
    return "ok";
  }
  
}
