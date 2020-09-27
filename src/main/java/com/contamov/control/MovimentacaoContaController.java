/**
 * @author abner
 */
package com.contamov.control;

import com.contamov.service.MovimentacaoContaService;
import com.contamov.service.dto.DadosContaNegativaResponseDTO;
import com.contamov.service.dto.DadosContaResponseDTO;
import com.contamov.service.dto.MovimentacaoContaResponseDTO;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contamov/movimentacaocontas")
public class MovimentacaoContaController {

  private final MovimentacaoContaService movimentacaoContaService;

  @Autowired
  public MovimentacaoContaController(MovimentacaoContaService movimentacaoContaService) {
    this.movimentacaoContaService = movimentacaoContaService;
  }
  
  @PostMapping
  public ResponseEntity<?> save(@RequestBody Map<String, String> parameters) {
    MovimentacaoContaResponseDTO dto = 
        movimentacaoContaService.processarArquivo(parameters.get("nomeArquivo"), parameters.get("arquivo"));
    return new ResponseEntity<>(dto, dto.getStatus());
  }
  
  @RequestMapping(value = "/getdados", method = RequestMethod.GET)
  public ResponseEntity<?> getDadosContaByNumeroAgenciaAndNumeroConta(
      @RequestParam(name="numeroagencia") String numeroAgencia, 
      @RequestParam(name="name", required = false) String numeroConta
  ) {
    List<DadosContaResponseDTO> listDto = movimentacaoContaService.getDadosContaAndMovimentacaoByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
    return new ResponseEntity<>(listDto, HttpStatus.OK);
  }
  
  @GetMapping(path = "/getcontasnegativas")
  public ResponseEntity<?> getContasNegativas() {
    List<DadosContaNegativaResponseDTO> listDto = movimentacaoContaService.getAllContaNegativa();
    return new ResponseEntity<>(listDto, HttpStatus.OK);
  }
  
}