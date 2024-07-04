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
  @ManyToOne()
  @JoinColumn(name ="autor_id")
  private Autor autor;
  private List<String> idioma;
  private Integer numeroDeDescargas;

  //Constructor predeterminado.
  public Libro(){}

  //constructor

  public Libro(DatosLibros datosLibros){
    this.titulo = datosLibros.titulo();
    this.idioma = datosLibros.idiomas();
    this.numeroDeDescargas = datosLibros.numeroDeDescargas();

}

  public Libro(List<Datos> datosLibros) {
  }


//metodos getter and setters.


  public Long getId() { return Id;  }

  public void setId(Long id) { Id = id;  }

  public String getTitulo() { return titulo;  }

  public void setTitulo(String titulo) { this.titulo = titulo;  }

  public Autor getAutor() { return autor;}

  public void setAutor(Autor autor) { this.autor = autor;}

  public List<String> getIdiomas() { return idioma;  }

  public void setIdiomas(List<String> idiomas) {this.idioma = idiomas; }

  public Integer getNumeroDeDescargas() {return numeroDeDescargas;  }

  public void setNumeroDeDescargas(Integer numeroDeDescargas) {this.numeroDeDescargas = numeroDeDescargas;  }

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

