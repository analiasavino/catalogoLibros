package com.analiasavino.catalogoDeLibros;

import com.analiasavino.catalogoDeLibros.principal.Main;
import com.analiasavino.catalogoDeLibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoDeLibrosApplication implements CommandLineRunner {

	@Autowired //le indica a spring que quiero que le haga una inyeccion

	private LibroRepository repository;

  public static void main(String[] args) {
		SpringApplication.run(CatalogoDeLibrosApplication.class, args);
	}

  @Override
	public void run(String ...args)throws Exception{
		Main main = new Main(repository);
		main.muestraElMenu();
	}
}
