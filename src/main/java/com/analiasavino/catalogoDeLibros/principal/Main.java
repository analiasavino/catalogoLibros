package com.analiasavino.catalogoDeLibros.principal;

import com.analiasavino.catalogoDeLibros.model.*;
import com.analiasavino.catalogoDeLibros.repository.LibroRepository;
import com.analiasavino.catalogoDeLibros.services.ConsumoApi;
import com.analiasavino.catalogoDeLibros.services.ConvierteDatos;

import java.util.*;
import java.util.Optional;

import static com.analiasavino.catalogoDeLibros.model.Menu.*;

public class Main {

  //Atributos de uso (scope) global podemos usarlo en cualquier parte de nuestro codigo.

  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoApi consumoApi = new ConsumoApi();
  private ConvierteDatos conversor = new ConvierteDatos();
  private Scanner teclado = new Scanner(System.in);
  private List<Datos> datosLibros =new ArrayList<>();
  private LibroRepository repository;
  Libro libro = new Libro();
  private com.analiasavino.catalogoDeLibros.model.Idioma Idioma;


  public Main(LibroRepository repository) {
    this.repository = repository;
  }

  //metodo que me permite mostrar el menu


  public void muestraElMenu() {
    int opcion = -1;
    while (opcion != 7) {
      System.out.println(getMenuPrincipal());
      opcion = teclado.nextInt();
      teclado.nextLine();

      //en funcion de la opcion elejida se ejecuta el swithc
      switch (opcion) {
        case 1:
          buscarInfo();
          salvarLibro();
          break;
        case 2:
          //buscarLibroPorAutor();
          break;
        case 3:
          //listarLibrosBuscados();
          break;
        case 4:
          System.out.println("caso 4");
          break;
        case 5:
          System.out.println("caso5");
          break;
        case 6:
          System.out.println("Por favor ingrese el idioma por el cual desea realizar su busqueda:");
          System.out.println(getMenuIdiomas());
          //listarLibrosPorIdiomas();
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
  private Datos buscarInfo() {
    System.out.println("Por favor ingrese el nombre del libro que desee buscar:");
      var tituloLibro = teclado.nextLine();
      var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
      var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
      Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
            .findFirst();
      if (libroBuscado.isPresent()) {
        System.out.println(libroBuscado.get());
      } else {
        System.out.println("Libro no encontrado.");
      }
      return datosBusqueda;
  }

   private void salvarLibro() {
     Optional<Libro> optionalLibro = repository.findByTitulo(String titulo);
     if (optionalLibro.isPresent()) {
       Libro libro = optionalLibro.get();
       System.out.println("El libro ya se encuentra registrado");
     } else {
       repository.save(libro);
     }
    }

  }

 /* private void buscarLibroPorAutor() {
    System.out.println("Por favor ingrese el nombre del autor que desee buscar");
    var autorBuscado= teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + ));
    var datosBusquedaXAutor = conversor.obtenerDatos(json, Datos.class);
    Optional<DatosLibros> libroBuscado = datosBusquedaXAutor.resultados().stream()
          .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
          .findFirst();

    if (libroBuscado.isPresent()) {
      System.out.println(libroBuscado.get());
      Libro libro = new Libro(libroBuscado.get());
      repository.save(libro);

    } else {
      System.out.println("Libro no encontrado");
    }

    return datosBusqueda;
  }*/

/*  private void listarLibrosBuscados() {
    List<Libro> libros = repository.findAll();
    System.out.println("La lista de libros guardados hasta ahora es la siguiente: ");

    libros.stream()
          .sorted(Comparator.comparing(Libro::getId))
          .forEach(System.out::println);

  }


  private void listarLibrosPorIdiomas() {
    System.out.println("Escriba el idioma del libro que desea buscar");
    var idiomaIngresado = teclado.nextLine();
    var idioma = Idioma.fromEspanol(idiomaIngresado);
    List<Libro> librosPorIdioma= repository.findByIdioma(idioma);
    System.out.println("Los libros en " + idioma + "son los siguentes");
    librosPorIdioma.forEach(System.out::println);
  }

}*/


