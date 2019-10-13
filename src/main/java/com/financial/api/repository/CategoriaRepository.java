package com.financial.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
