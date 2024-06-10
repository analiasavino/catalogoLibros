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
  private String nombre;
  private String fechaDeNacimiento;
  private String fechaDeFallecimiento;
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Libro libro;

  //constructor predeterminado es requisito para las bases de datos
  public Autor(){}
  //constructor

  public Autor(DatosAutor datosAutor) {
    this.nombre = nombre;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.fechaDeFallecimiento = fechaDeFallecimiento;
  }

  //getters and setters


  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

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

}
