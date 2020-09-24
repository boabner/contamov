/**
 * @author abner
 */
package com.contamov.control;

import com.contamov.service.MovimentacaoContaService;
import org.springframework.beans.factory.annotation.Autowired;
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

}