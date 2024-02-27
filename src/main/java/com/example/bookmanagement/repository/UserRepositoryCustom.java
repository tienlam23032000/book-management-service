package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.FilterParam;
import com.example.bookmanagement.model.UserFilterParam;

import java.util.List;

public interface UserRepositoryCustom {

    final String GET_ALL_QUERY = "SELECT u FROM User u WHERE u.isDeleted = false";

    List<User> findAll(UserFilterParam filterParam);

}
