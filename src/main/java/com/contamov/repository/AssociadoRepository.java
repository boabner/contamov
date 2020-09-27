/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.Associado;
import com.contamov.model.Conta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long>  {
  public Optional<Associado> findByCnpjCpf(String cnpjCpf);
  public Optional<Associado> findByNomeAndCnpjCpf(String nome, String cnpjCpf);
  public List<Associado> findAllByConta(Conta conta);
}