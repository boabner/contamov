/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Conta;
import com.contamov.repository.ContaRepository;
import com.contamov.service.dto.ContaResponseDTO;
import com.contamov.service.validations.ContaValidation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
  
  private final ContaRepository contaRepository;

  @Autowired
  public ContaService(ContaRepository contaRepository) {
    this.contaRepository = contaRepository;
  }

  public ContaResponseDTO atualizar(Conta conta) {
    ContaResponseDTO dto = new ContaResponseDTO();
    Optional<Conta> opConta = getByNumeroAgenciaAndNumeroConta(conta.getNumeroAgencia(), conta.getNumeroConta());
    if (opConta.isPresent()) {
        dto.setStatus(HttpStatus.CONFLICT);
        dto.setMessage("Já existe uma conta esse mesmo número de conta e agência.");
    }
    else {
      dto.setStatus(HttpStatus.CONFLICT);
      dto.setMessage("Conta não encontrada.");
    }
    return dto;
  }
  
  public ContaResponseDTO salvar(Conta conta) {
    String msg = ContaValidation.validarContaExistente(conta.getNumeroAgencia(), conta.getNumeroConta(), contaRepository);
    ContaResponseDTO dto = new ContaResponseDTO();
    if (!msg.contains("Erro")) {
      conta = contaRepository.save(conta);
      BeanUtils.copyProperties(conta, dto);
      dto.setMessage("Conta criada com sucesso.");
      dto.setStatus(HttpStatus.CREATED);
    }
    else {
      dto.setStatus(HttpStatus.CONFLICT);
      dto.setMessage(msg);
    }
    return dto;
  }
  
  public void deleteById(Long id) {
    contaRepository.deleteById(id);
  }
  
  public List<Conta> findAll() {
    return contaRepository.findAll();
  }
  
  public Optional<Conta> findContaById(Long id) {
    return contaRepository.findById(id);
  }
  
  public List<Conta> getByNumeroAgencia(String numeroAgencia) {
    return contaRepository.findByNumeroAgencia(numeroAgencia);
  }
  
  public Optional<Conta> getByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta) {
    return contaRepository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
  }
  
  public Conta getContaByNumeroAgenciaAndNumeroContaOrCreate(String numeroAgencia, String numeroConta) {
    Optional<Conta> opConta = contaRepository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
    Conta conta;
    if (!opConta.isPresent()) {
      conta = new Conta(numeroAgencia, numeroConta);
      contaRepository.save(conta);
    }
    else
      conta = opConta.get();
    return conta;
  }
  
}
