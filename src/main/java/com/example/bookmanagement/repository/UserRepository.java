package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isDeleted = false")
    Optional<User> findById(String id);

    @Query("SELECT COUNT(u) FROM User u WHERE u.isDeleted = false")
    int getTotalData();
}
