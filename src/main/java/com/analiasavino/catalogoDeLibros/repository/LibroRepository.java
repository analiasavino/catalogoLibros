package com.analiasavino.catalogoDeLibros.repository;

import com.analiasavino.catalogoDeLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long>{

  Libro findByTitulo(String titulo);
}
