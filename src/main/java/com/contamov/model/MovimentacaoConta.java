/**
 * @author abner
 */
package com.contamov.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // JPA Only
public class MovimentacaoConta extends AbstractEntity implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "movimentacaoconta_gen")
  Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_conta")
  private Conta conta;
  
  @Column(name = "tipo_operacao")
  private Character tipoOperacao;
  
  @Column(name = "valor_movimentacao")
  private BigDecimal valorMovimentacao;
  
  @Column(name = "data_movimentacao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataMovimentacao;
  
  @Column(name = "data_ins")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataIns;
  
  @Column(name = "data_alt")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlt;

  public MovimentacaoConta(Conta conta, Character tipoOperacao, BigDecimal valorMovimentacao, Date dataMovimentacao) {
    this.conta = conta;
    this.tipoOperacao = tipoOperacao;
    this.valorMovimentacao = valorMovimentacao;
    this.dataMovimentacao = dataMovimentacao;
  }
  
}