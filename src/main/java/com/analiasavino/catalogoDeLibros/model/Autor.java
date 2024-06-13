package com.analiasavino.catalogoDeLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autores")

public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  @Column(unique = true)
  private String nombre;
  private String fechaDeNacimiento;
  private String fechaDeFallecimiento;
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
  private List<Libro> libro;

  //constructor predeterminado es requisito para las bases de datos
  public Autor(){}
  //constructor

  public Autor(DatosAutor datosAutor) {
    this.nombre = nombre;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.fechaDeFallecimiento = fechaDeFallecimiento;
  }

  //getters and setters

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(String fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public String getFechaDeFallecimiento() {
    return fechaDeFallecimiento;
  }

  public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
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
    return "Autor{" +
          "Id=" + Id +
          ", nombre='" + nombre + '\'' +
          ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
          ", fechaDeFallecimiento='" + fechaDeFallecimiento + '\'' +
          ", libro=" + libro +
          '}';
  }
}
