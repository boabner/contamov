/**
 * @author abner
 */
package com.contamov.repository;

import com.contamov.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long>  {
  public Associado findByNomeAndCnpjCpf(String nome, String cnpjCpf);
}
