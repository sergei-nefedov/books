package pers.nefedov.books.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.nefedov.books.dto.BookDto;
import pers.nefedov.books.mappers.BookMapper;
import pers.nefedov.books.models.Book;
import pers.nefedov.books.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findByFilters(String title, String brand, Integer year, Pageable pageable) {
        return bookRepository.findByFilters(title, brand, year, pageable);
    }

    @Override
    public BookDto add(BookDto bookDto) {
        Book inconingBook = bookMapper.bookDtoToBook(bookDto);
        Book storedBook = bookRepository.save(inconingBook);
        return bookMapper.bookToBookDto(storedBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setVendorCode(updatedBook.getVendorCode());
        book.setTitle(updatedBook.getTitle());
        book.setYear(updatedBook.getYear());
        book.setBrand(updatedBook.getBrand());
        book.setStock(updatedBook.getStock());
        book.setPrice(updatedBook.getPrice());

        bookRepository.save(book);
    }
}
