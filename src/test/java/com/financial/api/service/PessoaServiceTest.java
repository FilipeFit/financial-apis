package com.financial.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.financial.api.model.Cidade;
import com.financial.api.model.Contato;
import com.financial.api.model.Endereco;
import com.financial.api.model.Estado;
import com.financial.api.model.Pessoa;
import com.financial.api.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

public class PessoaServiceTest {

  private PessoaService pessoaService;
  private Pessoa pessoa;
  private Endereco endereco;

  @Mock
  private PessoaRepository pessoaRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    pessoaService = new PessoaService(pessoaRepository);
    Estado estado = Estado.builder().nome("São Paulo").codigo(1l).build();
    Cidade cidade = Cidade.builder().nome("Campinas").estado(estado).build();
    endereco = Endereco.builder()
        .bairro("teste")
        .cep("13466110")
        .cidade(cidade)
        .logradouro("rua teste")
        .complemento("apt 1233")
        .numero("103")
        .build();
    Contato contato = Contato.builder()
        .nome("Teste contato")
        .email("teste@teste.com")
        .telefone("19 991005806")
        .codigo(1l)
        .build();
    pessoa = Pessoa.builder().codigo(1l).ativo(true).nome("Filioe").endereco(endereco).contatos(
        Arrays.asList(contato)).build();
  }


  @Test
  public void buscarPessoaPeloCodigo() {

    when(pessoaRepository.findById(1l)).thenReturn(Optional.of(pessoa));
    Pessoa pessoa = pessoaService.buscarPessoaPeloCodigo(1l);
    assertEquals(endereco, pessoa.getEndereco());
    //How many times do we call the method
    verify(pessoaRepository, times(1)).findById(1l);

  }
}