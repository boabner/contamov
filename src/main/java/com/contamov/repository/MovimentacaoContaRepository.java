/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.MovimentacaoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoContaRepository extends JpaRepository<MovimentacaoConta, Long>  {
  
}
