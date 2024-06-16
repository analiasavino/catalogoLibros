package com.analiasavino.catalogoDeLibros.repository;

import com.analiasavino.catalogoDeLibros.model.Idioma;
import com.analiasavino.catalogoDeLibros.model.Libro;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends JpaRepository<Libro, Long> {

 Optional<Libro>findByTitulo(String titulo);
 List<Libro> findByIdioma(Idioma idioma);



}



