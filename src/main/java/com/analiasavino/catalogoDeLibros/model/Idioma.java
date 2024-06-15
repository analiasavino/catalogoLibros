package com.analiasavino.catalogoDeLibros.model;

import jdk.jfr.Category;

public enum Idioma {
  Espanol("es","espanol"),
  Ingles("en", "ingles"),
  Frnaces("fr", "frances"),
  Portugues("pt", "portugues");

  private String idiomaGuntedex;
  private String idiomaaEspanol;
  Idioma(String idiomaGuntedex, String idiomaaEspanol){
    this.idiomaGuntedex = idiomaGuntedex;
    this.idiomaaEspanol = idiomaaEspanol;
  }
  public static Idioma fromString(String text){
    for(Idioma idioma: Idioma.values()){
      if(idioma.idiomaGuntedex.equalsIgnoreCase(text)){
        return idioma;
      }
    }
    throw new IllegalArgumentException("No se ha econtrado el idioma seleccionado : " + text);
  }
  public static Idioma fromEspanol(String text){
    for(Idioma idioma: Idioma.values()){
      if(idioma.idiomaGuntedex.equalsIgnoreCase(text)){
        return idioma;
      }
    }
    throw new IllegalArgumentException("No se ha econtrado el idioma seleccionado : " + text);
  }

}
