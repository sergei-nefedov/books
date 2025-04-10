package pers.nefedov.books.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pers.nefedov.books.dto.BookDto;
import pers.nefedov.books.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book bookDtoToBook(BookDto bookDto);
    BookDto bookToBookDto(Book book);
}