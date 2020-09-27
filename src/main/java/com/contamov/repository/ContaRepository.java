/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.Conta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long>  {

  public Optional<Conta> findByNumeroAgenciaAndNumeroConta(String numerAgencia, String numeroConta);
  public List<Conta> findByNumeroAgencia(String numerAgencia);
  
}
