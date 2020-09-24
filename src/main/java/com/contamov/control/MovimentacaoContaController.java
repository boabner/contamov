/**
 * @author abner
 */
package com.contamov.control;

import com.contamov.error.ResourceNotFoundException;
import com.contamov.model.MovimentacaoConta;
import com.contamov.service.MovimentacaoContaService;
import com.contamov.service.dto.MovimentacaoContaDTO;
import com.contamov.service.dto.MovimentacaoContaResponseDTO;
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
@RequestMapping("contamov/movimentacaoContas")
public class MovimentacaoContaController {
  
  private final MovimentacaoContaService movimentacaoContaService;
  
  @Autowired
  public MovimentacaoContaController(MovimentacaoContaService movimentacaoContaService) {
    this.movimentacaoContaService = movimentacaoContaService;
  }
  
  @GetMapping
  public List<MovimentacaoConta> listAll() {
    return movimentacaoContaService.findAll();
  }
  
  @PostMapping
  public ResponseEntity<?> save(@RequestBody MovimentacaoContaDTO dto) {
    MovimentacaoConta movimentacaoConta = movimentacaoContaService.salvar(dto.transformaParaObjeto());
    return new ResponseEntity<>(MovimentacaoContaResponseDTO.transformaEmDTO(movimentacaoConta), HttpStatus.CREATED);
  }
  
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfMovimentacaoContaExists(id);
    movimentacaoContaService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @PutMapping
  public ResponseEntity<?> update(@RequestBody MovimentacaoContaDTO dto) {
    MovimentacaoConta movimentacaoConta = movimentacaoContaService.salvar(dto.transformaParaObjeto());
    return new ResponseEntity<>(MovimentacaoContaResponseDTO.transformaEmDTO(movimentacaoConta), HttpStatus.CREATED);
  }
  
  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getMovimentacaoContaById(@PathVariable("id") Long id) {
    verifyIfMovimentacaoContaExists(id);
    MovimentacaoConta MovimentacaoConta = movimentacaoContaService.findMovimentacaoContaById(id).get();
    return new ResponseEntity<>(MovimentacaoConta, HttpStatus.OK);
  }
  
  private void verifyIfMovimentacaoContaExists(Long id) {
    if (!movimentacaoContaService.findMovimentacaoContaById(id).isPresent()) {
      throw new ResourceNotFoundException("MovimentacaoConta de id " + id + " não encontrada");
    }
  }
  
}