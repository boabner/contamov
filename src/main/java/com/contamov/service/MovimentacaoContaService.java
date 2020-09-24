/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Associado;
import com.contamov.model.Conta;
import com.contamov.model.MovimentacaoConta;
import com.contamov.repository.AssociadoRepository;
import com.contamov.repository.ContaRepository;
import com.contamov.repository.MovimentacaoContaRepository;
import com.contamov.util.DateUtil;
import java.math.BigDecimal;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoContaService {
  
  @Autowired
  MovimentacaoContaRepository movimentacaoContaRepository;
  
  @Autowired
  ContaRepository contaRepository;
  
  @Autowired
  AssociadoRepository associadoRepository;


  public MovimentacaoConta salvar(MovimentacaoConta movimentacaoConta) {
    return movimentacaoContaRepository.save(movimentacaoConta);
  }
  
  public void deleteById(Long id) {
    movimentacaoContaRepository.deleteById(id);
  }
  
  public int processarArquivo(String fileImp) {
    int count = 0;
    try (Scanner scanner = new Scanner(fileImp)) {
      String line;
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        if (line.startsWith("M"))
          processarMov(line);
        else
          processarConta(line);
        count++;
      }
    }
    return count;
  }
  
  private void processarMov(String line) {
    String[] mov = line.split(";");
    String numeroAgencia = mov[1],
           numeroConta = mov[2],
           tipoOperacao = mov[3],
           dataMovimentacao = mov[4],
           valorMovimentacao = mov[5];
    //
    Conta conta = contaRepository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
    if (conta == null) {
      conta = new Conta(numeroAgencia, numeroConta);
      contaRepository.save(conta);
    }
    processarAssociado(line);
    //------------------------------------------------------------------------------------------------------------------
    MovimentacaoConta movConta = new MovimentacaoConta(
        conta, tipoOperacao.charAt(0), new BigDecimal(valorMovimentacao), DateUtil.getDate(dataMovimentacao)
    );
    salvar(movConta);
  }
  
  private void processarAssociado(String line) {
    String[] values = line.split(";");
    String cnpjCpf = values[3],
           nome = values[4];
    //
    Associado associado = associadoRepository.findByNomeAndCnpjCpf(nome, cnpjCpf);
    if (associado == null)
      associadoRepository.save(associado);
  }
  
  private void processarConta(String line) {
    String[] values = line.split(";");
    String numeroAgencia = values[1],
           numeroConta = values[2];
    //
    Conta conta = contaRepository.findByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
    if (conta == null) {
      conta = new Conta(numeroAgencia, numeroConta);
      contaRepository.save(conta);
    }
  }
  
}
