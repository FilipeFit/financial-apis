package com.financial.api.service;

import com.financial.api.model.Pessoa;
import com.financial.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;

  public PessoaService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  public Pessoa salvar(Pessoa pessoa) {
    pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
    return pessoaRepository.save(pessoa);
  }

  public Pessoa atualizar(Long codigo, Pessoa pessoa) {
    Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);

    pessoaSalva.getContatos().clear();
    pessoaSalva.getContatos().addAll(pessoa.getContatos());
    pessoaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));

    BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo", "contatos");
    return pessoaRepository.save(pessoaSalva);
  }

  public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
    Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
    pessoaSalva.setAtivo(ativo);
    pessoaRepository.save(pessoaSalva);
  }

  public Pessoa buscarPessoaPeloCodigo(Long codigo) {
    Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
    return pessoaSalva.orElseThrow(() -> new EmptyResultDataAccessException(1));
  }
}
