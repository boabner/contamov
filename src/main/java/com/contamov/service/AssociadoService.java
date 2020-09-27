/**
 * @author abner
 */
package com.contamov.service;

import com.contamov.model.Associado;
import com.contamov.model.Conta;
import com.contamov.repository.AssociadoRepository;
import com.contamov.service.dto.AssociadoDTO;
import com.contamov.service.dto.AssociadoResponseDTO;
import com.contamov.service.validations.AssociadoValidation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
  
  @Autowired
  AssociadoRepository associadoRepository;

  @Autowired
  ContaService contaService;
  
  public AssociadoResponseDTO atualizar(Associado associado) {
    AssociadoResponseDTO dto = new AssociadoResponseDTO();
    Optional<Associado> opAssociado = associadoRepository.findById(associado.getId());
    if (opAssociado.isPresent()) {
      Associado associadoBD = opAssociado.get();
      opAssociado = associadoRepository.findByCnpjCpf(associado.getCnpjCpf());
      if (opAssociado.isPresent()) {
        associadoBD = opAssociado.get();
        if (!associado.getNome().equals(associadoBD.getNome())) {
          dto.setMessage("Operação não permitida.");
          dto.setStatus(HttpStatus.CONFLICT);
        }
        else {
          associadoBD.setCnpjCpf(associado.getCnpjCpf());
          associado = associadoRepository.save(associadoBD);
          BeanUtils.copyProperties(associado, dto);
          dto.setMessage("Associado atualizado com sucesso.");
          dto.setStatus(HttpStatus.OK);
        }
      }
      else {
        associadoBD.setCnpjCpf(associado.getCnpjCpf());
        associado = associadoRepository.save(associadoBD);
        BeanUtils.copyProperties(associado, dto);
        dto.setMessage("Associado atualizado com sucesso.");
        dto.setStatus(HttpStatus.OK);
      }
    }
    else {
      dto.setStatus(HttpStatus.CONFLICT);
      dto.setMessage("Associado não encontrado com esse ID.");
    }
    return dto;
  }

  public AssociadoResponseDTO salvar(AssociadoDTO associadoDTO) {
    String msg = AssociadoValidation.validarAssociadoExistente(associadoDTO.getNome(), associadoDTO.getCnpjCpf(), associadoRepository);
    AssociadoResponseDTO dto = new AssociadoResponseDTO();
    if (!msg.contains("Erro")) {
      Optional<Conta> opConta = contaService.getByNumeroAgenciaAndNumeroConta(associadoDTO.getNumeroAgencia(), associadoDTO.getNumeroConta());
      if (opConta.isPresent()) {
        Conta conta = opConta.get();
        Associado associadoSave = associadoDTO.transformaParaObjeto();
        associadoSave.setConta(conta);
        associadoRepository.save(associadoSave);
        BeanUtils.copyProperties(associadoSave, dto);
        dto.setMessage("Associado criado com sucesso.");
        dto.setStatus(HttpStatus.CREATED);
      }
      else {
        dto.setStatus(HttpStatus.CONFLICT);
        dto.setMessage("Conta não encontrada.");
      }
    }
    else {
      dto.setStatus(HttpStatus.CONFLICT);
      dto.setMessage(msg);
    }
    return dto;
  }
  
  public void deleteById(Long id) {
    associadoRepository.deleteById(id);
  }
  
  public List<Associado> findAll() {
    return associadoRepository.findAll();
  }
  
  public Optional<Associado> findAssociadoById(Long id) {
    return associadoRepository.findById(id);
  }
  
  public List<Associado> findAssociadoByConta(Conta conta) {
    return associadoRepository.findAllByConta(conta);
  }
  
  public Associado getAssociadoByNomeAndCnpjCpfOrCreate(String nome, String cnpjCpf, Conta conta) {
    Optional<Associado> opAssociado = associadoRepository.findByNomeAndCnpjCpf(nome, cnpjCpf);
    Associado associado;
    if (!opAssociado.isPresent()) {
      associado = new Associado(nome, cnpjCpf, conta);
      associadoRepository.save(associado);
    }
    else
      associado = opAssociado.get();
    return associado;
  }
 
  public List<AssociadoDTO> getListAssociadoDTOByListAssociado(List<Associado> listAssociado) {
    List<AssociadoDTO> listDto = new ArrayList();
    for(Associado associado : listAssociado) {
      AssociadoDTO dto = new AssociadoDTO();
      BeanUtils.copyProperties(associado, dto);
      listDto.add(dto);
    }
    return listDto;
  }
  
}