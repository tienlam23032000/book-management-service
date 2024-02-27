package com.example.bookmanagement.mapper;


import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.BookCreateDTO;
import com.example.bookmanagement.model.BookDTO;
import com.example.bookmanagement.model.BookPartialDTO;
import com.example.bookmanagement.model.UserPartialDTO;
import com.example.bookmanagement.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book mapDTOtoEntity(BookDTO dto) {
        // validate id
        UUIDUtils.validateUUID(dto.getId());
        return new Book(dto.getId(), dto.getName(), dto.getDescription(),
                dto.getAuthor());
    }

    public static Book mapDTOCreationToEntity(BookCreateDTO dto) {
        UUIDUtils.validateUUID(dto.getId());
        return new Book(dto.getId(), dto.getName(), dto.getDescription(),
                dto.getAuthor());
    }

    public static BookDTO mapEntityToDTO(Book book) {
        BookDTO dto = new BookDTO(book.getId(), book.getName(), book.getDescription(),
                book.getAuthor());
        if(book.getUser() != null) {
            dto.setUserId(book.getUser().getId());
        }
        return dto;
    }

    public static Book mapPatch(Book originalBook, BookPartialDTO dto) {
        Book bookUpdated = new Book();
        bookUpdated.setId(originalBook.getId());

        if(dto.getName() == null) { // cả cục optional null
            bookUpdated.setName(originalBook.getName());
        } else if (dto.getName().isPresent()) {
            bookUpdated.setName(dto.getName().get());
        } else {
            bookUpdated.setName(null);
        }

        if(dto.getDescription() == null) {
            bookUpdated.setDescription(originalBook.getDescription());
        } else if (dto.getDescription().isPresent()) {
            bookUpdated.setDescription(dto.getDescription().get());
        } else {
            bookUpdated.setDescription(null);
        }

        if(dto.getAuthor() == null) {
            bookUpdated.setAuthor(originalBook.getAuthor());
        } else if (dto.getAuthor().isPresent()) {
            bookUpdated.setAuthor(dto.getDescription().get());
        } else {
            bookUpdated.setAuthor(null);
        }

        return bookUpdated;
    }

    public static List<BookDTO> mapEntitiesToDTOs(List<Book> bookOrigin) {
        List<BookDTO> books = new ArrayList<>();
        for(Book b : bookOrigin) {
            books.add(mapEntityToDTO(b));
        }
        return books;
    }
}
