/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.MovimentacaoConta;
import com.contamov.repository.MovimentacaoContaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoContaService {
  
  private final MovimentacaoContaRepository associadoRepository;

  @Autowired
  public MovimentacaoContaService(MovimentacaoContaRepository associadoRepository) {
    this.associadoRepository = associadoRepository;
  }

  public MovimentacaoConta salvar(MovimentacaoConta associado) {
    return associadoRepository.save(associado);
  }
  
  public void deleteById(Long id) {
    associadoRepository.deleteById(id);
  }
  
  public List<MovimentacaoConta> findAll() {
    return associadoRepository.findAll();
  }
  
  public Optional<MovimentacaoConta> findMovimentacaoContaById(Long id) {
    return associadoRepository.findById(id);
  }
  
}
