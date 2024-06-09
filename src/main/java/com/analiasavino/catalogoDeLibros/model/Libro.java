package com.analiasavino.catalogoDeLibros.model;

import java.util.List;


public class Libro {
  //declaro las variables.
  private String titulo;
  private List<DatosAutor> autor;
  private List<String> idiomas;
  private Integer numeroDeDescargas;

  //constructor

  public Libro(DatosLibros datosLibros){
    this.titulo = datosLibros.titulo();
    this.autor = datosLibros.autor();
    this.idiomas = datosLibros.idiomas();
    this.numeroDeDescargas = datosLibros.numeroDeDescargas();

}
//metodos getter and setters.

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public List<DatosAutor> getAutor() {
    return autor;
  }

  public void setAutor(List<DatosAutor> autor) {
    this.autor = autor;
  }

  public List<String> getIdiomas() {
    return idiomas;
  }

  public void setIdiomas(List<String> idiomas) {
    this.idiomas = idiomas;
  }

  public Integer getNumeroDeDescargas() {
    return numeroDeDescargas;
  }

  public void setNumeroDeDescargas(Integer numeroDeDescargas) {
    this.numeroDeDescargas = numeroDeDescargas;
  }
  //metodo toString


}
