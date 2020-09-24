/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Associado;
import com.contamov.repository.AssociadoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
  
  private final AssociadoRepository associadoRepository;

  @Autowired
  public AssociadoService(AssociadoRepository associadoRepository) {
    this.associadoRepository = associadoRepository;
  }

  public Associado salvar(Associado associado) {
    return associadoRepository.save(associado);
  }
  
  public void deleteById(Long id) {
    associadoRepository.deleteById(id);
  }
  
  public List<Associado> findAll() {
    return associadoRepository.findAll();
  }
  
  public Optional<Associado> findAssociadoById(Long id) {
    return associadoRepository.findById(id);
  }
  
}
