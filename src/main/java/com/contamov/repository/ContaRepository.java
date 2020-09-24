/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long>  {

  public Conta findByNumeroAgenciaAndNumeroConta(String numerAgencia, String numeroConta);
  
}
