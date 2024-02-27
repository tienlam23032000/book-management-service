package com.example.bookmanagement.controller;

import com.example.bookmanagement.controller.base.BaseController;
import com.example.bookmanagement.model.*;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller // stereotype annotation cho controller nói chung
@RestController
@RequestMapping("/api/v1/books")
//@CrossOrigin(origins = "http://localhost:8000")
//@CrossOrigin
public class BookController implements BaseController<BookDTO, BookCreateDTO,
        BookPartialDTO, BookListDTO, String> {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{bookId}") // get by id: lấy book theo id
    public ResponseEntity<BookDTO> getById(@PathVariable("bookId") String id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping // create book
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateDTO bookCreateDTO) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(bookService.create(bookCreateDTO));
    }

    @PutMapping("{bookId}")
    public ResponseEntity<BookDTO> update(@PathVariable("bookId") String id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO));
    }

    @PatchMapping("{bookId}")
    public ResponseEntity<BookDTO> updatePartial(@PathVariable("bookId") String id,
                                          @RequestBody BookPartialDTO dto) {
        return ResponseEntity.ok(bookService.updatePartial(id, dto));
    }

    @DeleteMapping("{bookId}")
    public void delete(@PathVariable("bookId") String id) {
        bookService.delete(id);
    }

    @GetMapping
    public ResponseEntity<BookListDTO> getList(FilterParam filterParam) {
        BookListDTO books = bookService.getAll(filterParam);
        return ResponseEntity.ok(books);
    }

    @GetMapping("{bookId}/users/{userId}")
    public ResponseEntity<BookDTO> borrowBook(@PathVariable("bookId") String bookId,
                                              @PathVariable("userId") String userId) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, userId));
    }
}
