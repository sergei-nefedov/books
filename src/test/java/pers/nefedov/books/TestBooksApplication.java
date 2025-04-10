package pers.nefedov.books;

import org.springframework.boot.SpringApplication;

public class TestBooksApplication {

	public static void main(String[] args) {
		SpringApplication.from(BooksApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
