package com.financial.api.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.financial.api.model.Categoria;
import com.financial.api.repository.CategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class CategoriaResourceTest {

  @Mock
  private CategoriaRepository categoriaRepository;

  @Mock
  private ApplicationEventPublisher publisher;

  private CategoriaResource categoriaResource;
  private Categoria categoria;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    categoriaResource = new CategoriaResource(categoriaRepository,publisher);
    categoria = Categoria.builder().codigo(1l).nome("test categoria").build();
  }

  @Test
  public void listar() {
    when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));

    List<Categoria> categorais = categoriaResource.listar();
    assertEquals(1,categorais.size());
    verify(categoriaRepository,times(1)).findAll();

  }

  @Test
  public void testMockMVC() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(categoriaResource).build();

    mockMvc.perform(get("/categorias")).andExpect(status().isOk());
  }

  @Test
  public void criar() {
  }

  @Test
  public void buscarPeloCodigo() {
  }
}