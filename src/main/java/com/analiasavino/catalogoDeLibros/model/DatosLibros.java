package com.analiasavino.catalogoDeLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (
      @JsonAlias("title") String titulo,
      @JsonAlias("authors") List<DatosAutor> autor,
      @JsonAlias("languages") List<String> idiomas,
      @JsonAlias("download_count") Integer numeroDeDescargas)
{
  @Override
  public String toString() {
    return
    "\n ************************************************************************************\n"+
          "Datos del libro solicitado:" + "\n" +
               "Titulo: '" + titulo + '\'' + "\n" +
               "Autor: " + autor + "\n" +
               "Idiomas disponibles: " + idiomas + "\n"+
               "NumeroDeDescargas: " + numeroDeDescargas +"\n"+
    "**************************************************************************************\n";

  }
}
