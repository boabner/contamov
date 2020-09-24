/**
 * @author abner
 */
package com.contamov.control;

import com.contamov.error.ResourceNotFoundException;
import com.contamov.model.Conta;
import com.contamov.service.ContaService;
import com.contamov.service.dto.ContaDTO;
import com.contamov.service.dto.ContaResponseDTO;
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
@RequestMapping("contamov/contas")
public class ContaController {
  
  private final ContaService contaService;
  
  @Autowired
  public ContaController(ContaService contaService) {
    this.contaService = contaService;
  }
  
  @GetMapping
  public List<Conta> listAll() {
    return contaService.findAll();
  }
  
  @PostMapping
  public ResponseEntity<?> save(@RequestBody ContaDTO dto) {
    Conta conta = contaService.salvar(dto.transformaParaObjeto());
    return new ResponseEntity<>(ContaResponseDTO.transformaEmDTO(conta), HttpStatus.CREATED);
  }
  
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    verifyIfContaExists(id);
    contaService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @PutMapping
  public ResponseEntity<?> update(@RequestBody ContaDTO dto) {
    Conta conta = contaService.salvar(dto.transformaParaObjeto());
    return new ResponseEntity<>(ContaResponseDTO.transformaEmDTO(conta), HttpStatus.CREATED);
  }
  
  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getContaById(@PathVariable("id") Long id) {
    verifyIfContaExists(id);
    Conta Conta = contaService.findContaById(id).get();
    return new ResponseEntity<>(Conta, HttpStatus.OK);
  }
  
  private void verifyIfContaExists(Long id) {
    if (!contaService.findContaById(id).isPresent()) {
      throw new ResourceNotFoundException("Conta de id " + id + " não encontrada");
    }
  }
  
}