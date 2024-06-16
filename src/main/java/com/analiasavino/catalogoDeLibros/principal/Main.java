package com.analiasavino.catalogoDeLibros.principal;

import com.analiasavino.catalogoDeLibros.model.Datos;
import com.analiasavino.catalogoDeLibros.model.DatosLibros;
import com.analiasavino.catalogoDeLibros.model.Libro;
import com.analiasavino.catalogoDeLibros.repository.LibroRepository;
import com.analiasavino.catalogoDeLibros.services.ConsumoApi;
import com.analiasavino.catalogoDeLibros.services.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Main {

  //Atributos de uso (scope) global podemos usarlo en cualquier parte de nuestro codigo.

  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoApi consumoApi = new ConsumoApi();
  private ConvierteDatos conversor = new ConvierteDatos();
  private Scanner teclado = new Scanner(System.in);
  private List<Datos> datosLibros = new ArrayList<>();
  @Autowired
  private LibroRepository repositoryLibros;

  public Main(LibroRepository repositoryLibros) {
    this.repositoryLibros = repositoryLibros;
  }

  //metodo que me permite mostrar el menu

  public void muestraElMenu() {
    int opcion = 0;
    while (opcion != 7) {
      //declaro la variable menuPcipal que tiene scope solo dentro del while

      var menuPrincipal = """
            \n
            1 - Buscar libros por tíulo.
            2 - Buscar libros por autor.
            3 - Lista de libros guardados.
            4-  Lista de autores registrados.
            5 - Lista de autores vivos en determinado año.
            6 - Lista de libros por idioma.
            7 - Salir.
            """;
      System.out.println(menuPrincipal);
      opcion = teclado.nextInt();
      teclado.nextLine();

      //en funcion de la opcion elejida se ejecuta el swithc
      switch (opcion) {
        case 1:
          buscarLibro();
          guardarLibro();
          break;
        case 2:
          // buscarLibroPorAutor();
          break;
        case 3:
          //listarLibrosBuscados();
          break;
        case 4:
          System.out.println("caso4");
          break;
        case 5:
          System.out.println("caso5");
          break;
        case 6:
          System.out.println("caso6");
          break;
        case 7:
          System.out.println("Saliendo del sistema");
          break;
        default:
          System.out.println("Opción inválida.");
      }
    }
  }

  //Metodos

  private Datos buscarLibro() {
    System.out.println("Por favor ingrese el nombre del libro que desee buscar");
    var tituloLibro = teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
    Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
          .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
          .findFirst();
    if (libroBuscado.isPresent()) {
      System.out.println("Libro Encontrado ");
    } else {
      System.out.println("Libro no encontrado");
    }
    return datosBusqueda;
  }

  private void guardarLibro() {
    Datos datos = buscarLibro();
    if (datos != null && !datos.resultados().isEmpty()) {
      DatosLibros libro = datos.resultados().get(0);

      Libro libroAGuardar = new Libro(libro);
      Optional<Libro> libroExiste = repositoryLibros.findByTitulo(libroAGuardar.getTitulo());
      if (libroExiste.isPresent()) {
        System.out.println("El" + libroExiste + "ya se encuentra registrado en nuestra base de datos.");
      } else {
        repositoryLibros.save(libroAGuardar);
        System.out.println("El libro: " + libroAGuardar + "Se guardo correctamente en la base de datos.");

      }
    }
  }
}

/*  private void buscarLibroPorAutor() {
  }

  private void listarLibrosBuscados() {
    List<Libro> libros = repositoryLibros.findAll();
    System.out.println("La lista de libros guardados hasta ahora es la siguiente: ");

    libros.stream()
          .sorted(Comparator.comparing(Libro::getId))
          .forEach(System.out::println);

  }
}*/


