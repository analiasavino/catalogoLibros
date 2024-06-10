package com.analiasavino.catalogoDeLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
      @JsonAlias("name") String nombre,
      @JsonAlias("birth_year") String fechaDeNacimiento,
      @JsonAlias("death_year") String fechaDeFallecimiento

) {
}
