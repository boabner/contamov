/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.Conta;
import com.contamov.model.MovimentacaoConta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoContaRepository extends JpaRepository<MovimentacaoConta, Long>  {
  public List<MovimentacaoConta> findAllByContaOrderByDataMovimentacaoDesc(Conta conta);
}
