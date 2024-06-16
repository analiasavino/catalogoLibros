package com.analiasavino.catalogoDeLibros.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "libros")
public class Libro {
  //declaro las variables.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @Column(unique = true)
  private String titulo;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "autor_id")
  private Autor autor;
  @Enumerated(EnumType.STRING)
  private Idioma idioma;
  private Integer numeroDeDescargas;

  //Constructor predeterminado.
  public Libro(){}

  //constructor

  public Libro(DatosLibros datosLibros){
    this.titulo = datosLibros.titulo();
    this.autor = new Autor();
    this.idioma = Idioma.fromEspanol(datosLibros.idioma().toString().split(",")[0].trim());
    this.numeroDeDescargas = datosLibros.numeroDeDescargas();

}

  public Libro(List<Datos> datosLibros) {
  }

//metodos getter and setters.

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  //public List<DatosAutor> getAutor() {    return autor;}

  public void setAutor(List<DatosAutor> autor) {    this.autor = autor;}

  public Idioma getIdioma() { return idioma; }

  public void setIdioma(Idioma idioma) { this.idioma = idioma; }

  public Integer getNumeroDeDescargas() {
    return numeroDeDescargas;
  }

  public void setNumeroDeDescargas(Integer numeroDeDescargas) {
    this.numeroDeDescargas = numeroDeDescargas;
  }

  //metodo toString


  @Override
  public String toString() {
    return "Libro guardado: " +
           "Id: " + Id +
           ", titulo: " + titulo + '\'' +
           ", autor: " + autor +
           ", idiomas: " + idioma +
           ", numeroDeDescargas: " + numeroDeDescargas;
  }
}
