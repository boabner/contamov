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
public class Conta extends AbstractEntity implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "conta_gen")
  Long id;

  //@NotEmpty
  @Column(name = "numero_agencia", nullable = false)
  private String numeroAgencia;
  //
  @Column(name = "numero_conta", nullable = false)
  private String numeroConta;
  //
  @Column(name = "data_ins")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataIns;
  @Column(name = "data_alt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlt;
  
  public Conta(String numeroAgencia, String numeroConta) {
    this.numeroAgencia = numeroAgencia;
    this.numeroConta = numeroConta;
  }

}