package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.model.FilterParam;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepositoryCustom {

    final String GET_ALL_QUERY = "SELECT b FROM Book b WHERE b.isDeleted = false";

    List<Book> findAll(FilterParam filterParam);

}
