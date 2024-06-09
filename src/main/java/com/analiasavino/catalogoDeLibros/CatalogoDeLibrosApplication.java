package com.analiasavino.catalogoDeLibros;

import com.analiasavino.catalogoDeLibros.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoDeLibrosApplication implements CommandLineRunner {

  public CatalogoDeLibrosApplication() {
  }

  public static void main(String[] args) {
		SpringApplication.run(CatalogoDeLibrosApplication.class, args);
	}

  @Override
	public void run(String ...args)throws Exception{
		Main main = new Main();
		main.muestraElMenu();
	}
}
