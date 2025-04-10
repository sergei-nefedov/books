package pers.nefedov.books.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.nefedov.books.BookDto;
import pers.nefedov.books.models.Book;
import pers.nefedov.books.services.BookService;

@Controller
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getBooks(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String brand,
                           @RequestParam(required = false) Integer year,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {

        Page<Book> bookPage = (title != null || brand != null || year != null)
                ? bookService.findByFilters(title, brand, year, PageRequest.of(page, size))
                : bookService.findAll(PageRequest.of(page, size));

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", bookPage.getNumber());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalItems", bookPage.getTotalElements());
        model.addAttribute("pageSize", size);

        model.addAttribute("titleFilter", title);
        model.addAttribute("brandFilter", brand);
        model.addAttribute("yearFilter", year);
        return "books/list";
    }

    @PostMapping
    public String addBook(
            @Validated BookDto bookDto,
            RedirectAttributes redirectAttributes) {
        try {
            BookDto createdBook = bookService.add(bookDto);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
            return "redirect:/api/books";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding book");
            return "redirect:/api/books";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            bookService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Book deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting book");
        }
        return "redirect:/api/books";
    }

    @PutMapping("/{id}")
    public String updateBook(
            @PathVariable Long id,
            @ModelAttribute Book book,
            RedirectAttributes redirectAttributes) {
        try {
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating book: " + e.getMessage());
        }
        return "redirect:/api/books";
    }
}
