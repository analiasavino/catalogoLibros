package com.analiasavino.catalogoDeLibros.model;

public record Menu() {
  static String menuPrincipal = """
        1 - Buscar libro por titulo.
        2 - Buscar libro por autor.
        3 - Listar libros guardados.
        """;
}
