package com.analiasavino.catalogoDeLibros.principal;

import com.analiasavino.catalogoDeLibros.model.Datos;
import com.analiasavino.catalogoDeLibros.model.DatosLibros;
import com.analiasavino.catalogoDeLibros.model.Libro;
import com.analiasavino.catalogoDeLibros.repository.LibroRepository;
import com.analiasavino.catalogoDeLibros.services.ConsumoApi;
import com.analiasavino.catalogoDeLibros.services.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

  //Atributos de uso (scope) global podemos usarlo en cualquier parte de nuestro codigo.

  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoApi consumoApi = new ConsumoApi();
  private ConvierteDatos conversor = new ConvierteDatos();
  private Scanner teclado = new Scanner(System.in);
  private List<Datos> datosLibros = new ArrayList<>();
  private Libro libro = new Libro();
  @Autowired
  private LibroRepository repositoryLibros;

  public Main(LibroRepository repositoryLibros) {
    this.repositoryLibros = repositoryLibros;
  }

  //metodo que me permite mostrar el menu

  public void muestraElMenu(){
    System.out.println("\n Bienvenido al catalogo de libros de Analia \n");
    int opcion = -1;
    while (opcion != 7) {
      System.out.println( """
            1 - Buscar libros por tíulo.
            2 - Guardar libro en base de datos.
            3 - Lista de libros guardados.
            4-  Lista de autores registrados.
            5 - Lista de autores vivos en determinado año.
            6 - Lista de libros por idioma.
            7 - Salir.
            """);
      opcion = teclado.nextInt();
      teclado.nextLine();
      //en funcion de la opcion elejida se ejecuta el switch
      switch (opcion) {
        case 1:
          buscarLibro();
          break;
        case 2:
          guardarLibro();
          break;
        case 3:
          System.out.println("caso3");
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

  private Libro buscarLibro() {
    System.out.println("Por favor ingrese el nombre del libro que desee buscar");
    var tituloLibro = teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
    Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
          .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
          .findFirst();
    if (libroBuscado.isPresent()) {
      System.out.println(libroBuscado.get());
      var libro = libroBuscado.get();

    } else {
      System.out.println("Libro no encontrado");
    }
    return libro;
  }

  private void guardarLibro(){

    Optional<Libro> libroExiste = repositoryLibros.findByTitulo(libro.getTitulo());
    if (libroExiste.isPresent()) {
      System.out.println("\nEl libro ya esta registrado en nuestra base de datos.\n");
    } else {
      System.out.println("libro no encontrado en nuestra base de datos");
      System.out.println("Desea guardar el libro en nuestra base de datos?");
      Scanner scanner= new Scanner(System.in);
      var opcionCarga = teclado.nextInt();
      switch (opcionCarga) {
        case 1:
          repositoryLibros.save(libro);
          System.out.println("Libro guardado correctamente");
          break;
        case 2:
          System.out.println("El libro no se guardara");
          break;
        default:
          System.out.println("Opcion invalida");
      }
  }
    }
}
