package com.analiasavino.catalogoDeLibros.principal;

import com.analiasavino.catalogoDeLibros.model.*;
import com.analiasavino.catalogoDeLibros.repository.AutorRepository;
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
  private List<Libro> listadoLibros = new ArrayList<>();
  private List<Autor> listadoAutores = new ArrayList<>();


  @Autowired
  private LibroRepository repositoryLibros;

  @Autowired
  private AutorRepository repositoryAutores;

  public Main(LibroRepository repositoryLibros, AutorRepository repositoryAutores) {
    this.repositoryLibros = repositoryLibros;
    this.repositoryAutores = repositoryAutores;
  }

  //metodo que me permite mostrar el menu

  public void muestraElMenu() {
    System.out.println("\n Bienvenido al catalogo de libros de Analia \n");
    int opcion = -1;
    while (opcion != 7) {
      System.out.println("""
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
          buscarLibroApi();
          break;
        case 2:
          mostrarLibrosGuardados();
          break;
        case 3:
          mostrarAutoresRegistrados();
          break;
        case 4:
          autoresVivosSegunAnio();
          break;
        case 5:
          buscarLibroPorIdioma();
          break;
        case 6:
         // rankingLibros();
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

  private Datos getDatosApi() {
    System.out.println("Por favor ingrese el nombre del libro que desee buscar");
    var tituloLibro = teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

    return datosBusqueda;
  }

  private void buscarLibroApi() {
    Datos  libroApi = getDatosApi();

    //corroboro que el libro este en la api, y como hay varias opciones x libro me quedo con la primera
    if (libroApi != null && !libroApi.resultados().isEmpty()) {
      DatosLibros primeraOpcion = libroApi.resultados().getFirst();

      //como el libro esta lo muestro
      System.out.println(primeraOpcion);

      //instancio la clase libro para ya ver si lo guardo en la base de datos.
      Libro libro = new Libro(primeraOpcion);

      //Ahora vamos a ver si ya esta en la base de datos
      Optional<Libro> libroARegistrar = repositoryLibros.findByTitulo(libro.getTitulo());
      if (libroARegistrar.isPresent()) {
        System.out.println("\n El libro ya se encuentra registrado en nuestra base de datos. \n");
      } else {

        //Si el libro no esta registrado en la base de datos, corroboro si su autor ya esta cargado en lea tabla autor para no repetir
        //para eso primero instancio el objeto DatosAutor con los datos extraidos de la API
        if (!primeraOpcion.autor().isEmpty()) {
          DatosAutor autor = primeraOpcion.autor().getFirst();
          //creo un nuevo objeto autor con esos datos que extraemos de la api y vemos si esta en el repositorio
          Autor autor1 = new Autor(autor);
          Optional<Autor> autorOptional = repositoryAutores.findByNombre(autor1.getNombre());

          if (autorOptional.isPresent()) {
            Autor autorRegistrado = autorOptional.get();
            libro.setAutor(autorRegistrado);
            repositoryLibros.save(libro);
          } else {
            Autor autorNuevo = repositoryAutores.save(autor1);
            libro.setAutor(autorNuevo);
            repositoryLibros.save(libro);
          }
        }
      }
    } else {
      System.out.println("El libro no fue encontrado en la API.");
    }
  }

  private void mostrarLibrosGuardados() {
    listadoLibros = repositoryLibros.findAll();
    listadoLibros.stream()
          .forEach(System.out::println);
  }

  private void mostrarAutoresRegistrados() {
    listadoAutores = repositoryAutores.findAll();
    listadoAutores.stream()
          .forEach(System.out::println);
  }

  private void autoresVivosSegunAnio() {
    System.out.println("Ingresa el año vivo de autor(es) que desea buscar: ");
    var anio = teclado.nextInt();
    listadoAutores = repositoryAutores.listaAutoresVivosSegunAnio(String.valueOf(anio));
    listadoAutores.stream()
          .forEach(System.out::println);
  }

  private List<Libro> datosBusquedaLenguaje(String idioma){
    var dato = Idioma.fromString(idioma);
    System.out.println("Lenguaje buscado: " + dato);

    List<Libro> libroPorIdioma = repositoryLibros.findByIdioma(dato);
    return libroPorIdioma;
  }

  private void buscarLibroPorIdioma(){
    System.out.println("Selecciona el lenguaje/idioma que deseas buscar: ");

    var opcion = -1;
    while (opcion != 0) {
      var opciones = """
                    1. en - Ingles
                    2. es - Español
                    3. fr - Francés
                    4. pt - Portugués
                    
                    0. Volver a Las opciones anteriores
                    """;
      System.out.println(opciones);
      while (!teclado.hasNextInt()) {
        System.out.println("Formato inválido, ingrese un número que esté disponible en el menú");
        teclado.nextLine();
      }
      opcion = teclado.nextInt();
      teclado.nextLine();
      switch (opcion) {
        case 1:
          List<Libro> librosEnIngles = datosBusquedaLenguaje("[en]");
          librosEnIngles.forEach(System.out::println);
          break;
        case 2:
          List<Libro> librosEnEspanol = datosBusquedaLenguaje("[es]");
          librosEnEspanol.forEach(System.out::println);
          break;
        case 3:
          List<Libro> librosEnFrances = datosBusquedaLenguaje("[fr]");
          librosEnFrances.forEach(System.out::println);
          break;
        case 4:
          List<Libro> librosEnPortugues = datosBusquedaLenguaje("[pt]");
          librosEnPortugues.forEach(System.out::println);
          break;
        case 0:
          return;
        default:
          System.out.println("Ningún idioma seleccionado");
      }
    }
  }
}