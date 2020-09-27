/**
 * @author abner
 */
package com.contamov.control;

import com.contamov.error.ResourceNotFoundException;
import com.contamov.model.Associado;
import com.contamov.service.AssociadoService;
import com.contamov.service.dto.AssociadoDTO;
import com.contamov.service.dto.AssociadoResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contamov/associados")
public class AssociadoController {
  
  private final AssociadoService associadoService;
  
  @Autowired
  public AssociadoController(AssociadoService associadoService) {
    this.associadoService = associadoService;
  }
  
  @GetMapping
  public List<Associado> listAll() {
    return associadoService.findAll();
  }
  
  @PostMapping
  public ResponseEntity<?> save(@RequestBody AssociadoDTO dto) {
    AssociadoResponseDTO associado = associadoService.salvar(dto);
    return new ResponseEntity<>(associado, associado.getStatus());
  }
  
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfAssociadoExists(id);
    associadoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @PutMapping
  public ResponseEntity<?> update(@RequestBody AssociadoDTO dto) {
    AssociadoResponseDTO associado = associadoService.atualizar(dto.transformaParaObjetoComID());
    return new ResponseEntity<>(associado, associado.getStatus());
  }
  
  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getAssociadoById(@PathVariable("id") Long id) {
    verifyIfAssociadoExists(id);
    Associado Associado = associadoService.findAssociadoById(id).get();
    return new ResponseEntity<>(Associado, HttpStatus.OK);
  }
  
  private void verifyIfAssociadoExists(Long id) {
    if (!associadoService.findAssociadoById(id).isPresent()) {
      throw new ResourceNotFoundException("Associado de id " + id + " não encontrada");
    }
  }
  
}