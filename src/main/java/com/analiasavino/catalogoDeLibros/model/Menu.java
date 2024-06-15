package com.analiasavino.catalogoDeLibros.model;

public record Menu() {
  static String  menuPrincipal = """
            \n
            1 - Buscar libros por tíulo.
            2 - Buscar libros por autor.
            3 - Lista de libros guardados.
            4-  Lista de autores registrados.
            5 - Lista de autores vivos en determinado año.
            6 - Lista de libros por idioma.
            7 - Salir.
            """;
  static String menuIdiomas = """
        \n
        es = Español.
        en = Ingles.
        pr = Portugues.
        fr = Frances.
        """;

  public static String getMenuPrincipal() {
    return menuPrincipal;
  }

  public static String getMenuIdiomas() {
    return menuIdiomas;
  }


}

