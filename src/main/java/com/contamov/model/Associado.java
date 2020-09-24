/**
 * @author abner
 */
package com.contamov.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // JPA Only
public class Associado extends AbstractEntity implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "associado_gen")
  Long id;
  
  @Column(name = "nome", nullable = false)
  private String nome;
  @Column(name = "cnpj_cpf", nullable = false)
  private String cnpjCpf;
  //
  @Column(name = "data_ins")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataIns;
  @Column(name = "data_alt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlt;

  public Associado(String nome, String cnpjCpf) {
    this.nome = nome;
    this.cnpjCpf = cnpjCpf;
  }
  
}