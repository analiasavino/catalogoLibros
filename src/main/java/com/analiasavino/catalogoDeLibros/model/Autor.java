package com.analiasavino.catalogoDeLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Autores")

public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @Column(unique = true)
  private String nombre;
  private Integer fechaDeNacimiento;
  private Integer fechaDeFallecimiento;
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Libro> libro;

  //constructor predeterminado es requisito para las bases de datos
  public Autor(){}
  //constructor

  public Autor(DatosAutor datosAutor) {
    this.nombre = datosAutor.nombre();
    this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
    this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
  }

  //getters and setters

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public Integer getFechaDeFallecimiento() {
    return fechaDeFallecimiento;
  }

  public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
    this.fechaDeFallecimiento = fechaDeFallecimiento;
  }
  public List<Libro> getLibros() {
    return libro;
  }

  public void setLibros(List<Libro> libros) {
    this.libro = libro;
  }

  @Override
  public String toString() {
    StringBuilder librosStr = new StringBuilder();
    librosStr.append("Libros: ");

    for(int i = 0; i < libro.size() ; i++) {
      librosStr.append(libro.get(i).getTitulo());
      if (i < libro.size() - 1 ){
        librosStr.append(", ");
      }
    }
    return String.format("---------- Autor ----------%nNombre:" +
          " %s%n%s%nFecha de Nacimiento: %s%nFecha de Deceso:" +
          " %s%n---------------------------%n",nombre,librosStr.toString(),fechaDeNacimiento,fechaDeFallecimiento);
  }

 /* @Override
  public String toString() {
    return "Autor{" +
          "Id=" + Id +
          ", nombre='" + nombre + '\'' +
          ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
          ", fechaDeFallecimiento='" + fechaDeFallecimiento + '\'' +
          ", libro=" + libro +
          '}';
  }*/
}
