/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Conta;
import com.contamov.repository.ContaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
  
  private final ContaRepository contaRepository;

  @Autowired
  public ContaService(ContaRepository contaRepository) {
    this.contaRepository = contaRepository;
  }

  public Conta salvar(Conta conta) {
    return contaRepository.save(conta);
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
  
}
