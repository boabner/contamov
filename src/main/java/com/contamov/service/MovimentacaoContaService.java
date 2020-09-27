/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Associado;
import com.contamov.model.Conta;
import com.contamov.model.MovimentacaoConta;
import com.contamov.repository.MovimentacaoContaRepository;
import com.contamov.service.dto.AssociadoDTO;
import com.contamov.service.dto.ContaDTO;
import com.contamov.service.dto.DadosContaNegativaResponseDTO;
import com.contamov.service.dto.MovimentacaoContaDTO;
import com.contamov.service.dto.DadosContaResponseDTO;
import com.contamov.service.dto.MovimentacaoContaResponseDTO;
import com.contamov.service.validations.ContaValidation;
import com.contamov.service.validations.MovimentacaoContaValidation;
import com.contamov.util.BigDecimalUtil;
import com.contamov.util.DateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoContaService {
  
  @Autowired
  MovimentacaoContaRepository movimentacaoContaRepository;
  
  @Autowired
  ContaService contaService;
  
  @Autowired
  AssociadoService associadoService;


  public MovimentacaoConta salvar(MovimentacaoConta movimentacaoConta) {
    return movimentacaoContaRepository.save(movimentacaoConta);
  }
  
  public void deleteById(Long id) {
    movimentacaoContaRepository.deleteById(id);
  }
  
  public MovimentacaoContaResponseDTO processarArquivo(String nomeArquivo, String fileImp) {
    int count = 1, countOk = 0;
    StringBuilder responseError = new StringBuilder();
    //------------------------------------------------------------------------------------------------------------------
    try (Scanner scanner = new Scanner(fileImp)) {
      String line, ret;
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        //--------------------------------------------------------------------------------------------------------------
        if (line.startsWith("C")) {
          ret = ContaValidation.validarDadosConta(line, count);
          //------------------------------------------------------------------------------------------------------------
          if (ret.contains("Erro"))
            responseError.append(ret);
          else {
            processarConta(line);
            countOk++;
          }
        }
        else if (line.startsWith("M")) {
          ret = MovimentacaoContaValidation.validarDadosMovimentacao(line, count);
          if (ret.contains("Erro"))
            responseError.append(ret);
          else {
            processarMov(line);
            countOk++;
          }
        }
        count++;
      }
    }
    return getResponseBodyMovimentacaoConta(nomeArquivo, responseError.toString(), countOk);
  }
  
  private MovimentacaoContaResponseDTO getResponseBodyMovimentacaoConta(String nomeArquivo, String responseError, int countOk) {
    boolean success = countOk > 0,
            error = responseError.trim().length() > 0;
    //------------------------------------------------------------------------------------------------------------------
    MovimentacaoContaResponseDTO dto = new MovimentacaoContaResponseDTO(
        nomeArquivo,
        error ? responseError : "Nenhum erro encontrado.",
        success ? (countOk + " registro(s) armazenado(s) com sucesso.")  : "",
        error ? HttpStatus.MULTI_STATUS : HttpStatus.CREATED
    );
    return dto;
  }
  
  private String processarMov(String line) {
    String[] mov = line.split(",");
    String numeroAgencia = mov[1],
           numeroConta = mov[2],
           tipoOperacao = mov[3],
           dataMovimentacao = mov[4],
           valorMovimentacao = mov[5];
    //------------------------------------------------------------------------------------------------------------------
    Conta conta = contaService.getContaByNumeroAgenciaAndNumeroContaOrCreate(numeroAgencia, numeroConta);
    //------------------------------------------------------------------------------------------------------------------
    if (conta.getId() != null) {
      MovimentacaoConta movConta = new MovimentacaoConta(
          conta, tipoOperacao.charAt(0), new BigDecimal(valorMovimentacao), DateUtil.getDate(dataMovimentacao)
      );
      salvar(movConta);
    }
    return "ok";
  }

  
  private String processarConta(String line) {
    //------------------------------------------------------------------------------------------------------------------
    String[] values = line.split(",");
    String numeroAgencia, numeroConta, nome, cnpjCpf;
    //------------------------------------------------------------------------------------------------------------------
    numeroAgencia = values[1];
    numeroConta = values[2];
    nome = values[4];
    cnpjCpf = values[3];
    //------------------------------------------------------------------------------------------------------------------
    Conta conta = contaService.getContaByNumeroAgenciaAndNumeroContaOrCreate(numeroAgencia, numeroConta);
    processarAssociado(nome, cnpjCpf, conta);
    //------------------------------------------------------------------------------------------------------------------
    return "ok";
  }

  private Associado processarAssociado(String nome, String cnpjCpf, Conta conta) {
    Associado associado = associadoService.getAssociadoByNomeAndCnpjCpfOrCreate(nome, cnpjCpf, conta);
    return associado;
  }
  
  private List<MovimentacaoContaDTO> getListMovContDTOByListMovConta(List<MovimentacaoConta> listMov) {
    List<MovimentacaoContaDTO> listDto = new ArrayList();
    for (MovimentacaoConta mov : listMov) {
      MovimentacaoContaDTO dto = new MovimentacaoContaDTO();
      BeanUtils.copyProperties(mov, dto);
      listDto.add(dto);
    }
    return listDto;
  }
  
  public List<DadosContaResponseDTO> getDadosContaAndMovimentacaoByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta) {
    List<Conta> contas;
    //------------------------------------------------------------------------------------------------------------------
    if (numeroConta == null)
      contas = contaService.getByNumeroAgencia(numeroAgencia);
    else {
      contas = new ArrayList<>();
      Optional<Conta> opConta = contaService.getByNumeroAgenciaAndNumeroConta(numeroAgencia, numeroConta);
      if (opConta.isPresent())
        contas.add(opConta.get());
    }
    //------------------------------------------------------------------------------------------------------------------
    List<DadosContaResponseDTO> listDto = new ArrayList();
    if (!contas.isEmpty()) {
      //----------------------------------------------------------------------------------------------------------------
      for (Conta conta : contas) {
        //--------------------------------------------------------------------------------------------------------------
        List<AssociadoDTO> listAssociadoDTO = 
            associadoService.getListAssociadoDTOByListAssociado(associadoService.findAssociadoByConta(conta));
        //--------------------------------------------------------------------------------------------------------------
        List<MovimentacaoContaDTO> listMov = 
            getListMovContDTOByListMovConta(movimentacaoContaRepository.findAllByContaOrderByDataMovimentacaoDesc(conta));
        //--------------------------------------------------------------------------------------------------------------
        BigDecimal totalDebito = BigDecimal.ZERO, totalCredito = BigDecimal.ZERO, saldoFinal;
        //--------------------------------------------------------------------------------------------------------------
        for (MovimentacaoContaDTO movConta : listMov) {
          if (movConta.getTipoOperacao() == 'D')
            totalDebito = BigDecimalUtil.add(totalDebito, movConta.getValorMovimentacao(), 2);
          else
            totalCredito = BigDecimalUtil.add(totalCredito, movConta.getValorMovimentacao(), 2);
        }
        saldoFinal = BigDecimalUtil.subtract(totalCredito, totalDebito, 2);
        //--------------------------------------------------------------------------------------------------------------
        ContaDTO contaDTO = new ContaDTO();
        BeanUtils.copyProperties(conta, contaDTO);
        //--------------------------------------------------------------------------------------------------------------
        DadosContaResponseDTO dto = new DadosContaResponseDTO(
            contaDTO, listMov, listAssociadoDTO, totalDebito, totalCredito, saldoFinal
        );
        listDto.add(dto);
      }
    }
    return listDto;
  }
  
  public List<DadosContaNegativaResponseDTO> getAllContaNegativa() {
    List<Conta> contas = contaService.findAll();
    //------------------------------------------------------------------------------------------------------------------
    List<DadosContaNegativaResponseDTO> listDto = new ArrayList();
    if (!contas.isEmpty()) {
      //----------------------------------------------------------------------------------------------------------------
      for (Conta conta : contas) {
        //--------------------------------------------------------------------------------------------------------------
        List<MovimentacaoContaDTO> listMov = 
            getListMovContDTOByListMovConta(movimentacaoContaRepository.findAllByContaOrderByDataMovimentacaoDesc(conta));
        //--------------------------------------------------------------------------------------------------------------
        BigDecimal totalDebito = BigDecimal.ZERO, totalCredito = BigDecimal.ZERO, saldoFinal;
        //--------------------------------------------------------------------------------------------------------------
        for (MovimentacaoContaDTO movConta : listMov) {
          if (movConta.getTipoOperacao() == 'D')
            totalDebito = BigDecimalUtil.add(totalDebito, movConta.getValorMovimentacao(), 2);
          else
            totalCredito = BigDecimalUtil.add(totalCredito, movConta.getValorMovimentacao(), 2);
        }
        saldoFinal = BigDecimalUtil.subtract(totalCredito, totalDebito, 2);
        if (!BigDecimalUtil.isNegative(saldoFinal))
          continue;
        //--------------------------------------------------------------------------------------------------------------        
        List<AssociadoDTO> listAssociadoDTO = 
            associadoService.getListAssociadoDTOByListAssociado(associadoService.findAssociadoByConta(conta));
        //--------------------------------------------------------------------------------------------------------------
        ContaDTO contaDTO = new ContaDTO();
        BeanUtils.copyProperties(conta, contaDTO);
        //--------------------------------------------------------------------------------------------------------------
        DadosContaNegativaResponseDTO dto = new DadosContaNegativaResponseDTO(
            contaDTO, listAssociadoDTO, saldoFinal
        );
        listDto.add(dto);
      }
    }
    return listDto;
  }
  
}