/**
 * @author abner
 */
package com.contamov.service.validations;

import com.contamov.repository.AssociadoRepository;

public class AssociadoValidation {
  
  public static String validarAssociadoExistente(String nome, String cnpjCpf, AssociadoRepository contaRepository) {
    if (contaRepository.findByNomeAndCnpjCpf(nome, cnpjCpf).isPresent())
      return "Erro! Já existe um registro de associado com esse mesmo nome e CNPJ/CPF.";
    return "ok";
  }
  
}