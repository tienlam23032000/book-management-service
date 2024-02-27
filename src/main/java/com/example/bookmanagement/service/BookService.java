package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.exception.DuplicateIdException;
import com.example.bookmanagement.exception.NotFoundException;
import com.example.bookmanagement.mapper.BookMapper;
import com.example.bookmanagement.model.*;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.repository.BookRepositoryCustom;
import com.example.bookmanagement.repository.UserRepository;
import com.example.bookmanagement.service.base.BaseService;
import com.example.bookmanagement.utils.PaginationUtils;
import com.example.bookmanagement.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BaseService { // => spring bean

    private BookRepositoryCustom bookRepositoryCustom;

    private BookRepository bookRepository;

    private UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository,
                        BookRepositoryCustom bookRepositoryCustom) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookRepositoryCustom = bookRepositoryCustom;
    }

    public BookDTO getById(String id) {
        UUIDUtils.validateUUID(id);
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id " + id + " not found."));
        return BookMapper.mapEntityToDTO(book);
    }

    public BookDTO create(BookCreateDTO request) {
        Optional<Book> bookOptional = bookRepository.findById(request.getId());
        if(bookOptional.isPresent()) {
            throw new DuplicateIdException("Id " + request.getId() + " duplicate");
        }
        Book bookRequest = BookMapper.mapDTOCreationToEntity(request);
        bookRequest.setDeleted(false);
        Book book = bookRepository.save(bookRequest);
        return BookMapper.mapEntityToDTO(book);
    }

    public BookDTO update(String id, BookDTO dto) {
        User user = null;
        Book bookOriginal = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id " + id + " not found."));
        if(dto.getUserId() != null) {
            user = userRepository.findById(dto.getUserId()).orElseThrow(() ->
                    new NotFoundException("User with id " + id + " not found."));
        }
        dto.setId(id);
        Book bookRequest = BookMapper.mapDTOtoEntity(dto);
        bookRequest.setUser(user);
        bookRequest.setId(id);
        Book book = bookRepository.save(bookRequest);
        return BookMapper.mapEntityToDTO(book);
    }

    public BookDTO updatePartial(String id, BookPartialDTO dto) {
        User user = null;
        Book bookOriginal = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id " + id + " not found."));
        if (dto.getUserId() != null) {
            if (dto.getUserId().isPresent()) {
                user = userRepository.findById(dto.getUserId().get()).orElseThrow(() ->
                        new NotFoundException("User with id " + id + " not found."));
            }
        }
        dto.setId(id);
        Book bookRequest = BookMapper.mapPatch(bookOriginal, dto);
        bookRequest.setUser(user);
        Book book = bookRepository.save(bookRequest); // update vào db
        return BookMapper.mapEntityToDTO(book);
    }

    public void delete(String id) {
        UUIDUtils.validateUUID(id);
        Book originBook = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id " + id + " not found."));
        originBook.setDeleted(true);
        bookRepository.save(originBook);
    }

    public BookListDTO getAll(FilterParam filterParam) {
        PaginationUtils.validatePageAndSize(filterParam.getPage(), filterParam.getSize());
        Href href = new Href();
        int totalPage = (int) Math.round((double) bookRepository.getTotalData() / filterParam.getSize());
        var uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/books");
        if (filterParam.getAuthor() != null) {
            if (filterParam.getAuthor().isPresent()) {
                uri.path("&author=" + filterParam.getAuthor().get());
            }
        }
        if(filterParam.getPage() > 1) {
            href.setPrevious(uri.queryParam("page", filterParam.getPage() - 1)
                    .queryParam("size", filterParam.getSize())
                    .toUriString());
        }
        if(filterParam.getPage() != totalPage) {
            href.setNext(uri.queryParam("page", filterParam.getPage() + 1)
                    .queryParam("size", filterParam.getSize())
                    .toUriString());
        }
        List<BookDTO> bookDTOS = BookMapper
                .mapEntitiesToDTOs(bookRepositoryCustom.findAll(filterParam));
        href.setSize(String.valueOf(bookDTOS.size()));
        return new BookListDTO(bookDTOS, href);
    }

    public BookDTO borrowBook(String bookId, String userId) {
        UUIDUtils.validateUUID(bookId);
        UUIDUtils.validateUUID(userId);
        Book bookOriginal = bookRepository.findById(bookId).orElseThrow(() ->
                new NotFoundException("Book with id " + bookId + " not found."));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("User with id " + userId + " not found."));

        bookOriginal.setUser(user);
        Book book = bookRepository.save(bookOriginal);

        return BookMapper.mapEntityToDTO(book);
    }
}
