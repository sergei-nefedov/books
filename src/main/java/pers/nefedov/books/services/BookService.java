package pers.nefedov.books.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.nefedov.books.dto.BookDto;
import pers.nefedov.books.models.Book;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByFilters(String title, String brand, Integer year, Pageable pageable);

    BookDto add(BookDto bookDto);

    void deleteById(Long id);

    void update(Long id, BookDto bookDto);
}

