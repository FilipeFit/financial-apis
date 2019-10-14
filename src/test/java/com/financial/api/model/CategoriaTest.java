package com.financial.api.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {

  private static final Long ID_VALUE = 4l;
  private static final String NOME_CATEGORIA = "taxes";

  Categoria categoria;

  @Before
  public void setUp() {
    categoria = Categoria.builder().codigo(4l).nome("taxes").build();
  }

  @Test
  public void getCodigo() {
    assertEquals(ID_VALUE, categoria.getCodigo());
  }

  @Test
  public void getNome() {
    assertEquals(NOME_CATEGORIA, categoria.getNome());
  }
}
