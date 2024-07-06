package com.analiasavino.catalogoDeLibros.repository;

import com.analiasavino.catalogoDeLibros.model.Idioma;
import com.analiasavino.catalogoDeLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

  List<Libro> findByIdioma(Idioma idioma);

  Optional<Libro> findByTitulo(String titulo);

// @Query("SELECT l from Libro l ORDER BY l.numeroDeDecargas DESC LIMIT 10")
 List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();
}
