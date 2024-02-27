package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isDeleted = false")
    Optional<Book> findById(String id);

    @Query("SELECT COUNT(b) FROM Book b WHERE b.isDeleted = false")
    int getTotalData();

}
